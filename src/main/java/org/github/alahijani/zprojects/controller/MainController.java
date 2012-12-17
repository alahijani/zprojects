package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.Task;
import org.github.alahijani.zprojects.model.User;
import org.github.alahijani.zprojects.service.TaskService;
import org.github.alahijani.zprojects.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @Resource
    private UserService userService;
    @Resource
    private TaskService taskService;

    /**
     * Custom handler for the welcome view.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String welcomePage(ModelMap map, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.findVisible(username);

        map.put("user", user);
        map.put("tasks", tasks);


        return "welcome";
    }

}
