package com.scm.contactManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return new String("user/dashboard");
    }

    @RequestMapping("/profile")
    public String userProfile() {
        return new String("user/profile");
    }
    
    
}
