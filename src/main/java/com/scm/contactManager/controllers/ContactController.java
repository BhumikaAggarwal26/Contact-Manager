package com.scm.contactManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class ContactController {

    @RequestMapping("/addContact")
    public String addNewContact() {
        return new String("/user/contacts/add_contact");
    }
    

}
