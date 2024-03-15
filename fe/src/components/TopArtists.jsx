import React, { useEffect, useState } from "react";
import "../styles/TopArtists.css";

function TopArtists() {
  const [topArtists, setTopArtists] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/api/top-artists")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setTopArtists(data);
      })
      .catch((error) => {
        console.error("Error fetching top artists:", error);
      });
  }, []);

  return (
    <div className="artists-grid">
      {topArtists ? (
        topArtists.map((artist) => (
          <div key={artist.name} className="artist-card">
            <img src={artist.images[0].url} alt={artist.name} />
            <div className="card-body">
              <h5 className="card-title">{artist.name}</h5>
            </div>
          </div>
        ))
      ) : (
        <h1>Retrieving...</h1>
      )}
    </div>
  );
}

export default TopArtists;
