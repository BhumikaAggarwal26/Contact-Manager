package com.scm.contactManager.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.contactManager.entities.Contacts;
import com.scm.contactManager.helpers.ResourceNotFoundException;
import com.scm.contactManager.repositories.ContactRepo;
import com.scm.contactManager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contacts saveContact(Contacts contact) {
        
        contact.setId(UUID.randomUUID().toString());
        return contactRepo.save(contact);

    }

    @Override
    public Contacts updateContacts(Contacts contacts) {

        var oldContact = contactRepo.findById(contacts.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        oldContact.setName(contacts.getName());
        oldContact.setEmail(contacts.getEmail());
        oldContact.setPhoneNumber(contacts.getPhoneNumber());
        oldContact.setAddress(contacts.getAddress());
        oldContact.setDescription(contacts.getDescription());
        oldContact.setPicture(contacts.getPicture());
        oldContact.setFavorite(contacts.isFavorite());
        oldContact.setLinkedInLink(contacts.getLinkedInLink());

        return contactRepo.save(oldContact);
    }

    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found with given ID:" + id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contacts> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contacts getById(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found with given ID:" + id));
    }

    @Override
    public List<Contacts> getByUserId(String userId) {
       return contactRepo.findByUserId(userId);
    }

}
