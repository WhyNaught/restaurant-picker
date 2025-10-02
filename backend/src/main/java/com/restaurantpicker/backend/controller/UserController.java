package com.restaurantpicker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantpicker.backend.models.User;
import com.restaurantpicker.backend.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    @Autowired
    private UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService; 
    }

    @GetMapping("/user/get")
    public User getUserById(@RequestParam("user_id") int user_id) {
        final User fetchedUser = userService.fetchUserById(user_id); 
        System.out.println(fetchedUser);

        return fetchedUser; 
    }

    @PostMapping("/user/register") // this method is for manually creating users, not for using google oauth
    public ResponseEntity<String> postMethodName(@RequestBody User newUser) {
        return userService.registerUser(newUser); 
    }
    
    
}
