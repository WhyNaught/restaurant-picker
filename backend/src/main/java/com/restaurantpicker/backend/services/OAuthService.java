package com.restaurantpicker.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap; 
import org.springframework.util.LinkedMultiValueMap;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.restaurantpicker.backend.repository.userRepo;

// https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=http://localhost:8080/oauth/google/code&response_type=code&client_id=858944625931-ir146lm53p2mv01n0k3jg8o5o53u73nt.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+openid&access_type=offline

@Service
public class OAuthService {

    @Autowired
    private final userRepo userRepo; 

    private final String serverUrl; 
    private final String clientId; 
    private final String clientSecret; 

    // we do all our env variable injections through the constructor via DI :3
    public OAuthService(userRepo userRepo, @Value("${server_url}") String serverUrl, @Value("${client_id}") String clientId, @Value("${client_secret}") String clientSecret) {
        this.userRepo = userRepo; 
        this.serverUrl = serverUrl; 
        this.clientId = clientId; 
        this.clientSecret = clientSecret;
    }

    public String processGoogleCode(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("code", code);
        params.add("redirect_uri", serverUrl + "/oauth/google/code");
        params.add("client_id",clientId);
        params.add("client_secret", clientSecret);
        params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile");
        params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email");
        params.add("scope", "openid");
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);

        String url = "https://oauth2.googleapis.com/token";
        // this lets us make a post call to google's api to get the code we want 
        String accessToken = restTemplate.postForObject(url, requestEntity, String.class);
        return accessToken;
    }

    public JsonObject getProfileDetailsGoogle(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        // we ping google for the user's info once we get the access token
        String url = "https://www.googleapis.com/oauth2/v2/userinfo";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);

        return jsonObject; 
    }
}
