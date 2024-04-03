import React, { useEffect, useState } from "react";
import Layout from "./Layout";
import "../styles/TopArtists.css";

function TopArtists() {
  const [topArtists, setTopArtists] = useState();

  useEffect(() => {
    fetch("https://localhost:8080/api/top-artists")
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
    <Layout>
      <div className="artists-grid">
        {topArtists ? (
          topArtists.map((artist) => (
            <div key={artist.name} className="artist-card">
              <img src={artist.images[0].url} alt={artist.name} />
              <div className="card-body">
                <h5
                  className="card-title"
                  style={{ color: "white", fontFamily: "Helvetica" }}
                >
                  {artist.name}
                </h5>
              </div>
            </div>
          ))
        ) : (
          <h1>Retrieving...</h1>
        )}
      </div>
    </Layout>
  );
}

export default TopArtists;
