import React from "react";

function Navbar() {
  return (
    <nav className="nav">
      <a href="/home" className="home"></a>
      <ul>
        <li>My Top Artists</li>
        <li>My Top Songs</li>
        <li>My Playlists</li>
      </ul>
    </nav>
  );
}

export default Navbar;
