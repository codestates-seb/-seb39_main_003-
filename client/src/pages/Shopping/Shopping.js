/* eslint-disable no-lone-blocks */
import React, { useState } from 'react';
import styled from 'styled-components';
import Carousel from "nuka-carousel";
import Image1 from '../../assets/dog1.png';
import Image2 from '../../assets/dog2.png';
import Image3 from '../../assets/dog3.png';
import EatDropdown from './EatDropdown';
import HealthDropdown from './HealthDropdown';
import OtherDropdown from './OtherDropdown';

const Wrapper = styled.div`

  @font-face {
    font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }

  @font-face {
    font-family: 'InfinitySans-RegularA1';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

  .windowBox {
    width: 100%;
    height: 550px;
    display: flex;
    justify-content: center;
    /* border: 3px solid red; */
  }

  .window {
  width: 67%;
  height: 500px;
  overflow: hidden;
  /* border: 3px solid blue; */
};

.flexboxImage {
  width: 100%;
  height: 500px;
  background-size: cover;
}

.categories {
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  /* border: 3px solid green; */
}

.category {
  width: 200px;
  height: 80px;
  background-color: #E1FFEE;
  display: flex;
  justify-content: center;
  line-height: 80px;
  border-radius: 15px;
  font-weight: bold;
  font-size: 25px;
  transition: .3s;
  cursor: pointer;
  font-family: Cafe24Ssurround;


  &:hover {
  width: 230px;
  /* height: 85px; */
  font-weight: bold;
  font-size: 30px;
  background-color: #A5F1E9;
  display: flex;
  justify-content: center;
  line-height: 85px;
  border-radius: 15px;
  }
}

.famousBox {
    margin-top: 50px;
    width: 100%;
    height: 100px;
    background-color: beige;
    display: flex;
    justify-content: center;
    font-style: italic;
  }

  .famous {
    width: 100%;
    height: 100px;
    background-color: beige;
    display: flex;
    justify-content: center;
    line-height: 100px;
    font-size: 40px;
    font-weight: bold;
    font-family: InfinitySans-RegularA1;
  }

  .item_list_box {
    width: 100%;
    height: 100%;
    border: 2px solid green;
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
  }

.item_list {
    margin: 80px 10px 80px 10px;
    width: 20%;
    height: 200px;
    background-color: skyblue;
    border: 2px solid royalblue;
    display: flex;
    justify-content: center;
    line-height: 200px;
    transition: .3s;
    cursor: pointer;

    &:hover {
      margin: 80px 10px 80px 10px;
      width: 23%;
      height: 220px;
      background-color: skyblue;
      border: 2px solid royalblue;
      display: flex;
      justify-content: center;
      line-height: 200px;
    }
  }
`;

function Shopping() {

  const [eat, setEat] = useState(false);
  const [health, setHealth] = useState(false);
  const [other, setOther] = useState(false);
  // const [test, setTest] = useState("");

  // useEffect(() => {
  //   fetch("http://ec2-52-79-180-182.ap-northeast-2.compute.amazonaws.com:8080/api/v1/member")
  //   .then(res => res.json)
  //   .then(res => {
  //     setTest(res)
  //   })
  // }, [])

  const eatButtonClick = () => {
    setEat(!eat)
  };

  const healthButtonClick = () => {
    setHealth(!health)
  };

  const otherButtonClick = () => {
    setOther(!other)
  };

  return (
    <Wrapper>
      {/* 캐러셀 */}
      <div className="windowBox">
        <div className="window">
          <Carousel>
              <img className="flexboxImage" alt="image1" src={Image1} />
              <img className="flexboxImage" alt="image2" src={Image2} />
              <img className="flexboxImage" alt="image3"src={Image3} />
          </Carousel>
        </div>
      </div>
      {/* 캐러셀 */}


      {/* 카테고리 */}
      <div className='categories'>
          <div>
          <span className='category' onClick={eatButtonClick}>먹거리</span>
          {eat && <EatDropdown />}
          </div>
          
          
          <div>
          <span className='category' onClick={healthButtonClick}>건강관리</span>
          {health && <HealthDropdown />}
          </div>
          
          <div>
          <span className='category' onClick={otherButtonClick}>각종 용품</span>
          {other && <OtherDropdown />}
          </div>
      </div>
      {/* 카테고리 */}

      {/* 인기상품 */}
      <div className="famousBox">
        <span className="famous">이달의 인기상품</span>
      </div>

      <div class="item_list_box">
        <span className="item_list">여기는</span>
        <span className="item_list">데이터를</span>
        <span className="item_list">받아와야</span>
        <span className="item_list">할듯</span>

        <span className="item_list">여기는</span>
        <span className="item_list">데이터를</span>
        <span className="item_list">받아와야</span>
        <span className="item_list">할듯</span>
      </div>
      {/* 인기상품 */}

    </Wrapper>
  )
}

export default Shopping;