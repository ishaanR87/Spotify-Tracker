import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./src/components/Login";
import TopArtists from "./src/components/TopArtists"; // Adjust the path accordingly

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/top-artists" element={<TopArtists />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;