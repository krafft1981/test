package com.work.test.mvcController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/mvc")
@Controller
public class MvcIndexController {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String mvcIndex(Model model) {
        return "index";
    }
}
