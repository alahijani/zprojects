package org.github.alahijani.zprojects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ali Lahijani
 */
@Controller
public class MainController {
    /**
     * Custom handler for the welcome view.
     */
    @RequestMapping("/")
    public String welcomeHandler() {
        return "welcome";
    }


}
