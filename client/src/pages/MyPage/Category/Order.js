/* eslint-disable react-hooks/exhaustive-deps */

import React, { useState, useEffect } from 'react'

import styled from 'styled-components'
import { useLocation } from 'react-router';
import Cat from '../../Shopping/images/cat.png';
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';



const Wrapper = styled.div`

@font-face {
    font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }

box-sizing: border-box;
.orderterritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
// 주문 내역 텍스트
.orderBackground{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.ordertext{  
  margin: 20px;
  padding: 20px;  

  font-size: 2rem;
  font-weight: bolder;
  text-align: right;
}

.adressBox {
  width: 90%;
  margin: 0px 0px 40px 0px;
  display: flex;
  justify-content: center;
  /* border: 1px solid red; */
}

.firstRadio {
  margin-right: 15px;
}

.secondRadio {
  margin: 0px 15px;
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
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #B1B2FF;
  border-bottom: 1px solid #B1B2FF;
  margin-bottom: 20px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.myordertab {
  padding: 10px;
  width: 40%;
  display: flex;
  justify-content: center;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
.myordertab2 {
  padding: 10px;
  width: 20%;
  display: flex;
  justify-content: center;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
.myordertab3 {
  padding: 10px;
  width: 20%;
  display: flex;
  justify-content: center;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
.myordertab4 {
  padding: 10px;
  width: 20%;
  display: flex;
  justify-content: center;

  font-weight: bold;
  font-size: large;
  color: #9263FF;
}
// 주문 목록 본문
.orderAll {
  width: 100%;
  display: flex;
  justify-content: space-around;
  /* align-items: center; */
  /* border: 2px solid blue; */
  background-color: #EEF1FF;
  margin: 1rem 0rem;
}

.orderList1{
  width: 40%;
  padding: 20px;
  /* margin-bottom: 30px; */
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}

.orderList2 {
  width: 20%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.deliver {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 1.1rem;
}

.charge {
  font-weight: 500;
}

.charge2 {
  font-weight: 600;
}

.myorderimg{
  width: 15rem;
  height: 10rem;
  /* border: 1px solid red; */
}

.myOrderName{
  /* border: 1px solid red; */
  font-size: 1.3rem;
  font-weight: 600;
  margin: 0px 20px;
}

.myOrderCount {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
  font-size: 1.3rem;
  font-weight: 600;
  margin: 0px 20px;
}

.myOrderPrice{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
  font-size: 1.2rem;
  font-weight: 500;
}

.orderbutton {
    padding: 15px 40px;
    border: 1px solid purple;
    border-radius: 20px;
    font-family: Cafe24Ssurround;
  
    &:hover {
      background-color: #EEF1FF;
    }
  }

.addressBox {
  /* border: 1px solid red; */
  margin-bottom: 2.5rem;
}

`

const StyledLink = styled(Link)`
  font-size: 1.5rem;
  text-decoration: none;
  color: black;
  /* border: 2px solid red; */
  `

