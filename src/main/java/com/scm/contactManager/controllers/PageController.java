package com.scm.contactManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
    
        @RequestMapping("/home")
        public String home(Model model){
            System.out.println("Home Page Handler");
            
            //Sending data to View
            model.addAttribute("project", "Contact Manager Project");
            model.addAttribute("gitHubLink", "https://github.com/BhumikaAggarwal26");
            
            return "home";  // return required html page
        }

        @RequestMapping("/about")
        public String aboutPage(Model model) {
            model.addAttribute("isLogin", true);
            return "about";
        }

        @RequestMapping("/service")
        public String servicePage() {
            return "service";
        }
        
        @RequestMapping("/contact")
        public String getContact() {
            return new String("contact");
        }
        
        @RequestMapping("/login")
        public String login() {
            return new String("login");
        }

        @RequestMapping("/signup")
        public String signup() {
            return new String("signup");
        }
}
