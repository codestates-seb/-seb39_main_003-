import styled from 'styled-components';
import React from 'react'
import { Link } from "react-router-dom";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import CommunityNav from '../../components/CommunityNav';
import Info from "../Community/Info";
import Hospital from "../Community/Hospital";
import Protect from "../Community/Protect";
import Lost from "../Community/Lost";

const Wrapper = styled.div`

box-sizing: border-box;

// 게시글 목록
.cpostterritory{
    
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 3px solid blue;
}
.cpostterritory > .cbackground{
    padding: 30px 10px;
    margin: 20px;
    border-radius: 20px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #F9F9F9;
}
.cposttop{
    width: 90vw;
    padding: 10px 10px;
    margin-bottom: 30px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: rgb(146 99 255);
}
.cposttext{
    color: #F9F9F9;
    font-weight: bold;
}
.cpostmiddle{
    width: 90vw;
    padding: 10px 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.cpost{
    width: 90vw;
    padding: 10px 10px;
    border-radius: 20px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: #EEF1FF;
}
.title{}
.content{
    text-decoration: none;
    color: #000000;
}
.user{}
.cpostbottom{
    padding: 10px;
    margin-top: 10px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
.cpostpage{
    padding: 5px;

    text-decoration: none;
    color: #000000;
}
`;

function Community() {
  return (
    <Wrapper>
      {/* <!-- 커뮤니티탭 -->
        <div className="cTabTerritory">
          <div className="cBackground">
            <a href="/community/work" className="ctabText">산책</a>
            <a href="/community/info" className="ctabText">정보공유</a>
            <a href="/community/hospital" className="ctabText">병원추천</a>
            <a href="/community/protect" className="ctabText">임시보호</a>
            <a href="/community/lost" className="ctabText">실종신고</a>
          </div>
        </div>

        <!-- 검색창 -->
         <div className="csearchterritory">
            <div className="cbackground">
                <input type="textarea" className="csearch" placeholder="검색어 입력"/>
                <svg className="csearchsvg"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" fill="rgb(146 99 255)"></path></svg>
            </div>
        </div> */}
        {/* <Router> */}
            <CommunityNav />
                <Routes>
                    <Route path="/community/walk" element={<Community />} />
                    <Route path="/community/info" element={<Info />} />
                    <Route path="/community/hospital" element={<Hospital />} />
                    <Route path="/community/protect" element={<Protect />} />
                    <Route path="/community/lost" element={<Lost />} />
                </Routes>
        {/* </Router> */}

        {/* <!-- 게시글 목록 --> */}
        <div className="cpostterritory">
            <div className="cbackground">
                <div className="cposttop">
                    <span className="cposttext">글 제목</span>
                    <span className="cposttext">내용</span>
                    <span className="cposttext">작성자</span>
                </div>
                <div className="cpostmiddle">
                    <div className="cpost">
                        <span className="title">글 제목</span>
                        <a href="/" className="content">내용</a>
                        <span className="user">작성자</span>
                    </div>
                </div>
                
                <div className="cpostbottom">
                    <a href="/" className="cpostpage">1</a>
                    <a href="/" className="cpostpage">2</a>
                    <a href="/" className="cpostpage">3</a>
                    <a href="/" className="cpostpage">4</a>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default Community;