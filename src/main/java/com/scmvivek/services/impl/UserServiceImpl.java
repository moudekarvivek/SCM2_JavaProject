package com.scmvivek.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scmvivek.entities.User;
import com.scmvivek.helpers.ResourceNotFoundException;
import com.scmvivek.repositories.UserRepo;
import com.scmvivek.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    //To save this we will need a Repository
    
    @Autowired
    private UserRepo userRepo;
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
       //user id : have to generate it
       String userId=UUID.randomUUID().toString();
       user.setUserId(userId);
       
       //password Encode
        //user.setPassword(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        //We will update user from user2
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnable(user.isEnable());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        //Save the user in the database

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
   
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).
        orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null); //If user is present then return true else false
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
