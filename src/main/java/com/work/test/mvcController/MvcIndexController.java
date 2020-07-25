package com.work.test.mvcController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/mvc")
@Controller
public class MvcIndexController {

    @GetMapping(value = {"/", "/index"})
    public String mvcIndex(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/noAccess")
    public String noAccess() {
        return "noAccess";
    }
}
