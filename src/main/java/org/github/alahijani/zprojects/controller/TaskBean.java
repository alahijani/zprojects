package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.Project;
import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.model.User;
import org.github.alahijani.zprojects.service.ProjectService;
import org.github.alahijani.zprojects.service.TaskService;
import org.github.alahijani.zprojects.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ali Lahijani
 */
@RequestMapping("/project/{projectId}/task")
@Controller
public class TaskBean {

    @Resource
    private TaskService taskService;
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(ModelMap map, @PathVariable("projectId") String projectId) {
        Project project = projectService.getReference(projectId);

        List<Task> tasks = taskService.findAll(project);
        map.put("tasks", tasks);
        map.put("project", project);
        return "task/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNew(ModelMap map, @PathVariable("projectId") String projectId) {
        Project project = projectService.getReference(projectId);

        Task task = new Task();
        task.setProject(project);
        map.addAttribute("task", task);
        map.addAttribute("allUsers", getAllUsers());

        return "task/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveNew(@Valid Task task, BindingResult result, @PathVariable("projectId") String projectId) {
        Project project = projectService.getReference(projectId);

        task.setProject(project);
        if (result.hasErrors()) {
            return "task/new";
        }

        task = taskService.save(task);

        return "redirect:/project/" + project.getId() + "/task/" + task.getId();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") String id, ModelMap map, @PathVariable("projectId") String projectId) {
        Task task = taskService.findById(id);
        if (!task.getProject().getId().equals(projectId))
            throw new NoResultException();

        map.put("task", task);
        map.addAttribute("allUsers", getAllUsers());

        return "task/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveById(@PathVariable("id") String id, @Valid Task task, BindingResult result, @PathVariable("projectId") String projectId) {
        Project project = projectService.getReference(projectId);

        task.setId(id);
        task.setProject(project);

        if (result.hasErrors()) {
            /**
             * todo: bug: unlike "edit data", "view data" should be actually reloaded from database
             */
            return "task/view";
        }

        task = taskService.save(task);

        return "redirect:/project/" + project.getId() + "/task/" + task.getId();
    }

    /**
     * Returns the {@link UserService#findAll() collection of all users} ni a fashion that can be used
     * for rendering a combo-box.
     *
     * @return the set of all users, each represented by its full name, indexed by database ID
     */
    private Map<String, String> getAllUsers() {
        List<User> users = userService.findAll();

        HashMap<String, String> map = new HashMap<String, String>(users.size());
        for (User user : users) {
            map.put(user.getId(), user.getFullName());
        }
        return map;
    }

}
