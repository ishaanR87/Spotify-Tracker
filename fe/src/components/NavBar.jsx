import React from "react";
import { Link } from "react-router-dom";
import "../styles/NavBar.css";

function NavBar() {
  const handleLogout = () => {
    fetch("https://localhost:8080/api/logout", {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          // logout was successful
          window.location.href = "/";
        } else {
          // handle logout failure
          console.error("Logout failed:", response.statusText);
        }
      })
      .catch((error) => {
        console.error("Error during logout:", error);
      });
  };

  return (
    <nav className="nav">
      <Link to="/home" className="home">
        Home
      </Link>
      <ul>
        <li>
          <Link to="/top-artists">My Top Artists</Link>
        </li>
        <li>
          <Link to="/top-tracks">My Top Songs</Link>
        </li>
        <li>
          <Link to="/recommendations">My Recommended Songs</Link>
        </li>
        <li>
          <Link to="/" onClick={handleLogout}>
            Logout
          </Link>
        </li>
      </ul>
    </nav>
  );
}

export default NavBar;
