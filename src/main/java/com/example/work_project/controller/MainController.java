package com.example.work_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("title", "MainPage");
        return "main_page";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("title", "aboutPage");
        return "about";
    }


}