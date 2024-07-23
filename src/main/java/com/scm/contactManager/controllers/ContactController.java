package com.scm.contactManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactManager.forms.ContactForm;
import com.scm.contactManager.helpers.Message;
import com.scm.contactManager.helpers.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class ContactController {

    @RequestMapping("/addContact")
    public String addNewContact(Model model) {

        ContactForm contactForm = new ContactForm();

        model.addAttribute("contactForm", contactForm);
    
        return new String("user/contacts/add_contact");
    }

    @PostMapping("/saveContact")
    public String saveNewContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, HttpSession session) {

        if(result.hasErrors()){
            session.setAttribute("message", Message.builder()
                    .content("Please fill the correct information")
                    .type(MessageType.red)
                    .build());

            return "user/contacts/add_contact";
        }


        return new String("redirect:/addContact");
    }
    
    

}
