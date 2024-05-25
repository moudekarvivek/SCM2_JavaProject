package com.scmvivek.controllers;


import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scmvivek.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {
    
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // User Dashboard Page
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        System.out.println("User Dashboard Page");
        return "user/dashboard";
    }
    
    //user Profile Page
    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication){
        
        
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("Logged in User: {} ",username);
        System.out.println("User Profile Page");
        return "user/profile";
    }
    
    //user add contact Page
    

    //user view contact Page

    //user edit contact Page

    //user delete conact Page
}
