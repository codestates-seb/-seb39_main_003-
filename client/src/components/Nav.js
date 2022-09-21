/* eslint-disable jsx-a11y/alt-text */
import styled from 'styled-components';
import { FaUserAlt } from "react-icons/fa";
import { BsSearch } from "react-icons/bs";
import { Link } from "react-router-dom";
import React, { useState } from 'react';
import UserInfo from './UserInfo';

const Wrapper = styled.div `
  /* position: fixed;
  z-index: 1; */

  .headerBox {
    width: 100%;
    height: 100px;
    display: flex;
    flex-direction: row;
    align-items: center;
    line-height: 100px;
    /* border: 3px solid green; */
  }
  
  .navbar {
    width: 100%;
    height: 150px;
    /* border: 1px solid red; */
    display: flex;
    justify-content: space-around;
    background-color: #9263FF;
    color: white;
    font-size: 35px;
    font-weight: bold;
  }

  .navlist {
    display: flex;
    justify-content: center;
    line-height: 50px;
    border-radius: 15px;
    transition: .3s;
    cursor: pointer;
    text-decoration: none;
  }

  .navlist:hover {
    display: flex;
    justify-content: center;
    color: #FEDB39;
    cursor: pointer;
  }

  .logo {
    display: flex;
    justify-content: center;
    width: 168px;
    height: 100px;
    background-color: #EEF1FF;
    border-radius: 20px;
    margin-right: 20px;
    cursor: pointer;
  }

  .search {
    width: 518px;
    height: 50px;
    /* border: 1px solid red; */
    border-radius: 15px;
    background-color: #F0F0F0;
    padding-left: 20px;
  }

  .profile {
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 100px;
    /* border: 1px solid blue; */
    position: absolute;
    right: 0%;
    cursor: pointer;
  }

  .headerList {
    margin: 0px 30px 0px 40px;
  }

  .searchIcon {
    height: 100%;
    font-size: 24px;
    color: #ff425c;
    cursor: pointer;
    transition: .3s;
    line-height: 100%;

    &:hover {
      font-size: 30px;
    }
  }

  .search_bar {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .show-menu{
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 15%;
  height: 500px;
  position: absolute;
  top: 10%;
  right: 0%;
  transition: 1s;
  background-color: #FEFBF6;
  border-left: 3px solid black;
  border-top: 3px solid black;
  border-bottom: 3px solid black;
  z-index: 1;
  border-radius: 5px;
}

  .hide-menu{
  width: 15%;
  height: 30%;
  position: absolute;
  left: -15%;
  transition: 1s;
  background-color: #FEFBF6;
}

  .info {
    width: 100%;
    height: 500px;
    background-color: beige;
    padding-left: 20px;
    border: 1px solid lightgray;
    cursor: pointer;
    font-size: 20px;
    font-weight: 500;
    display: flex;
    align-items: center;

    &:hover {
      background-color: #F3E0B5;
    }
  }
`;

const StyeldLink = styled(Link)`
  color: white;
  text-decoration: none;
  transition: .3s;

  &:hover {
    color: #FEDB39;
  }
`;

const Nav = () => {

  const [open, setOpen] = useState(false);

  const userButtonClick = () => {
    setOpen(!open);
  };

  return (
    <Wrapper>
      <div className="header">
          <div className="headerBox">
              <span className="logo headerList">로고</span>
              <div className="search_bar">
                  <input type="text" placeholder="어떤 상품을 찾으세요?" className="search headerList"></input>
                  <span className="searchIcon"><BsSearch /></span>
              </div>
              <span className="profile headerList" onClick={userButtonClick}><FaUserAlt /></span>
              <span className={open ? "show-menu" : "hide-menu"}>
                <span className='info'><StyeldLink to="/mypage">마이페이지</StyeldLink></span>
                <span className='info'>장바구니</span>
                <span className='info'>고객센터</span>
                <span className='info'>로그아웃</span>
              </span>
          </div>
      </div>
      
      <div className="navbar">
        <span className="navlist"><StyeldLink to="/">Home</StyeldLink></span>
        <span className="navlist">동물병원</span>
        <span className="navlist"><StyeldLink to="/shopping">쇼핑</StyeldLink></span>
        <span className="navlist"><StyeldLink to="/community">커뮤니티</StyeldLink></span>
        <span className="navlist"><StyeldLink to="/notice">공지사항</StyeldLink></span>
        <span className="navlist"><StyeldLink to="/FAQ">FAQ</StyeldLink></span>
      </div>
    </Wrapper>
  )
}

export default Nav;