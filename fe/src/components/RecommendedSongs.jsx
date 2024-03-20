import React, { useEffect, useState } from "react";
import Layout from "./Layout";

function RecommendedSongs() {
  const [recommendedSongs, setRecommendedSongs] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/recommendations")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setRecommendedSongs(data);
      })
      .catch((error) => {
        console.error("Error fetching recommended songs", error);
      });
  }, []);

  return (
    <Layout>
    <div>
      <h1>Recommended Songs</h1>
      <ul>
        {recommendedSongs.map((song) => (
          <li key={song.id}>{song.name}</li>
        ))}
      </ul>
    </div>
    </Layout>
  );
}

export default RecommendedSongs;
