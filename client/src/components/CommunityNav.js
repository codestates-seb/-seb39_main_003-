// import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import React from 'react';
import styled from "styled-components";
import { Link } from "react-router-dom";


const Wrapper = styled.div`

box-sizing: border-box;

// 커뮤니티 탭
.cTabTerritory {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
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

// 게시글 등록 버튼
.pbackground{
  width: 20%;
    
    padding: 5px;
    margin: 20px;
    border-radius: 20px;
    box-shadow: 1px 1px 1px lightgrey;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    
    background-color: #F9F9F9;
    font-weight: bold;
}
.postingbutton{
  padding: 5px;
  font-weight: bold;
}

// 커뮤니티 검색창
.csearchterritory{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
}
.csearchterritory > .cbackground{
    width: 20%;
    
    padding: 5px;
    margin: 20px;
    border-radius: 20px;
    box-shadow: 1px 1px 1px lightgrey;

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

const StyledLink = styled(Link)`
  color: black;
  text-decoration: none;
  display: flex;
  justify-content: center;

  &:hover {
    color: #9263FF;
  }

`;

function CommunityNav() {
  return (
    <Wrapper>
        {/* <!-- 커뮤니티탭 --> */}
        <div className="cTabTerritory">
            <div className="cBackground">
            <span className="ctabText">
              <StyledLink to="/community/walk">산책</StyledLink>
            </span>
            <span className="ctabText">
              <StyledLink to="/community/info">정보공유</StyledLink>
            </span>
            <span className="ctabText">
            <StyledLink to="/community/hospital">병원추천</StyledLink>
            </span>
            <span className="ctabText">
              <StyledLink to="/community/protect">임시보호</StyledLink>
            </span>
            <span className="ctabText">
            <StyledLink to="/community/lost">실종신고</StyledLink>
            </span>
            </div>
        </div>

        {/* <!-- 검색창 --> */}
            <div className="csearchterritory">
              {/* 게시글 등록 버튼 */}
              <div className='pbackground'>
                <StyledLink to="/community/posting" className='postingbutton'>게시글 등록</StyledLink>
              </div>
            <div className="cbackground">
                <input type="textarea" className="csearch" placeholder="검색어 입력"/>
                <svg className="csearchsvg"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" fill="rgb(146 99 255)"></path></svg>
            </div>
        </div>
    </Wrapper>
  )
}

export default CommunityNav;