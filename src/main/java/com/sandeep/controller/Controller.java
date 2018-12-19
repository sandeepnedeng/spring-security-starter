package com.sandeep.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String indexPage(Model m) {

        m.addAttribute("name", "sandeep");

        return "index";
    }
}
