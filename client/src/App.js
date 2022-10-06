import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import Main from "./pages/Main";
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
import ChangeMypage from "./pages/MyPage/ChangeMypage";
import SignUp from "./pages/MyPage/Category/SignUp";
import SignIn from "./pages/MyPage/Category/SignIn";
import Items from "./pages/Shopping/Items";
import AddProduct from "./pages/Shopping/AddProduct";
import Cart from "./pages/MyPage/Category/Cart/Cart";
import CartBody from "./pages/MyPage/Category/Cart/CartBody";
import Wish from "./pages/MyPage/Category/Wish";
import Order from "./pages/MyPage/Category/Order";
import SuccessLogin from "./components/SuccessLogin";
import Addproduct from "./pages/Shopping/AddProduct";
import { useState } from 'react';
// import FAQPost from "./pages/FAQPost";
import FAQPost from "./pages/FAQ/FAQPost";
import FAQPage from "./pages/FAQ/FAQPage";
import NoticePost from "./pages/Notice/NoticePost";
import NoticePage from "./pages/Notice/NoticePage";
import HospitalPosting from "./pages/Community/detail/HospitalPosting";
import InfoPosting from "./pages/Community/detail/InfoPosting";
import LostPosting from "./pages/Community/detail/LostPosting";
import ProtectPosting from "./pages/Community/detail/ProtectPosting";
import HospitalPost from "./pages/Community/detail/HospitalPost"
import Buy from "./pages/MyPage/Buy";
import Vet from "./pages/Vet/Vet";

function App() {

    const [cart, setCart] = useState([]);
  
    const convertPrice = (price) => {
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

  return (
    <div className="App">
      
      <Router>
          {sessionStorage.getItem('accessToken') ? <SuccessLogin /> : <Nav />}
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/shopping" element={<Shopping convertPrice={convertPrice} />} />
                <Route path="/community" element={<Community />} />
                <Route path="/notice" element={<Notice />} />
                <Route path="/FAQ" element={<FAQ />} />
                <Route path="/vet" element={<Vet />} />
                
                <Route path="/FAQ/FAQPOST" element={<FAQPost />} />
                <Route path="/FAQ/FAQPage/:boardId" element={<FAQPage />} />
                
                <Route path="/notice/noticePost" element={<NoticePost/>} />
                <Route path="/notice/noticePage/:boardId" element={<NoticePage/>} />

                <Route path="/mypage" element={sessionStorage.getItem('accessToken') ? <Mypage /> : <SignIn />} />
                <Route path="/mypage/change" element={<ChangeMypage />} />
                <Route path="/login" element={<SignIn />} />
                <Route path="/signup" element={<SignUp/>} />
                <Route path="/mypage/cart" element={<Cart cart={cart} setCart={setCart} convertPrice={convertPrice}/>} />
                <Route path="/mypage/cart" element={<CartBody cart={cart} setCart={setCart} convertPrice={convertPrice}/>} />
                <Route path="/mypage/wish" element={<Wish convertPrice={convertPrice} />} />
                <Route path="/mypage/order" element={<Order convertPrice={convertPrice} />} />
                <Route path="/mypage/buy" element={<Buy/>} />

                <Route path="/community/walk" element={<Community />} />
                <Route path="/community/info" element={<Info />} />
                <Route path="/community/hospital" element={<Hospital />} />
                <Route path="/community/protect" element={<Protect />} />
                <Route path="/community/lost" element={<Lost />} />

                <Route path="/community/posting" element={<Posting />}/>
                <Route path="/community/hospitalposting" element={<HospitalPosting/>} />
                <Route path="/community/infoposting" element={<InfoPosting/>} />
                <Route path="/community/lostposting" element={<LostPosting/>} />
                <Route path="/community/protectposting" element={<ProtectPosting/>} />

                <Route path="/community/post/:communityId" element={<Post/>}/>
                <Route path="/community/hospitalpost/:boardId" element={<HospitalPost/>}/>
                {/* <Route path="/community/infopost/:boardId" element={<InoPost/>}/>
                <Route path="/community/lostpost/:boardId" element={<LostPost/>}/>
                <Route path="/community/protectpost/:boardId" element={<Protectpost/>}/> */}

                <Route path="/shopping/add" element={<Addproduct />} />
                <Route path="/shopping/meal" element={<Meal convertPrice={convertPrice} />} />
                <Route path="/shopping/cookie" element={<Cookie convertPrice={convertPrice} />} />
                <Route path="/shopping/vita" element={<Vita />} />
                <Route path="/shopping/allVita" element={<AllVita />} />
                <Route path="/shopping/pad" element={<Pad />} />
                <Route path="/shopping/toy" element={<Toy />} />
                <Route path="/shopping/add" element={<AddProduct />} />
                <Route path="/shopping/item/:itemId" element={<Items
                convertPrice={convertPrice}
                cart={cart}
                setCart={setCart}/>} />
            </Routes>
      </Router>

    </div>
  );
}

export default App;