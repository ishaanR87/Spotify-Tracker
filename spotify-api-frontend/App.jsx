import Login from "./src/components/Login";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./src/components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Navbar />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
