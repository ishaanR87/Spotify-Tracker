package com.ishaan.spotifybackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import jakarta.servlet.http.HttpServletResponse; // Change javax to jakarta
import java.io.IOException;
import java.net.URI;

import com.ishaan.spotifybackend.service.SpotifyService;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final SpotifyService spotifyService;

    public AuthController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("login")
    public String spotifyLogin() {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true)
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    @GetMapping(value = "get-user-code/")
    public void getSpotifyCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set access and refresh token 
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(spotifyApi.getAccessToken());
        response.sendRedirect("http://localhost:3000/nav");
    }
}
