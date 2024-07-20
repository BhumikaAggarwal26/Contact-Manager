package com.scm.contactManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.contactManager.entities.User;
import com.scm.contactManager.repositories.UserRepo;
import com.scm.contactManager.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    // @Override
    // public Optional<User> getUserById(String id) {
       
    // }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        User savedUser = userRepo.save(user);
        return savedUser;
    }

    // @Override
    // public Optional<User> updateUser(User user) {
       
    // }

    // @Override
    // public void deleteUser(String id) {
       
    // }

    // @Override
    // public boolean isUserExist(String userId) {
       
    // }

    // @Override
    // public List<User> getAllUsers() {
        
    // }

}
