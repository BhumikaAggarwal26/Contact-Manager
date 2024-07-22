package com.scm.contactManager.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping("/dashboard")
    public String userDashboard(Model model, Authentication authentication) {
        return new String("user/dashboard");
    }

    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        return new String("user/profile");
    }
    
    
}
