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
`;

function Shopping() {

  const [eat, setEat] = useState(false);
  const [health, setHealth] = useState(false);
  const [other, setOther] = useState(false);

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

    </Wrapper>
  )
}

export default Shopping;