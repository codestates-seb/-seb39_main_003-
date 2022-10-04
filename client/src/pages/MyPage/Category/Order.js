/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState } from 'react'
import styled from 'styled-components'
import { useLocation } from 'react-router';
import Cat from '../../Shopping/images/cat.png';


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
  border: 1px solid red;
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
  width: 45%;
  padding: 20px;
  /* margin-bottom: 30px; */
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  /* border: 2px solid green; */
}

.orderList2 {
  width: 45%;
  padding: 20px;

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  /* border: 2px solid red; */
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

.myordername{
  /* border: 1px solid red; */
  font-size: 1.3rem;
  font-weight: 600;
  margin: 0px 20px;
}
.myorderstate{
  /* border: 1px solid red; */
  font-size: 1.2rem;
  font-weight: 500;
}
`

function Order( {convertPrice} ) {

  const [orderList, setOrderList] = useState([]);
  const [selectValue, setSelectValue] = useState("내 배송지");

  const handleChangeMy = (e) => {
    // console.log(`*****handleChange*****`);
    // console.log(`선택한 값 : ${e.target.value}`);

    setSelectValue("내 배송지");
  };

  const handleChangeNew = (e) => {
    // console.log(`*****handleChange*****`);
    // console.log(`선택한 값 : ${e.target.value}`);

    setSelectValue("신규 배송지");
  };

  const location = useLocation();

  // console.log(location)
  React.useEffect(() => {
    setOrderList(location.state.list)
  }, [orderList])

  const token = sessionStorage.getItem('accessToken');
  const realToken = token.slice(7)
  // console.log(realToken)
  
  window.Buffer = window.Buffer || require("buffer").Buffer; 
  
  const base64Payload = realToken.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
  const payload = Buffer.from(base64Payload, 'base64'); 
  const result = JSON.parse(payload.toString())
  // console.log(result);

    const [info, setInfo] = useState([]);
    
    React.useEffect(() => {
      fetch(`http://211.58.40.128:8080/api/v1/member/${result.id}`)
      .then(res => res.json())
      .then(res => {
        setInfo(res)
        // console.log(res)
      })
    } , [])

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
              // checked={setSelectValue("내 배송지")}
              onChange={handleChangeMy}
              />
            내 배송지
            <input
              name="platform"
              type="radio"
              className='secondRadio'
              // checked={setSelectValue("신규 배송지")}
              onChange={handleChangeNew}
            />
            신규 배송지
         </div>

         <div>
           { selectValue === "내 배송지" ? <span>{info.address}</span> : <span>새로 들어갈 주소</span> }
         </div>

        {/* 내 주문 목록 */}
        <div className='myorderlistBackground'>
          {/* 주문 목록 탭 */}
          <div className='myordertabBackground'>
              <span className='myordertab'>상품정보</span>
              <span className='myordertab'>배송비 / 배송 형태</span>
          </div>
          {/* 주문목록 본문 */}

          {orderList && orderList.map((el, idx) => {
            return (
              <div className='orderAll' key={idx}>
                <div className='orderList1'>
                    <span>
                      <img src={Cat} alt='상품 사진' className='myorderimg'/>
                    </span>
                    <span className='myordername'>{el.itemName}</span>
                    <span className='myorderstate'>{convertPrice(el.price * el.itemCnt)} 원</span>
                </div>

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
      </div>
    </Wrapper>
  )
}

export default Order