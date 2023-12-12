package com.ishaan.spotifybackend.controllers;

import org.springframework.web.bind.annotation.RestController;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/api")
public class SpotifyController {
 
    private static final String clientId = "f97f541285d24c52a12939a59d65a69c";
    private static final String clientSecret = "f186b4e66470458e9ba2f50ca40042bb";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/callback");
    
    private String code = "";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

    @GetMapping("login")
    @ResponseBody
    public String spotifyLogin() {
     AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//          .state("x4xkmn9pu3j6ukrs8n")
//          .scope("user-read-birthdate,user-read-email")
//          .show_dialog(true)
    .build();
    }
    

}