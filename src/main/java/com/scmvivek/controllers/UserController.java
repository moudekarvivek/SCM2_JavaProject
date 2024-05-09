package com.scmvivek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    // User Dashboard Page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        System.out.println("User Dashboard Page");
        return "user/dashboard";
    }
    
    //user Profile Page
    @RequestMapping(value = "/profile")
    public String userProfile(){
        System.out.println("User Profile Page");
        return "user/profile";
    }
    
    //user add contact Page
    

    //user view contact Page

    //user edit contact Page

    //user delete conact Page
}
