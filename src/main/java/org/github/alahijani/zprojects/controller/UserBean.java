package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.User;
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
import java.util.List;

/**
 * @author Ali Lahijani
 */
@RequestMapping("/user")
@Controller
public class UserBean {

    @Resource
    private UserService userService;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String all(ModelMap map) {
        List<User> users = userService.findAll();
        map.put("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNew(ModelMap modelMap) {
        User user = new User();
        user.setEnabled(true);  // by default checked in the form
        modelMap.addAttribute("user", user);

        return "user/new";
    }

    @RequestMapping(value = "/new", method=RequestMethod.POST)
    public String saveNew(@Valid User user, BindingResult result) {
        if (userService.duplicateUsername(user)) {
            result.rejectValue("username", "duplicate");
        }
        if (result.hasErrors()) {
            return "user/new";
        }

        user = userService.save(user);

        return "redirect:/user/" + user.getId();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") String id, ModelMap map) {
        User user = userService.findById(id);
        map.put("user", user);

        return "user/view";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.POST)
   	public String saveById(@PathVariable("id") String id, @Valid User user, BindingResult result) {
        user.setId(id);
        if (userService.duplicateUsername(user)) {
            result.rejectValue("username", "duplicate");
        }
        if (result.hasErrors()) {
            /**
             * todo: bug: unlike "edit data", "view data" should be actually reloaded from database
             */
            return "user/view";
        }

        user = userService.save(user);

        return "redirect:/user/" + user.getId();
    }

}
