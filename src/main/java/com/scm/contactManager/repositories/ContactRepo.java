package com.scm.contactManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.contactManager.entities.Contacts;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, String>{

}
