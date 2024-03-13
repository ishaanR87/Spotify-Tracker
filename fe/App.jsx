import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./src/components/Login";
import TopArtists from "./src/components/TopArtists";
import NavBar from "./src/components/Navbar";
import TopTracks from "./src/components/TopTracks";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/top-artists" element={<TopArtists />} />
        <Route path="/nav" element={<NavBar />} />
        <Route path="/top-tracks" element={<TopTracks />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
