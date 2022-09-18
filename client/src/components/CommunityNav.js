import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React from 'react';
import styled from "styled-components";

const Wrapper = styled.div`

box-sizing: border-box
background-color: black;

// 커뮤니티 탭
.cTabTerritory{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border: 3px solid red;
}
.cTabTerritory > .cBackground{
  width: 100%;
  padding: 10px 10px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  font-weight: bold;
  background-color: #EEF1FF;
}
.ctabText{
  font-size: large;
  color: #000000;
  text-decoration: none;
}
.ctabText:hover{
  color: rgb(146 99 255);
}

// 커뮤니티 검색창
.csearchterritory{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    border: 3px solid green;
}
.csearchterritory > .cbackground{
    width: 20%;
    
    padding: 5px;
    margin: 20px;
    border-radius: 20px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    background-color: #F9F9F9;
}
.csearch{
    width: 15vw;
    padding: 3px;
    margin: 3px;
    border-style: none;

    text-align: center;
    font-size: 15px;
}
.csearchsvg{
    width: 25px;
    height: 25px;
}
`

function CommunityNav() {
  return (
    <Wrapper>
        {/* <!-- 커뮤니티탭 --> */}
        <div className="cTabTerritory">
            <div className="cBackground">
            <a href="/community/work" className="ctabText">산책</a>
            <a href="/community/info" className="ctabText">정보공유</a>
            <a href="/community/hospital" className="ctabText">병원추천</a>
            <a href="/community/protect" className="ctabText">임시보호</a>
            <a href="/community/lost" className="ctabText">실종신고</a>
            </div>
        </div>

        {/* <!-- 검색창 --> */}
            <div className="csearchterritory">
            <div className="cbackground">
                <input type="textarea" className="csearch" placeholder="검색어 입력"/>
                <svg className="csearchsvg"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" fill="rgb(146 99 255)"></path></svg>
            </div>
        </div>
    </Wrapper>
  )
}

export default CommunityNav;