import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import Test from "./pages/test";

function App() {
  return (
    <div className="App">
      
      <Router>
        <Test />
        {/* <Nav /> */}
        {/* <Footer /> */}
      </Router>

    </div>
  );
}

export default App;
