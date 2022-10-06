/* eslint-disable no-use-before-define */
/* eslint-disable jsx-a11y/alt-text */
import styled from 'styled-components';
import { FaUserAlt } from "react-icons/fa";
import { BsSearch } from "react-icons/bs";
import { Link } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ShoppingCart from '../pages/Shopping/images/icon-shopping-cart.svg';


const Wrapper = styled.div `

@font-face {
    font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  
  .headerBox {
    width: 100%;
    height: 100px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    line-height: 100px;
    /* border: 3px solid green; */
  }

  .memberBox {
    width: 61%;
    /* border: 1px solid red; */
    display: flex;
    justify-content: right;
    padding-right: 50px;
  }

  .member {
    width: 6vw;
    height: 4vh;
    line-height: 4vh;
    border: 1px solid lightgray;
    background-color: #EEEEEE;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    margin-right: 10px;
    font-size: 17px;
    cursor: pointer;

    &:hover {
      font-weight: bold;
      background-color: #CFD2CF;
    }
  }
  
  .navbar {
    height: 7vh;
    /* border: 1px solid red; */
    display: flex;
    justify-content: space-around;
    background-color: #9263FF;
    color: white;
    font-size: 3vmin;
    font-weight: bold;
  }

  .navlist {
    display: flex;
    justify-content: center;
    align-items: center;
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
    /* width: 6vw;
    height: 4vh;
    line-height: 4vh; */
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
    font-size: 25px;
    cursor: pointer;
  }

  .headerList {
    margin: 0px 30px 0px 40px;
  }

  .searchIcon {
    height: 100%;
    font-size: 3.4vmin;
    color: #ff425c;
    cursor: pointer;
    transition: .3s;
    line-height: 100%;

    &:hover {
      font-size: 4vmin;
    }
  }

  .search_bar {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .info {
    width: 100%;
    height: 500px;
    background-color: beige;
    padding-left: 20px;
    border: 1px solid lightgray;
    cursor: pointer;
    font-size: 2vmin;
    font-weight: 500;
    display: flex;
    align-items: center;

    &:hover {
      background-color: #F3E0B5;
    }
  }

  .shoppingCart {
    margin-left: 20px;
    padding-right: 30px;
  }

  .welcome {
    padding-right: 40px;
    font-size: 1.5rem;
    color: #9263FF;
    font-family: Cafe24Ssurround;
  }
`;

const StyledLink = styled(Link)`
  color: white;
  text-decoration: none;
  transition: .3s;

  &:hover {
    color: #FEDB39;
  }
`

;

const Nav = () => {

  const token = sessionStorage.getItem('accessToken');
  const realToken = token.slice(7)
  // console.log(realToken)
  
  window.Buffer = window.Buffer || require("buffer").Buffer;
  
  const base64Payload = realToken.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
  const payload = Buffer.from(base64Payload, 'base64'); 
  const result = JSON.parse(payload.toString())
  // console.log(result);
  // console.log(result.memberId)

    const [info, setInfo] = useState([]);
    
    useEffect(() => {
      fetch(`http://211.58.40.128:8080/api/v1/member/${result.memberId}`)
      .then(res => res.json())
      .then(res => {
        setInfo(res)
        // console.log(res)
      })
    } , [])

  const navigate = useNavigate();

  const [open, setOpen] = useState(false);

  return (
    <Wrapper>
          <div className="headerBox">
              <span className="logo headerList">
                {/* 로고 */}
                <img className="logoImage" alt="logo" src="https://cdn.discordapp.com/attachments/1020944788419248179/1026869818810437683/logo.png"
                onClick={() => {
                  navigate('/');
                }}/>
              </span>
              <div className="search_bar">
                  <input type="text" placeholder="어떤 상품을 찾으세요?" className="search headerList"></input>
                  <span className="searchIcon"><BsSearch /></span>
              </div>

              <div className='memberBox'>

                <span className='welcome'>
                  어서오라개! {info.nickName}
                </span>

                <span className='shoppingCart profile'>
                  <img src={ShoppingCart} alt="cart" onClick={() => {
                    navigate(`/mypage/cart`)
                  }}/>
                </span>

                <span className="profile headerlist" onClick={ () => {
                  sessionStorage.getItem('accessToken') ? navigate(`/mypage`) : navigate(`/login`) }}>
                  <FaUserAlt />
                  </span>

              </div>

              {/* <span className={open ? "show-menu" : "hide-menu"}>
                <span className='info'><MyPage to="/mypage">마이페이지</MyPage></span>
                <span className='info'>장바구니</span>
                <span className='info'>고객센터</span>
                <span className='info' onClick={handleButtonLogout}>로그아웃</span>
              </span> */}
          </div>
      
      <div className="navbar">
        <span className="navlist">
          <StyledLink to="/">Home</StyledLink>
        </span>

        <span className="navlist">
          <StyledLink to="/vet">동물병원</StyledLink>
        </span>

        <span className="navlist">
          <StyledLink to="/shopping">쇼핑</StyledLink>
        </span>

        <span className="navlist">
          <StyledLink to="/community">커뮤니티</StyledLink>
        </span>

        <span className="navlist">
          <StyledLink to="/notice">공지사항</StyledLink>
        </span>
        
        <span className="navlist">
          <StyledLink to="/FAQ">FAQ</StyledLink>
        </span>
      </div>
    </Wrapper>
  )
}

export default Nav;