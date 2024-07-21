package com.scm.contactManager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.contactManager.entities.User;
import com.scm.contactManager.forms.UserForm;
import com.scm.contactManager.helpers.Message;
import com.scm.contactManager.helpers.MessageType;
import com.scm.contactManager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

        @Autowired
        private UserService userService;

        private Logger logger = LoggerFactory.getLogger(PageController.class);

        @RequestMapping("/")
        public String index() {
            return new String("redirect:/home");
        }
        
    
        @RequestMapping("/home")
        public String home(){
            return "home";  // return required html page
        }

        @RequestMapping("/about")
        public String aboutPage() {
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
        public String addNewUser(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
            
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

            logger.info("New User Saved Successfully!!");
             
            Message message = new Message();
            message.setContent("Registration Successful");
            message.setType(MessageType.green);

            session.setAttribute("message", message);

            return new String("redirect:/signup");
        }
        
}
