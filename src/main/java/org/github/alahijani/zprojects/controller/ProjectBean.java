package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.Project;
import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.service.ProjectService;
import org.github.alahijani.zprojects.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@RequestMapping("/project")
@Controller
public class ProjectBean {

    @Resource
    private ProjectService projectService;
    @Resource
    private TaskService taskService;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(ModelMap map) {
        List<Project> projects = projectService.findAll();
        map.put("projects", projects);
        return "project/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNew(ModelMap modelMap) {
        Project project = new Project();
        modelMap.addAttribute("project", project);

        return "project/new";
    }

    @RequestMapping(value = "/new", method=RequestMethod.POST)
    public String saveNew(@Valid Project project, BindingResult result) {
        if (projectService.duplicateCode(project)) {
            result.rejectValue("code", "duplicate");
        }
        if (result.hasErrors()) {
            return "project/new";
        }

        project = projectService.save(project);

        return "redirect:/project/" + project.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") String id, ModelMap map, Principal principal) {
        Project project = projectService.findById(id);
        map.put("project", project);

        if (principal != null) {
            String username = principal.getName();
            List<Task> tasks = taskService.findVisible(project, username);
            map.put("tasks", tasks);
        }

        return "project/view";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.POST)
   	public String saveById(@PathVariable("id") String id, @Valid Project project, BindingResult result) {
        project.setId(id);
        if (projectService.duplicateCode(project)) {
            result.rejectValue("code", "duplicate");
        }
        if (result.hasErrors()) {
            /**
             * todo: bug: unlike "edit data", "view data" should be actually reloaded from database
             */
            return "project/view";
        }

        project = projectService.save(project);

        return "redirect:/project/" + project.getId();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    public void notFount() {
    }
}
