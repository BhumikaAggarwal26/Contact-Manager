package com.scm.contactManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.contactManager.entities.Contacts;
import com.scm.contactManager.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {
 // get contact

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{contactId}")
    public Contacts getContact(@PathVariable String contactId) {
        return contactService.getById(contactId);
    }
}
