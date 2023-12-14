import React from "react";
import "../styles/Navbar.css";

function Navbar() {
  return (
    <nav className="nav">
      <a href="/home" className="home">
        Spotify Tracker
      </a>
      <ul>
        <li>
          <a href="/top-artists">My Top Artists</a>
        </li>
        <li>
          <a href="/top-songs">My Top Songs</a>
        </li>
        <li>
          <a href="/my-playlist">My Playlist</a>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
