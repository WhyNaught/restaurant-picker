package com.restaurantpicker.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurantpicker.backend.models.User;
import com.restaurantpicker.backend.repository.userRepo;

@Service
public class UserService {

    @Autowired 
    userRepo userRepo; 

    public User fetchUserById(int user_id) {
        final User fetchedUser = userRepo.getUserById(user_id); 

        return fetchedUser; 
    }

    public ResponseEntity<String> registerUser(User newUser) {
        userRepo.save(newUser); // we upload to the database 

        return new ResponseEntity<>("New user registered successfully!", HttpStatus.CREATED); 
    }
}
