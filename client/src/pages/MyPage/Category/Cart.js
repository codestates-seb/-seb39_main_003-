/* eslint-disable jsx-a11y/alt-text */
import React from 'react'
import styled from 'styled-components'
// import { Link } from 'react-router-dom'
import Cat from '../../Shopping/images/cat.png'

const Wrapper = styled.div`
.cartterritory{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
}
// 장바구니 텍스트
.cartBackground{
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
.cartlistBackground{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
//장바구니 탭
.carttabBackground{
    width: 90vw;
    padding: 5px;
    border-top: 1px solid #B1B2FF;
    border-bottom: 1px solid #B1B2FF;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
.carttab{
    padding: 10px;

    font-weight: bold;
    font-size: large;
    color: #9263FF;
}
.cartBox{
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

.cartimg{
    width: 10rem;
    height: 10rem;

}

.cartname{}
.cartprice{}
.cartamount{}
.orderbutton{}
`

function Cart() {
  return (
    <Wrapper>
        <div className='cartterritory'>
            {/* 장바구니 텍스트 */}
            <div className='cartBackground'>
                <span className='carttext'>장바구니</span>
            </div>
            <div className='cartlistBackground'>
                {/* 장바구니 탭 */}
                <div className='carttabBackground'>
                    <span className='carttab'>상품명</span>
                    <span className='carttab'>판매가</span>
                    <span className='carttab'>수량</span>
                </div>
                {/* 장바구니 본문 */}
                <div className='cartBox'>
                    <span className='cartimgs'>
                        <img className='cartimg' src={Cat}/>
                    </span>
                    <span className='cartname'>상품이름</span>
                    <span className='cartprice'>판매가</span>
                    <input className='cartamount' type='number' placeholder='수량'></input>
                </div>
                <button className='orderbutton'>주문하기</button>
            </div>
        </div>
    </Wrapper>
  )
}

export default Cart