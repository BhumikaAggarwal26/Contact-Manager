package com.scm.contactManager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.contactManager.entities.Contacts;
import com.scm.contactManager.entities.User;
import com.scm.contactManager.formsdto.ContactFormDTO;
import com.scm.contactManager.helpers.Helper;
import com.scm.contactManager.helpers.Message;
import com.scm.contactManager.helpers.MessageType;
import com.scm.contactManager.services.ContactService;
import com.scm.contactManager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@RequestMapping("/user")
public class ContactController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/addContact")
    public String addNewContact(Model model) {

        ContactFormDTO contactFormDTO = new ContactFormDTO();

        model.addAttribute("contactFormDTO", contactFormDTO);
    
        return new String("user/contacts/add_contact");
    }

    @PostMapping("/saveContact")
    public String saveNewContact(@Valid @ModelAttribute ContactFormDTO contactFormDTO, BindingResult result, HttpSession session, Authentication authentication) {

        if(result.hasErrors()){
            session.setAttribute("message", Message.builder()
                    .content("Please fill the correct information")
                    .type(MessageType.red)
                    .build());

            return "user/contacts/add_contact";
        }

        Contacts contact = new Contacts();

        contact.setName(contactFormDTO.getName());
        contact.setEmail(contactFormDTO.getEmail());
        contact.setPhoneNumber(contactFormDTO.getPhoneNumber());
        contact.setAddress(contactFormDTO.getPhoneNumber());
        contact.setDescription(contactFormDTO.getDescription());
        contact.setLinkedInLink(contactFormDTO.getLinkedInLink());
        contact.setFavorite(contactFormDTO.isFavorite());
        
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        contact.setUser(user);

        contactService.saveContact(contact);

        session.setAttribute("message",
                Message.builder()
                        .content("Contact is Saved successfully !! ")
                        .type(MessageType.green)
                        .build()
        );


        return new String("redirect:/user/addContact");
    }

    @GetMapping("/viewContacts")
    public String viewAllContacts(Model model, Authentication authentication) {

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        List<Contacts> contacts = contactService.getByUserId(user.getUserId());

        model.addAttribute("allContactsOfUser", contacts);

        return new String("user/contacts/view_contacts");
    }

    @RequestMapping("/deleteContact/{contactId}")
    public String deleteContact(@PathVariable("contactId") String contactId, HttpSession session) {
        
        contactService.delete(contactId);
        logger.info("contactId {} deleted", contactId);

        session.setAttribute("message",
                Message.builder()
                        .content("Contact is Deleted successfully !! ")
                        .type(MessageType.green)
                        .build()
        );

        return "redirect:/user/viewContacts";
    }
    
    @GetMapping("/view/{contactId}")
    public String updateContactFormView(@PathVariable("contactId") String contactId, Model model) {

        var contact = contactService.getById(contactId);

        ContactFormDTO contactFormDTO = new ContactFormDTO();
        contactFormDTO.setName(contact.getName());
        contactFormDTO.setEmail(contact.getEmail());
        contactFormDTO.setPhoneNumber(contact.getPhoneNumber());
        contactFormDTO.setAddress(contact.getAddress());
        contactFormDTO.setDescription(contact.getDescription());
        contactFormDTO.setFavorite(contact.isFavorite());
        contactFormDTO.setLinkedInLink(contact.getLinkedInLink());
        
        model.addAttribute("contactFormDTO", contactFormDTO);
        model.addAttribute("contactId", contactId);

        return "user/contacts/update_contact_view";
    }

    @PostMapping("/updateContact/{contactId}")
    public String updateContact(@PathVariable("contactId") String contactId, @Valid @ModelAttribute ContactFormDTO contactFormDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/update_contact_view";
        }

        var contact = contactService.getById(contactId);

        contact.setId(contactId);
        contact.setName(contactFormDTO.getName());
        contact.setEmail(contactFormDTO.getEmail());
        contact.setPhoneNumber(contactFormDTO.getPhoneNumber());
        contact.setAddress(contactFormDTO.getAddress());
        contact.setDescription(contactFormDTO.getDescription());
        contact.setFavorite(contactFormDTO.isFavorite());
        contact.setLinkedInLink(contactFormDTO.getLinkedInLink());


        var updateContact = contactService.updateContacts(contact);
        logger.info("Updated Contact {}", updateContact);

        model.addAttribute("message", Message.builder().content("Contact Updated !!").type(MessageType.green).build());

        return "redirect:/user/viewContacts";
    }

}
