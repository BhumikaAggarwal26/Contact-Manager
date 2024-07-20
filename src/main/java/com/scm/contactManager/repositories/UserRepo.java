package com.scm.contactManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.contactManager.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
    
}
