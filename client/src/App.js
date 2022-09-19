import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
// import Test from "./pages/test";
import Main from "./pages/Main";
// import Footer from "./components/Footer";
import Shopping from "./pages/Shopping/Shopping";
import Community from "./pages/Community/Community";
import Info from "./pages/Community/Info";
import Hospital from "./pages/Community/Hospital";
import Protect from "./pages/Community/Protect";
import Lost from "./pages/Community/Lost";
import Notice from "./pages/Notice";
import FAQ from "./pages/FAQ";

function App() {
  return (
    <div className="App">
      
      <Router>
          <Nav />
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/shopping" element={<Shopping />} />
                <Route path="/community" element={<Community />} />
                <Route path="/notice" element={<Notice />} />
                <Route path="/FAQ" element={<FAQ />} />

                <Route path="/community/walk" element={<Community />} />
                <Route path="/community/info" element={<Info />} />
                <Route path="/community/hospital" element={<Hospital />} />
                <Route path="/community/protect" element={<Protect />} />
                <Route path="/community/lost" element={<Lost />} />
            </Routes>
      </Router>

    </div>
  );
}

export default App;


// nav