import React from "react";
import styled from "styled-components";
import { useForm } from "react-hook-form";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cart from "./Cart";

const Wrapper = styled.div`
box-sizing: border-box;
.paymentTerritory{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    
    background-color: #F9F9F9;
}
//결제하기 텍스트
.paymentBackground{
    width: 100%;
    padding: 20px;
    margin: 20px;

    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
}
.paymenttText{
    margin: 20px;
    padding: 20px;

    font-size: x-large;
    font-weight: bolder;
    text-align: right;
}
//배송정보 목록
.shippinginfoBox{
  width: 90vw;
  padding: 40px;
  border: 1px solid #b1b2ff;

  display: flex;
  flex-direction: column
}
.shippingInfo{
  margin: 10px;

  display: flex;
  flex-direction: row;
  justify-content: space-between
}
.text{
  font-weight: bold;
  font-size: large;
}
.input{
  width: 70vw;
  border: none;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
  background-color: #EEF1FF;
}
/* 결제하기 버튼 */
.paymentButtonBox{
  margin-top: 50px;
}
.paymentButton{}
`;

function Payment() {

    // const { register, handleSubmit } = useForm();
    const perchase = () => {
      
    }

  return (
    <Wrapper>
      <div className="paymentTerritory">

        {/* 결제하기 텍스트 */}
        <div className="paymentBackground">
          <span className="paymenttText">결제하기</span>
        </div>

        {/* 배송정보 목록 */}
        <div className="shippinginfoBox">
            <div className="shippingInfo">
                <span className="text">이름</span>
                <input className="input"></input>
            </div>
            <div className="shippingInfo">
                <span className="text">배송지</span>
                <input className="input"></input>
            </div>
            <div className="shippingInfo">
                <span className="text">전화번호</span>
                <input className="input"></input>
            </div>
            <div className="shippingInfo">
                <span className="text">요청사항</span>
                <input className="input"></input>
            </div>
        </div>
        {/* 결제하기 버튼 */}
        <div className="paymentButtonBox">
          <button className="paymentButton" onClick={perchase}>결제하기</button>
        </div>
      </div>

        {/* 장바구니 내용 */}
        <Cart></Cart>
        {/* 아... 근데 장바구니 컴포넌트를 통째로 가져오면 안될것같음..
        1. 버튼 어케할건데?
        2. 보내는 로직은 어떻게 되는건데?
        라는 이유로 컴포넌트 통째로 가져오는건 안될것같아 ㅜㅜ */}
    </Wrapper>
  );
}

export default Payment;
