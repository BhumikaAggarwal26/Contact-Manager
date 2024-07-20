package com.scm.contactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.contactManager.entities.User;
import com.scm.contactManager.forms.UserForm;
import com.scm.contactManager.services.UserService;

import jakarta.validation.Valid;


@Controller
public class PageController {

        @Autowired
        private UserService userService;
    
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
        public String signup(Model model) {
            UserForm userForm = new UserForm();
            model.addAttribute("userForm", userForm);  // this blank attribute used during loading of signup page
                                                       // during submit -> data added to these field and goes to addNewUser
            return new String("signup");
        }

        @RequestMapping(value="/addNewUser", method=RequestMethod.POST)
        public String addNewUser(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult) {
            
            if(rBindingResult.hasErrors()){
                return "signup";
            }

            User user = new User();
             
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setAbout(userForm.getAbout());
            user.setProfilePic("/resources/static/images/default_ProfilePic.jpg");

            User savedUser = userService.saveUser(user);

            return new String("redirect:/signup");
        }
        
}
