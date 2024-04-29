package com.scmvivek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println(" Home Page handler");
        //sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("Youtube_Channel", "Vivek Moudekar");
        model.addAttribute("GitHub_Repository", "https://github.com/moudekarvivek/Discussion-Forum");
        return "home";
    }
    //about route
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About Page Loading");
        return "about";
    }
    //Services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services Page Loading");
        return "services";
    }
}
