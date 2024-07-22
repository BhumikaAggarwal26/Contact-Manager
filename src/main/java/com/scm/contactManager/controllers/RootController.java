package com.scm.contactManager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.contactManager.entities.User;
import com.scm.contactManager.helpers.Helper;
import com.scm.contactManager.services.UserService;



@ControllerAdvice
public class RootController {
    
    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        // Adding Logged In User Info to the Model to be used in View
        if (authentication == null) { // Only for Authenticated user
            return;
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        
        logger.info("User logged in: {}", username);
       
        User user = userService.getUserByEmail(username);
        
        model.addAttribute("loggedInUser", user);

    }
}
