package com.ishaan.spotifybackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import com.ishaan.spotifybackend.service.SpotifyService;

import se.michaelthelin.spotify.SpotifyApi;

@RestController
@RequestMapping("/api")
public class SpotifyController {

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping(value = "top-artists")
    public Artist[] getUserTopArtists() {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range("medium_term")
                .limit(50)
                .offset(5)
                .build();
        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();
        
            return artistPaging.getItems();
        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return new Artist[0];
    }

     @GetMapping(value = "top-tracks")
    public Track[] getUserTopTracks() {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
        final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
                .time_range("medium_term")
                .limit(50)
                .offset(5)
                .build();

        try {
            final Paging<Track> trackPaging = getUsersTopTracksRequest.execute();

            return trackPaging.getItems();
        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return new Track[0];
    }

    @GetMapping(value = "recommendations")
    public Track[] getRecommendations() {
        SpotifyApi spotifyApi = spotifyService.getSpotifyApi();

        // fetch top tracks
        final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
                .time_range("medium_term")
                .limit(5)
                .offset(0)
                .build();

        try {
            final List<String> seedTracks = new ArrayList<>();
        
            final Paging<Track> topTracks = getUsersTopTracksRequest.execute();

            for (Track track : topTracks.getItems()) {
                seedTracks.add(track.getId());
            }
    
            String[] seedTracksArray = seedTracks.toArray(new String[0]);

            // construct the request for recommendations using top tracks as seeds
            final GetRecommendationsRequest.Builder recommendationsBuilder = spotifyApi.getRecommendations().limit(30); // limit the number of recommended tracks

            // add each track ID as a seed
            for (String trackId : seedTracksArray) {
                recommendationsBuilder.seed_tracks(trackId);
            }

            // build the recommendations request
            final GetRecommendationsRequest getRecommendationsRequest = recommendationsBuilder.build();

            final Track[] recommendedTracks = getRecommendationsRequest.execute().getTracks();

            return recommendedTracks;
        } catch (Exception e) {
            System.out.println("Something went wrong while fetching recommendations: " + e.getMessage());
            return new Track[0]; 
        }
    }
}

