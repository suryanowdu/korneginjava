package com.fsvmxd67.MyWebSite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyWebSiteController {
    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "mainPage";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("title", "О нас");
        return "aboutPage";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("title", "Контакты");
        return "contactPage";
    }
}