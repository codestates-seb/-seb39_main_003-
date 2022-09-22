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
import Meal from "./pages/Shopping/Category/Meal";
import Cookie from "./pages/Shopping/Category/Cookie";
import Vita from "./pages/Shopping/Category/Vita";
import AllVita from "./pages/Shopping/Category/AllVita";
import Pad from "./pages/Shopping/Category/Pad";
import Toy from "./pages/Shopping/Category/Toy";
import Posting from "./pages/Community/detail/Posting";
import Post from "./pages/Community/detail/Post";
import Mypage from "./pages/MyPage/Mypage";
import SignUp from "./pages/MyPage/Category/SignUp";
import SignIn from "./pages/MyPage/Category/SignIn";

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
                
                <Route path="/mypage" element={<Mypage />} />
                <Route path="/login" element={<SignIn />} />
                <Route path="/signup" element={<SignUp/>} />

                <Route path="/community/walk" element={<Community />} />
                <Route path="/community/info" element={<Info />} />
                <Route path="/community/hospital" element={<Hospital />} />
                <Route path="/community/protect" element={<Protect />} />
                <Route path="/community/lost" element={<Lost />} />
                <Route path="/community/posting" element={<Posting />}/>
                <Route path="/community/post/3" element={<Post/>}/>

                <Route path="/shopping/meal" element={<Meal />} />
                <Route path="/shopping/cookie" element={<Cookie />} />
                <Route path="/shopping/vita" element={<Vita />} />
                <Route path="/shopping/allVita" element={<AllVita />} />
                <Route path="/shopping/pad" element={<Pad />} />
                <Route path="/shopping/toy" element={<Toy />} />
            </Routes>
      </Router>

    </div>
  );
}

export default App;


// nav