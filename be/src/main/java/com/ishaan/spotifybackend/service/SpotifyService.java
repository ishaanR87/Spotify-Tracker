package com.ishaan.spotifybackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

@Service
public class SpotifyService {

    private final SpotifyApi spotifyApi;

    public SpotifyService(
            @Value("${clientId}") String clientId,
            @Value("${clientSecret}") String clientSecret,
            @Value("${redirectUri}") String redirectUri
    ) {
        try {
            this.spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectUri(SpotifyHttpManager.makeUri(redirectUri))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SpotifyApi instance: " + e.getMessage(), e);
        }
    }

    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}