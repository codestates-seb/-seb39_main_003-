import React from 'react'
import styled from 'styled-components'

import SocialLogin from '../../components/SocialLogin'

const Wrapper = styled.div`
box-sizing: border-box;

.buyBackground{
  width: 100vw;
  padding: 20px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #f9f9f9;
}
// 주문 완료 되었습니다.
.buyCompleteText{
  width: 100vw;
  margin-top: 80px;
  margin-bottom: 80px;

  font-weight: 700;
  font-size: xx-large;
  text-align: center;
}
// 배송지
.buyAddress{
  width: 90vw;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;
  padding: 0px 200px;
  margin-bottom: 50px;
  
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.bAd1{
  padding: 100px 0px;
  margin-right: 50px;

  font-size: large;
  font-weight: 500;
}
.bAd2{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
// 주문 번호
.buyNumber{
  width: 90vw;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;
  padding: 0px 200px;
  margin-bottom: 50px;
  
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.bNum1{
  padding: 20px 0px;
  margin-right: 50px;

  font-size: large;
  font-weight: 500;
}
.bNum2{}
// 사진
.picbox{
  margin-bottom: 50px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-self: center;
}
.pic1{
  width: 60vw;
  
}
// 주문 금액
.buyAmount{
  width: 90vw;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;
  
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.bAm1{
  padding: 20px 0px;
  margin-right: 50px;

  font-size: larger;
  font-weight: 600;
}
.bAm2{
  margin-left: 20px;
}
`

function Buy() {
  return (
    <Wrapper>
      <div className='buyBackground'>
        <div className='buyCompleteText'>주문 완료 되었습니다.</div>
        <div className='buyAddress'>
          <div className='bAd1'>배송지</div>
          <div className='bAd2'>배송 정보 담기는 칸</div>
        </div>
        <div className='buyNumber'>
          <div className='bNum1'>주문 번호</div>
          <div className='bNum2'>주문 번호 담기는 칸</div>
        </div>
        <div className='picbox'>
          <img className='pic1' src="https://cdn.discordapp.com/attachments/1020944788419248179/1027607347897573476/carousel1.jpg"/>
        </div>
        <div className='buyAmount'>
          <div className='bAm1'>주문 금액</div>
          <div className='bAm2'>주문 금액 담기는 칸</div>
        </div>
      </div>

      <div>
        <SocialLogin></SocialLogin>
      </div>
    </Wrapper>
  )
}

export default Buy