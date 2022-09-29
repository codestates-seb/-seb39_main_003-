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
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ItemData from '../../dummytest/ItemData';

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

  .item_box {
    width: 20%;
    height: 15rem;
    border: 1px solid gray;
    display: flex;
    flex-direction: column;
    justify-content: end;
    align-items: center;
    overflow-x: hidden;
    cursor: pointer;
    margin: 15px;
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
    border: 2px solid red;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
  }

.item_list {
    margin: 0px 10px 0px 10px;
  }

  .test1 {
    width: 100%;
    display: flex;
    align-items: center;
    font-size: 1.1rem;
    font-weight: 500;
  }
  .test2 {
    width: 100%;
    /* border: 1px solid black; */
    font-size: 1.2rem;
    font-weight: bold;
  }

  .image {
    width: 16rem;
    height: 10rem;
    position: relative;
    bottom: 5%;
    border: 1px solid lightgray;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

function Shopping() {

  const navigate = useNavigate();


  const [click, setClick] = useState(false);

  const [select, setSelect] = useState(undefined);

  // const [eat, setEat] = useState(false);
  // const [health, setHealth] = useState(false);
  // const [other, setOther] = useState(false);

  const [test, setTest] = useState(ItemData);

  // 유저 정보 조회 로직 (GET)

  // const [info, setInfo] = useState(undefined);

  // useEffect(() => {
  //   fetch(`http://211.58.40.128:8080/api/v1/member/000001`)
  //   .then(res => res.json())
  //   .then(res => {
  //     setInfo(res)
  //   })
  // } , [])


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
          <span className='category' onClick={() => {
            select === '먹거리' ? setClick(!click) : setClick(true)
            setSelect('먹거리')
            }}>먹거리</span>
          {click && select === '먹거리' ? <EatDropdown /> : undefined}
          </div>
          
          <div>
          <span className='category' onClick={() => {
            select === '건강관리' ? setClick(!click) : setClick(true)
            setSelect('건강관리')
            }}>건강관리</span>
          {click && select === '건강관리' ? <HealthDropdown /> : undefined}
          </div>
          
          <div>
          <span className='category' onClick={() => {
            select === '각종 용품' ? setClick(!click) : setClick(true)
            setSelect('각종 용품')
            }}>각종 용품</span>
          {click && select === '각종 용품' ? <OtherDropdown /> : undefined}
          </div>
      </div>
      {/* 카테고리 */}

      {/* 인기상품 */}
      <div className="famousBox">
        <span className="famous">이달의 인기상품</span>
      </div>


          <div className="item_list_box">
            {test.map((item, idx) => {
              return (

                <div key={idx} className='item_box'>

                    <div className='image'>
                        <div>{item.image}</div>
                    </div>

                    <div className='test1'>
                        <div className="item_list">{item.name}</div>
                    </div>

                    <div className='test2'>
                        <div className="item_list">{item.price}원</div>
                    </div>

                </div>
              )
            })}
          </div>

      {/* 인기상품 */}

    </Wrapper>
  )
}

export default Shopping;