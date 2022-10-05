/* eslint-disable jsx-a11y/alt-text */
import React, { useState } from 'react'
import styled from 'styled-components'
// import { Link } from 'react-router-dom'
import Body from './CartBody';

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

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
.carttext{
    margin: 20px;
    
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
    font-size: 2rem;
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

function Cart( {cart, setCart, convertPrice} ) {

  return (

    <Wrapper>
        <div className='cartterritory'>
            
            <div className='cartlistBackground'>

                <div className='carttabBackground'>
                    <span className='carttab'>장바구니</span>
                </div>

                <Body />
                
            </div>
        </div>
    </Wrapper>
  )
}

export default Cart