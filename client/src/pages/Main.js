import React from "react";
import styled from 'styled-components';
import Image1 from '../assets/dog1.png';
import Image2 from '../assets/dog2.png';
import Image3 from '../assets/dog3.png';
import Hospital from '../assets/hospital.png';
import Shopping from '../assets/shopping.png';
import Community from '../assets/community.png';
import Notice from '../assets/notice.png';
import FAQ from '../assets/faq.png';
import Carousel from "nuka-carousel";
import { Link } from "react-router-dom";


const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  justify-content: center;
  overflow-x: hidden;

  .windowBox {
    width: 100%;
    height: 600px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .window {
  width: 67%;
  height: 500px;
  overflow: hidden;
};

.flexboxImage {
  width: 100%;
  height: 500px;
  background-size: cover;
  /* background-position: 50% 50%;
  background-size: contain;
  background-repeat: no-repeat;
  flex: none; */
}

.tabBox {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.tabs {
  margin: 20px 0px 20px 0px;
  cursor: pointer;
  transition: .3s;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;

  &:hover {
    width: 93%;
    height: 320px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-size: cover;
    font-size: 70px;
    font-weight: bold;
    color: white;
  }
}

.hospital {
  width: 90%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-image: url(${Hospital});
  font-size: 50px;
  font-weight: bold;
  color: white;
}

.shopping {
  width: 90%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-image: url(${Shopping});
  font-size: 50px;
  font-weight: bold;
  color: white;
}

.community {
  width: 90%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-image: url(${Community});
  font-size: 50px;
  font-weight: bold;
  color: white;
}

.notice {
  width: 90%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-image: url(${Notice});
  font-size: 50px;
  font-weight: bold;
  color: white;
}

.FAQ {
  width: 90%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-image: url(${FAQ});
  font-size: 50px;
  font-weight: bold;
  color: white;
  margin-bottom: 200px;
}

`;

const StyledLink = styled(Link)`
  width: 100%;
  color: white;
  text-decoration: none;
  transition: .3s;
  display: flex;
  justify-content: center;

`;

function Main() {

  return (
    <Wrapper>
      {/* 캐러셀 컴포넌트 */}
    <div className="windowBox">
      <div className="window">
          <Carousel>
              <img className="flexboxImage" alt="image1" src={Image1} />
              <img className="flexboxImage" alt="image2" src={Image2} />
              <img className="flexboxImage" alt="image3"src={Image3} />
          </Carousel>
      </div>
    </div>
      {/* 캐러셀 컴포넌트 */}
  

        {/* 메뉴 탭 컴포넌트 */}
      <div className="tabBox">

          <div className="hospital tabs">
            동물병원
          </div>

            <StyledLink to="/shopping">
              <div className="shopping tabs">
                쇼핑
              </div>
            </StyledLink>

            <StyledLink to="/community">
              <div className="community tabs">
                커뮤니티
              </div>
            </StyledLink>

            <StyledLink to="/notice">
              <div className="notice tabs">
                공지사항
              </div>
            </StyledLink>

            <StyledLink to="/FAQ">
              <div className="FAQ tabs">
                FAQ
              </div>
            </StyledLink>

    </div>
        {/* 메뉴 탭 컴포넌트 */}

    </Wrapper>
  )
}

export default Main;