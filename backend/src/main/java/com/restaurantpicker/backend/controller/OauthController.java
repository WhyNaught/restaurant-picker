package com.restaurantpicker.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.restaurantpicker.backend.services.OAuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OauthController {

    private OAuthService oAuthService; 

    @GetMapping("/oauth/google/code")
    public String grantCode(@RequestParam("code") String code, @RequestParam("scope") String scope, @RequestParam("authuser") String authUser, @RequestParam("prompt") String prompt) {
        return oAuthService.processGoogleCode(code); 
    }
    
}
