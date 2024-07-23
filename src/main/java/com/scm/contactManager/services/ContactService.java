package com.scm.contactManager.services;

import java.util.List;

import com.scm.contactManager.entities.Contacts;

public interface ContactService {

    Contacts saveContact(Contacts contact);

    Contacts updateContacts(Contacts contacts);

    void delete(String id);

    List<Contacts> getAll();

    Contacts getById(String id);

    List<Contacts> getByUserId(String userId);

}
