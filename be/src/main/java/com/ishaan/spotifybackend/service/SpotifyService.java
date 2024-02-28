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
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(SpotifyHttpManager.makeUri(redirectUri))
                .build();
    }

    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}