package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.User;
import org.github.alahijani.zprojects.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@RequestMapping("/user")
@Controller
public class UserBean {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String all(ModelMap map) {
        List<User> users = userService.findAll();
        map.put("users", users);
        return "user/list";
    }

/*
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String create(@ModelAttribute("userCommand") UserCommand userCommand) {
        if (userCommand == null) {
            throw new IllegalArgumentException("A userDt is required");
        }
        User user = createUserFromUserCommand(userCommand);
        return "redirect:/user/" + user.getId();
    }
*/

/*
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String create(@ModelAttribute("userCommand") UserCommand userCommand) {
        if (userCommand == null) {
            throw new IllegalArgumentException("A userDt is required");
        }
        User user = createUserFromUserCommand(userCommand);
        return "redirect:/user/" + user.getId();
    }
*/

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());

        return "user/edit";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String get(@PathVariable("username") String username, ModelMap map) {
        User user = userService.findByUsername(username);
        map.put("user", user);

        return "user/edit";
    }

    @RequestMapping(method=RequestMethod.POST)
   	public String create(/*@Valid*/ User user, BindingResult result) {
   		if (result.hasErrors()) {
   			return "user/edit";
   		}
        userService.save(user);

   		return "redirect:/user/" + user.getUsername();
   	}

}
