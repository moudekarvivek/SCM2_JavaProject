package com.scmvivek.services;
import java.util.List;
import java.util.Optional;

import com.scmvivek.entities.User;

public interface UserService {
    User saveUser(User user);
    
    
    Optional<User> getUserById(String id); // Optional will check if user is present or not if present then it will return the user object
    
    Optional<User> updateUser(User user);
    
    void deleteUser(String id);
    
    boolean isUserExist(String userId);
    
    boolean isUserExistByEmail(String email);
    
    List<User> getAllUsers();
    
    // add more methods here related to user service logic
}
