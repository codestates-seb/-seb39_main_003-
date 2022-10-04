import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'

const Wrapper = styled.div`
box-sizing: border-box;
.orderterritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #f9f9f9;
}
// 주문 내역 텍스트
.orderBackground{
  width: 100%;
  padding: 20px;
  margin: 20px;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}
.ordertext{  
  margin: 20px;
  padding: 20px;  

  font-size: x-large;
  font-weight: bolder;
  text-align: right;
}
// 내 주문 목록
.myorderlistBackground{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
// 주문 목록 탭
.myordertabBackground{
  width: 90vw;
  padding: 5px;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.myordertab{
  padding: 10px;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
// 주문 목록 본문
.myorderlistBox{
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
.myorderimg{}
.myordername{}
.myorderstate{}
`

function Order() {
  return (
    <Wrapper>
      <div className='orderterritory'>
        {/* 주문내역 텍스트 */}
        <div className='orderBackground'>
          <div className='ordertext'>내 주문 내역</div>
        </div>
        {/* 내 주문 목록 */}
        <div className='myorderlistBackground'>
          {/* 주문 목록 탭 */}
          <div className='myordertabBackground'>
              <span className='myordertab'>상품정보</span>
              <span className='myordertab'>주문상태</span>
          </div>
          {/* 주문목록 본문 */}
          <div className='myorderlistBox'>
              <img className='myorderimg'/>
              <span className='myordername'>상품이름</span>
              <span className='myorderstate'>주문상태</span>
          </div>
        </div>
      </div>
    </Wrapper>
  )
}

export default Order