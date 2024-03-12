import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function TopArtists() {
  const [userTopArtists, setUserTopArtists] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8080/api/top-artists")
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setUserTopArtists(data);
      })
      .catch(error => {
        console.error("Error fetching top artists:", error);
      });
  }, []);

  return (
    <div>
      {userTopArtists ? (
        userTopArtists.map((artistResult) => (
          <h1 key={artistResult.name}>{artistResult.name}</h1>
        ))
      ) : (
        <h1>LOADING...</h1>
      )}
    </div>
  );
}

export default TopArtists;