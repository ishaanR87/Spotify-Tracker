import React from "react";
import NavBar from "./NavBar"; 
import "../styles/Home.css";

function Home() {
  return (
    <div className="home-container">
      <NavBar />
      <div className="content">
        <h1>Welcome to Spotify Tracker!</h1>
        <p>Explore your top artists and tracks.</p>
      </div>
    </div>
  );
}

export default Home;