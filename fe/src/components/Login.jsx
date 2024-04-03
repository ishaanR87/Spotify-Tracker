import React from "react";
import "../styles/Login.css";

function Login() {
  const handleSpotifyLogin = () => {
    fetch("https://localhost:8080/api/login")
      .then((response) => response.text())
      .then((response) => {
        window.location.replace(response);
      });
  };

  return (
    <div id="body">
      <div id="btncontainer">
        <div id="button-wrapper">
          <p>
            <strong>Please Login with Spotify.</strong>
          </p>
          <button id="loginbtn" onClick={handleSpotifyLogin}>
            Login
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
