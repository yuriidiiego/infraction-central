import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./components/NavBar";
import FooterBar from "./components/FooterBar";
import Home from "./pages/Home";
import About from "./pages/About";

function App() {
  return (
    <>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
        </Routes>
        <FooterBar />
      </BrowserRouter>
    </>
  );
}

export default App;
