package org.github.alahijani.zprojects.controller;

import org.github.alahijani.zprojects.model.User;
import org.github.alahijani.zprojects.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ali Lahijani
 */
@org.springframework.stereotype.Controller
public class UserBean implements Controller {

    @Resource
    private UserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        List<User> users = userService.findAll();
        return new ModelAndView("users", "users", users);
    }


}
