import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import FootLogo from '../../assets/carousel3.jpg';
// import SocialLogin from '../../components/SocialLogin'
import { useLocation } from 'react-router';
import Dog from '../../assets/강아지 장난감.jpg';


const Wrapper = styled.div`
box-sizing: border-box;

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

.buyBackground{
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
// 주문 완료 되었습니다.
.buyCompleteText{
  width: 100vw;
  margin-top: 80px;
  margin-bottom: 80px;

  font-weight: 700;
  font-size: xx-large;
  text-align: center;
  font-family: InfinitySans-RegularA1;
}
// 배송지
.buyAddress{
  width: 90vw;
  padding: 0px 100px;
  margin-bottom: 50px;
  border: 2px solid #B1B2FF;
  background-color: #F6E6E4;
  border-radius: 40px;
  
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.bAd1{
  padding: 100px 0px;
  margin-right: 50px;

  font-size: 2rem;
  font-weight: 500;
  font-family: Cafe24Ssurround;
}
.bAd2{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  /* border: 1px solid blue; */
  font-size: 1.5rem;
  font-weight: 500;
  font-family: InfinitySans-RegularA1;
}

.bAd3 {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  /* border: 1px solid blue; */
  font-size: 1.2rem;
  font-weight: 500;
  font-family: InfinitySans-RegularA1;
}

.bAd4 {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  /* border: 1px solid blue; */
  font-size: 1rem;
  font-weight: 500;
  font-family: InfinitySans-RegularA1;
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

.img {
  /* border: 1px solid red; */
  width: 17.5rem;
  height: 13rem;
  background-size: cover;
}
`

function Buy( {convertPrice} ) {

  const location = useLocation();
  const [checkOrder, setCheckOrder] = useState([])

  useEffect(() => {
    setCheckOrder(location.state.orderInfo)
  }, [checkOrder] )
  // console.log(checkOrder)

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
      fetch(`http://211.58.40.128:8080/api/v1/member/${result.memberId}`)
      .then(res => res.json())
      .then(res => {
        setInfo(res)
        console.log(res)
      })
    } , [])

  return (
    <Wrapper>
      <div className='buyCompleteText'>주문이 완료 되었습니다</div>
      {checkOrder && checkOrder.map((el, idx) => {
        return (
        <div key={idx}>
            <div className='buyBackground'>


                <div className='buyAddress'>
                    <div className='bAd1'>주문 완료 상품</div>
                      {/* <div className='bAd2'>{el.thumbnail}</div> */}
                    <div className='bAd2'>
                      <img className="img" src={el.thumbnail} alt="사진" />
                    </div>
                    <div className='bAd2'>{el.itemName}</div>
                    <div className='bAd2'>{convertPrice(el.totalPrice)} 원</div>
                    <div className='bAd3'>
                      배송 준비중
                    <div className='bAd4'>{info.address} xx동 xx호</div>
                    </div>
                </div>

            </div>
        </div>
        )

          
      })}
          <div className='picbox'>
            <img className='pic1' src={FootLogo} alt='사진' />
          </div>


      <div>
      </div>
    </Wrapper>
  )
}

export default Buy