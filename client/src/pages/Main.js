import React, {useState, useEffect} from "react";
import styled from 'styled-components';
// import Main1 from '../assets/위풍댕댕 메인1.png';
// import Image1 from '../assets/dog1.png';
// import Image2 from '../assets/dog2.png';
// import Image3 from '../assets/dog3.png';
import Carousel1 from '../assets/carousel1.png';
import Carousel2 from '../assets/carousel2.png';
import Carousel3 from '../assets/carousel3.jpg';
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

  // useEffect(() => {
    let test = window.location.search;
    console.log(test);

    let param = new URLSearchParams(test);
    console.log(param);

    let access = param.get("access_token");
    console.log(access);

    let refresh = param.get("refresh_token");
    console.log(access);

    if(access !== null) {
      sessionStorage.setItem("accessToken", `${access}`)
      sessionStorage.setItem("refreshToken", `${refresh}`)
      document.location.href = '/'
    }

    // ** 로그인 랜딩 페이지 사용 권장 **

    // else {
    //   sessionStorage.removeItem("accessToken");
    // }
  // }, []);

  // window.Buffer = window.Buffer || require("buffer").Buffer;

  // const base64Payload = access.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
  // const payload = Buffer.from(base64Payload, "base64");
  // const result = JSON.parse(payload.toString());
  // console.log(result);

  // const [info, setInfo] = useState([]);

  // useEffect(() => {
  //   fetch(`http://211.58.40.128:8080/api/v1/member/$%7Bresult.memberId%7D%60`)
  //     .then((res) => res.json())
  //     .then((res) => {
  //       setInfo(res);
  //       console.log(res)
  //     });
  // }, []);

  return (
    <Wrapper>


      {/* 캐러셀 컴포넌트 */}
    <div className="windowBox">
      <div className="window">
          <Carousel>
            <img className="flexboxImage" alt="image1" src={Carousel1} />
            <img className="flexboxImage" alt="image2" src={Carousel2} />
            <img className="flexboxImage" alt="image3"src={Carousel3} />
          </Carousel>
      </div>
    </div>
      {/* 캐러셀 컴포넌트 */}
  
        {/* <button>
          <a href='http://ec2-15-165-63-80.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google'>구글 로그인</a>
        </button> */}

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