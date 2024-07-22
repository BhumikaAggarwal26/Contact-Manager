package com.scm.contactManager.services;

import java.util.List;
import java.util.Optional;

import com.scm.contactManager.entities.User;

public interface UserService {

    // Optional<User> getUserById(String id);

    User saveUser(User user);

    // Optional<User> updateUser(User user);

    // void deleteUser(String id);

    // boolean isUserExist(String userId);

    // List<User> getAllUsers();

    User getUserByEmail(String email);
}
