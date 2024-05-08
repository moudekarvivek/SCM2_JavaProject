package com.scmvivek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scmvivek.entities.User;
import com.scmvivek.forms.UserForm;
import com.scmvivek.helpers.Message;
import com.scmvivek.helpers.MessageType;
import com.scmvivek.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {
    
    @Autowired
    private UserService userService;

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
    
    //Contact Page
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }
    
    //Login
    @GetMapping("/login")
    public String login(){  
        return new String("login");
    }
    
    //Register
    @GetMapping("/register")
    public String register(Model model){ // will send Blank Object from here using Model
        
        UserForm userForm = new UserForm();
        //we can also put some default values in the object
        
        // userForm.setName("Vivek");
        model.addAttribute("userForm", userForm); // sending blank object to register page (register.html
        
        return new String("register");
    }
    
    //Processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm,HttpSession session){ // In this userForm automatically object will get create and the data which is coming in the form will get set in this object
        System.out.println("Processing Registeration");
        // fetch form data
        // To recieve data our to fetch it we will make class
        // UserForm class to recieve data in this class object
        System.out.println(userForm);
        // Validate Form Data
        
        // Save to Database
        // userservice this will content all methods which will execute business logic of the user
        
        //UserForm convert or added data to User
        // User user = User.builder()                we are not using builder pattern because default value of self is not coming
        //     .name(userForm.getName())
        //     .email(userForm.getEmail())
        //     .password(userForm.getPassword())
        //     .about(userForm.getAbout())
        //     .phoneNumber(userForm.getPhoneNumber())
        //     .profilePic("")
        //     .build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("");



        User savedUser = userService.saveUser(user);
        System.out.println("User Saved");
        // Message= "Registration Successful"

        Message message = Message.builder().content("Registration Successful").type(MessageType.yellow).build();
        //add the message: [to Send message we will use session]
        session.setAttribute("message", message); // This message will be shown after do register is called
        
        // Redirect to login page
        return "redirect:/register";// After Processing Registeration redirecting to register page
    }       
}
