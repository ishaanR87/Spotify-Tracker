package com.ishaan.spotifybackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import com.ishaan.spotifybackend.service.SpotifyService;
import se.michaelthelin.spotify.SpotifyApi;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final SpotifyService spotifyService;

    @Autowired
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

    @GetMapping(value = "get-user-code")
    public String getSpotifyCode(@RequestParam("code") String userCode, HttpServletResponse response)
            throws IOException {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            response.sendRedirect("http://localhost:3000/top-artists");
            return spotifyApi.getAccessToken();
        } catch (Exception e) {
            return "Authorization Failed: " + e.getMessage();
        }
    }
}