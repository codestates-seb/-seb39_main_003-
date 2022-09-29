import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'

const Wrapper = styled.div`
.wishlistterritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #f9f9f9;
}
// 찜 목록 텍스트
.wishBackground{
  width: 100%;
  padding: 20px;
  margin: 20px;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}
.carttext{
  margin: 20px;
  padding: 20px;  
  
  font-size: x-large;
  font-weight: bolder;
  text-align: right;
}
// 내 찜 목록
.wishlistBackground{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
// 찜 목록 탭
.wishtabBackground{
  width: 90vw;
  padding: 5px;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.wishtab{
  padding: 10px;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
// 찜 목록 본문
.wishlistBox{
  width: 90vw;
  padding: 20px;
  margin-bottom: 30px;
  border-bottom-right-radius: 10px;
  border-bottom-right-radius: 10px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;


  background-color: #EEF1FF;
}
.wishimg{}
.wishname{}
.wishprice{}
`

function Wish() {
  return (
    <Wrapper>
      <div className='wishlistterritory'>
        {/* 찜 목록 텍스트 */}
        <div className='wishBackground'>
          <span className='carttext'>찜 목록</span>
        </div>
        {/* 내 찜 목록 */}
        <div className='wishlistBackground'>
          {/* 찜 목록 탭 */}
          <div className='wishtabBackground'>
              <span className='wishtab'>상품정보</span>
          </div>
          {/* 찜 목록 본문 */}
          <div className='wishlistBox'>
              <img className='wishimg'/>
              <span className='wishname'>상품이름</span>
              <span className='wishprice'>판매가</span>
          </div>
          </div>
      </div>
    </Wrapper>
  )
}

export default Wish