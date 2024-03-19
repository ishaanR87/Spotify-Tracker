import React, { useEffect, useState } from "react";
import "../styles/TopTracks.css";
import Layout from "./Layout";

function TopTracks() {
  const [topTracks, setTopTracks] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/api/top-tracks")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setTopTracks(data);
      })
      .catch((error) => {
        console.error("Error fetching top tracks:", error);
      });
  }, []);

  return (
    <Layout>
      <div className="tracks-grid">
        {topTracks ? (
          topTracks.map((track) => (
            <div key={track.name} className="track-card">
              <img src={track.album.images[0].url} alt={track.name} />
              <div className="card-body">
                <h5 className="card-title">{track.name}</h5>
                <p className="card-text">{track.artists[0].name}</p>
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

export default TopTracks;
