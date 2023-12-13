package com.ishaan.spotifybackend.controllers;

import org.springframework.web.bind.annotation.RestController;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class AuthController {
 
    private static final String clientId = "f97f541285d24c52a12939a59d65a69c";
    private static final String clientSecret = "f186b4e66470458e9ba2f50ca40042bb";
   private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/callback");
    
    private static SpotifyApi spotifyApi;
    
    // Endpoint to initiate the Spotify authorization process
    @GetMapping("login")
    @CrossOrigin(origins = "http://localhost:5173")
    public String spotifyLogin() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();
        
       
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true) 
                .build();
        
        URI uri = authorizationCodeUriRequest.execute();
        return uri.toString(); 
    }
    

    @GetMapping(value = "get-user-code")
    public String spotifyCallback(@RequestParam("code") String code) {
        try {
            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                    .build();
    
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            
            return "Authorization Successful!";
        } catch (Exception e) {
            return "Authorization Failed: " + e.getMessage(); 
        }
    }
}