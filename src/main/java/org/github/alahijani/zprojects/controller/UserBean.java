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
import javax.validation.Valid;
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());

        return "user/edit";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") String id, ModelMap map) {
        User user = userService.findById(id);
        map.put("user", user);

        return "user/edit";
    }

    @RequestMapping(method=RequestMethod.POST)
   	public String save(@Valid User user, BindingResult result) {
        if (userService.duplicateUsername(user)) {
            result.rejectValue("username", "duplicate");
        }
        if (result.hasErrors()) {
            return "user/edit";
        }

        user = userService.save(user);

        return "redirect:/user/" + user.getId();
    }

}
