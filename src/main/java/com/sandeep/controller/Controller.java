package com.sandeep.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String indexPage(Model m, @AuthenticationPrincipal String user) {

        m.addAttribute("name", user);

        return "index";
    }
}