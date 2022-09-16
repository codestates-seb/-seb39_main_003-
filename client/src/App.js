import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
// import Test from "./pages/test";
import Main from "./pages/Main";
// import Footer from "./components/Footer";
import Shopping from "./pages/Shopping";
import Community from "./pages/Community";
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
            </Routes>
      </Router>

    </div>
  );
}

export default App;


// nav