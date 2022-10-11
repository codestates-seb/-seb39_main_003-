import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'
import Cat from '../../Shopping/images/cat.png';

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
  margin-top: 20px;

  font-weight: bold;
  font-size: 2rem;
  color: #9263FF;
}
// 찜 목록 본문
.wishlistBox{
  width: 90vw;
  padding: 20px;
  margin-top: 20px;
  border-bottom-right-radius: 10px;
  border-bottom-right-radius: 10px;
  font-size: 1.3rem;
  font-weight: 500;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;


  background-color: #EEF1FF;
}

.img {
  width: 10rem;
    height: 10rem;
}
`

function Wish( {convertPrice} ) {

  const [wishList, setWishList] = useState([])

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/item/wish?memberId=000001&page=1&size=8`)
    .then(res => res.json())
    .then(res => {
      setWishList(res.data)
      // console.log(res.data)
    })
    .catch(err => console.log(err))
  }, [])

  return (
    <Wrapper>
      <div className='wishlistterritory'>
        {/* 찜 목록 텍스트 */}
        {/* <div className='wishBackground'>
          <span className='carttext'>찜 목록</span>
        </div> */}
        {/* 내 찜 목록 */}
        <div className='wishlistBackground'>
          {/* 찜 목록 탭 */}
          <div className='wishtabBackground'>
              <span className='wishtab'>찜 목록</span>
          </div>
          {/* 찜 목록 본문 */}

          {wishList && wishList.map((el, idx) => {
            return (
                <div className='wishlistBox' key={idx}>
                    <span>
                      <img src={el.thumbnail} alt='상품 사진' className='img'/>
                    </span>
                    <span className='wishname'>{el.itemName}</span>
                    <span className='wishprice'>{convertPrice(el.price)} 원</span>
                </div>
            )
          })}
          </div>
      </div>
    </Wrapper>
  )
}

export default Wish