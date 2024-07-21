package com.scm.contactManager.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.contactManager.entities.User;
import com.scm.contactManager.helpers.AppConstants;
import com.scm.contactManager.repositories.UserRepo;
import com.scm.contactManager.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Override
    // public Optional<User> getUserById(String id) {
       
    // }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));

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
