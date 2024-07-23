package com.scm.contactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.contactManager.entities.Contacts;
import com.scm.contactManager.entities.User;
import com.scm.contactManager.forms.ContactForm;
import com.scm.contactManager.helpers.Helper;
import com.scm.contactManager.helpers.Message;
import com.scm.contactManager.helpers.MessageType;
import com.scm.contactManager.services.ContactService;
import com.scm.contactManager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/addContact")
    public String addNewContact(Model model) {

        ContactForm contactForm = new ContactForm();

        model.addAttribute("contactForm", contactForm);
    
        return new String("user/contacts/add_contact");
    }

    @PostMapping("/saveContact")
    public String saveNewContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, HttpSession session, Authentication authentication) {

        if(result.hasErrors()){
            session.setAttribute("message", Message.builder()
                    .content("Please fill the correct information")
                    .type(MessageType.red)
                    .build());

            return "user/contacts/add_contact";
        }

        Contacts contact = new Contacts();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setFavorite(contactForm.isFavorite());
        
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        contact.setUser(user);

        contactService.saveContact(contact);


        return new String("redirect:/user/addContact");
    }
    
    

}