function Order( {convertPrice} ) {

  const location = useLocation();
  const [orderList, setOrderList] = useState([]);
  const [selectValue, setSelectValue] = useState("내 배송지");

  const navigate = useNavigate();
  // console.log(location)

  useEffect(() => {
    setOrderList(location.state.list)
  }, [orderList])
  // console.log(orderList)

  const handleChangeMy = () => {
    setSelectValue("내 배송지");
  };

  const handleChangeNew = () => {
    setSelectValue("신규 배송지");
  };



  const token = sessionStorage.getItem('accessToken');
  const realToken = token.slice(7)
  // console.log(realToken)
  
  window.Buffer = window.Buffer || require("buffer").Buffer; 
  
  const base64Payload = realToken.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
  const payload = Buffer.from(base64Payload, 'base64'); 
  const result = JSON.parse(payload.toString())
  console.log(result);

    const [info, setInfo] = useState([]);
    
    React.useEffect(() => {
      fetch(`https://shopforourpets.shop:8080/api/v1/member/${result.memberId}`)
      .then(res => res.json())
      .then(res => {
        setInfo(res)
        // console.log(res)
      })
    } , [])

    const [inOrder, setInOrder] = useState([]);

    const final = []
    for(let i = 0; i < orderList.length; i++) {
      const first = {
        itemId: orderList[i].itemId,
        orderItemCnt: orderList[i].itemCnt
      }
      final.push(first)
      // console.log(final)
    }

    let totalPrice = 0;
    for(let i = 0; i < orderList.length; i++) {
      totalPrice += orderList[i].price * orderList[i].itemCnt;
    }

    const orderItem = () => {
      fetch(`https://shopforourpets.shop:8080/api/v1/pay`,{
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          amount: totalPrice,
          payBy: "카카오 페이",
          memberId: info.memberId,
          orderItems: final,
          newAddress: '서울 중곡동 225-10',
          newPhone: '010-2060-1122',
          newName: '홍길동',
          requirement: '안전하게 와주세요'
        })
      })
      .then(() => {
        alert('결제가 완료되었습니다 !')
        // navigate(`/mypage/buy`)
      })
      .then((err) => console.log(err))
    }
    // console.log(totalPrice)
    // console.log(info.memberId)
    // console.log(final)
    
//     let totalPrice = 0;
//             for(let i = 0; i < orderList.length; i++) {
//               totalPrice += orderList[i].price * orderList[i].itemCnt;
//             }
// console.log(totalPrice)



  return (
    <Wrapper>
      <div className='orderterritory'>

          {/* 주문내역 텍스트 */}
          <div className='orderBackground'>
            <div className='ordertext'>주문서</div>
          </div>

          <div className="adressBox">
              <input
                name="platform"
                type="radio"
                className='firstRadio'
                checked={true}
                // checked={setSelectValue("내 배송지")}
                onClick={handleChangeMy}
                onChange={handleChangeMy}
                />
              내 배송지

              <input
                name="platform"
                type="radio"
                className='secondRadio'
                // checked={setSelectValue("신규 배송지")}
                onClick={handleChangeNew}
                onChange={handleChangeNew}
              />
              신규 배송지
          </div>

          <div className='addressBox'>
            { selectValue === "내 배송지" ? <span>{info.address}</span> : <span>새로 들어갈 주소</span> }
          </div>


          {/* 내 주문 목록 */}
          <div className='myorderlistBackground'>
            {/* 주문 목록 탭 */}
            <div className='myordertabBackground'>
                <span className='myordertab'>상품이미지</span>
                <span className='myordertab2'>수량</span>
                <span className='myordertab3'>가격</span>
                <span className='myordertab4'>배송비 / 배송 형태</span>
            </div>

            {/* 주문목록 본문 */}
            {orderList && orderList.map((el, idx) => {
              return (
                <div className='orderAll' key={idx}>
                  <div className='orderList1'>
                      <span>
                        <img src={el.thumbnail} alt='상품 사진' className='myorderimg'/>
                      </span>
                      <span className='myOrderName'>{el.itemName}</span>
                  </div>
                      <span className='myOrderCount'>{el.itemCnt} 개</span>
                      <span className='myOrderPrice'>{convertPrice(el.price * el.itemCnt)} 원</span>

                  <div className='orderList2'>
                    <span className='deliver'>
                      <span className='charge'>택배배송</span>
                      <span className='charge2'>배송비 무료</span>
                    </span>
                  </div>


                </div>
              )
            })}
          </div>

          <div className='orderBox'>
            <StyledLink to={`/mypage/buy`} state={
              {orderInfo: orderList}
              }>
              <span className='orderbutton' onClick={orderItem}>
                {convertPrice(totalPrice)}원 결제하기
              </span>
            </StyledLink>
          </div>
          

      </div>
    </Wrapper>
  )
}

export default Order