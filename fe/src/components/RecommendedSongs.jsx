import React, { useEffect, useState } from "react";
import Layout from "./Layout";
import "../styles/RecommendedSongs.css";

function RecommendedSongs() {
  const [recommendedSongs, setRecommendedSongs] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/recommendations")
      .then((response) => response.json())
      .then((data) => {
        console.log("Fetched data:", data);
        setRecommendedSongs(data);
      })
      .catch((error) => {
        console.error("Error fetching recommended songs", error);
      });
  }, []);

  console.log("Recommended songs:", recommendedSongs);

  return (
    <Layout>
      <div>
        <h1>Recommended Songs</h1>
        <div className="recommended-songs-container">
          {recommendedSongs.map((song) => (
            <div key={song.id} className="song-card">
              <img src={song.album.images[0].url} alt={song.album.name} />
              <div className="song-details">
                <h3 style={{ color: "white", fontFamily: "Helvetica" }}>
                  {song.name}
                </h3>
                <h4 style={{ color: "white", fontFamily: "Helvetica" }}>
                  {song.artists[0].name}
                </h4>
                <h5 style={{ color: "grey", fontFamily: "Helvetica" }}>
                  Album: {song.album.name}
                </h5>
              </div>
            </div>
          ))}
        </div>
      </div>
    </Layout>
  );
}

export default RecommendedSongs;
