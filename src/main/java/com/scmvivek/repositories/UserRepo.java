package com.scmvivek.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scmvivek.entities.User;

// This UserRepo interface will be used to interact with the database and this will content all the methods which will help us to interact with the database
@Repository
public interface UserRepo extends JpaRepository<User, String> {
//extra Methods DB related operations
//Custom Query Methods
//Custom Finder Methods
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}


