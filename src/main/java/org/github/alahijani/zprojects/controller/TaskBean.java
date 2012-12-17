package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.Project;
import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.model.User;
import org.github.alahijani.zprojects.service.ProjectService;
import org.github.alahijani.zprojects.service.TaskService;
import org.github.alahijani.zprojects.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ali Lahijani
 */
@RequestMapping("/project/{projectId}/task")
@Controller
public class TaskBean extends BaseBean {

    @Resource
    private TaskService taskService;
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        userService.registerCustomEditor(binder);
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
    public String saveNew(@PathVariable("projectId") String projectId, @Valid Task task, BindingResult result) {
        Project project = projectService.getReference(projectId);

        task.setProject(project);
        if (result.hasErrors()) {
            return "task/new";
        }

        task = taskService.save(task);

        return "redirect:/project/" + project.getId() + "/task/" + task.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("projectId") String projectId, @PathVariable("id") String id, ModelMap map) {
        Task task = taskService.findById(id);
        if (!task.getProject().getId().equals(projectId))
            throw new NoResultException();

        map.put("task", task);
        map.addAttribute("allUsers", getAllUsers());

        return "task/view";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String saveById(@PathVariable("projectId") String projectId, @PathVariable("id") String id, @Valid Task task, BindingResult result) {
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

        return "redirect:/project/" + projectId + "/task/" + id;
    }

    /**
     * Returns the {@link UserService#findAll() collection of all users} in a fashion that can be used
     * for rendering a combo-box.
     *
     * @return the set of all users, each represented by its {@link User#fullName full name}
     */
    private Map<User, String> getAllUsers() {
        List<User> users = userService.findAll();

        HashMap<User, String> map = new HashMap<User, String>(users.size());
        for (User user : users) {
            map.put(user, user.getFullName());
        }
        return map;
    }

    /**
     * Reassigns the given {@link Task} to another user.
     *
     * @param id        ID of the {@link Task} to reassign
     * @param task      need not be {@link Valid}, only value of {@link Task#assignee assignee} matters
     * @param projectId ID of the parent {@link Project}
     * @return a redirect to the newly saved task
     */
    @RequestMapping(value = "/{id}/assignee", method = RequestMethod.POST)
    public String reassign(@PathVariable("projectId") String projectId,
                           @PathVariable("id") String id,
                           Task task,
                           Principal principal) {

        User assignee = task.getAssignee();

        /**
         * reload the data from the database
         */
        task = taskService.findById(id);

        if (!task.getProject().getId().equals(projectId))
            throw new NoResultException("Task with ID " + id + " in project with ID " + projectId);

        User oldAssignee = task.getAssignee();
        if (oldAssignee == null || !oldAssignee.getUsername().equals(principal.getName()))
            throw new AccessDeniedException("Cannot be reassigned");

        task.setAssignee(assignee);

        task = taskService.save(task);

        return "redirect:/project/" + projectId + "/task/" + id;
    }

}
