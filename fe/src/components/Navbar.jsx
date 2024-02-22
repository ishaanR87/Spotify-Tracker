// Navbar.js
import React from "react";
import { Link } from "react-router-dom";
import "../styles/NavBar.css";

function Navbar() {
  return (
    <nav className="nav">
      <Link to="/home" className="home">
        Spotify Tracker
      </Link>
      <ul>
        <li>
          <Link to="/top-artists">My Top Artists</Link>
        </li>
        <li>
          <Link to="/top-songs">My Top Songs</Link>
        </li>
        <li>
          <Link to="/my-playlist">My Playlist</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
