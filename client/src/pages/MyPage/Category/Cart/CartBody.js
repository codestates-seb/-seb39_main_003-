import React, { useEffect, useState } from 'react'
import styled from 'styled-components';
import { ImCancelCircle } from "react-icons/im";
import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import Cat from '../../../Shopping/images/cat.png';
import { Link } from 'react-router-dom'
// import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`
  width: 100%;
  margin: 40px 0px 20px 0px;
  /* border: 1px solid red; */

  @font-face {
    font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }

  .listBox {
    width: 100%;
    border: 1px solid lightgray;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
  }

  .listImg {
    width: 15rem;
    height: 10rem;
    background-size: cover;
  }

  .itemAll {
    width: 25%;
    /* border: 1px solid green; */
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1.3rem;
    font-weight: 500;
  }

  .deleteBox {
    width: 3rem;
  }

  .listDelete {
    cursor: pointer;
  }

  .listCount {
    height: 2rem;
    /* border: 1px solid red; */
  }

  .countChild {
    padding: 0px 10px 0px 10px;
    font-size: 1.5rem;
  }

  .cal {
    cursor: pointer;
  }

  .totalBox {
    height: 5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid purple;
    background-color: #EEE3CB;
    border-radius: 7px;
    margin-bottom: 2rem;
    font-family: Cafe24Ssurround;
  }

  .totalPrice {
    font-size: 1.7rem;
  }

  .checkBox {
    height: 3rem;
    border: 1px solid gray;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: 600;
    font-size: 1.2rem;
  }

  .type {
    position: relative;
    left: 19rem;
    padding: 0px 8rem 0px 17rem;
    /* border: 1px solid red; */
  }

  .checkLine {
    width: 9rem;
    /* border: 1px solid red; */
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .check {
    width: 1rem;
    height: 1rem;
    /* border: 1px solid red; */
  }

  .orderBox {
    height: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 7px;
    margin-bottom: 2rem;
    font-family: Cafe24Ssurround;

  }

  .orderbutton {
    padding: 15px 40px;
    border: 1px solid purple;
    border-radius: 20px;
  
    &:hover {
      background-color: #EEF1FF;
    }
  }
  `;

const StyledLink = styled(Link)`
  font-size: 1.5rem;
  text-decoration: none;
  color: black;
  `

// useState??? cartList setCartList ???????????? ????????? ????????? ????????????
// GET ???????????? ???????????? ???????????? ?????? ????????? ????????? ?????? 
// map ?????? ????????? ???????????? ???????????? ????????? ????????? ????????? ?????? ??????????????? ??????
function CartBody( {convertPrice} ) {

  const [cartList, setCartList ] = useState([])
  const [checkLists, setCheckLists] = useState([]);
  const [testPrice, setTestsPrice] = useState(0)

  // const navigate = useNavigate();

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/cart?memberId=000001&page=1&size=10`)
    .then(res => res.json())
    .then(res => {
      setCartList(res.data)
      console.log(res.data)
    })
  }, [])


    // for(let i = 0; i < cartList.length; i++) {
    //   totalPrice += cartList[i].price * cartList[i].itemCnt
    // }

  
  return (
    
    <Wrapper>

      <div className='checkBox'>
        {/* <span>??????</span>
        <span>?????????</span> */}
        <span className='type'>??????</span>
        <span className='type'>??????</span>
      </div>

      {cartList && cartList.map((el, idx) => {
        
      // const Image = `https://mypet-imaga.s3.ap-northeast-2.amazonaws.com/items/${el.thumbnail}`

      const deleteCartItemId = () => {
        fetch(`https://shopforourpets.shop:8080/api/v1/cart/${el.cartItemId}`, {
          method: 'DELETE'
        })
        .then(() => {
          window.location.reload('/mypage/cart')
        })
        .catch((err) => {
          console.log(err)
        })
      }
      const Plus = (count) => {
        fetch(`https://shopforourpets.shop:8080/api/v1/cart/${el.cartItemId}`, {
          method: 'PATCH',
          headers: {
            "content-type": "application/json",
            "accept": "application/json"
          },
          body: JSON.stringify({
            itemCnt: count + 1,
            itemId: el.itemId,
            memberId: "000001",
          })
        })
        .then(() => window.location.reload());
      }

      const Minus = (count) => {
        fetch(`https://shopforourpets.shop:8080/api/v1/cart/${el.cartItemId}`, {
          method: 'PATCH',
          headers: {
            "content-type": "application/json",
            "accept": "application/json"
          },
          body: JSON.stringify({
            itemCnt: count - 1,
            itemId: el.itemId,
            memberId: "000001",
          })
        })
        .then(() => window.location.reload());
      }

      // const handleCheckList = (checked, id) => {
      //   if (checked) {
      //     setCheckLists([...checkLists, id]);
      //   } else {
      //     setCheckLists(checkLists.filter((check) => check !== id));
      //   }
      // };
      const handleChecked = (e) => {
        const targetItem = cartList.filter((el) => {
          return el.itemName === e.currentTarget.name})

        if(e.currentTarget.checked) {
          setCheckLists((prev) => [...prev, el]);
          console.log(el)
          setTestsPrice((totalPrice) => totalPrice + ((targetItem[0].price) * targetItem[0].itemCnt)
          )}
        else {
          setCheckLists(cartList.filter((item) => item.cartItemId !== el.cartItemId));
          setTestsPrice((totalPrice) => totalPrice - ((targetItem[0].price) * targetItem[0].itemCnt)
          )}
          console.log('targetItem:', targetItem)
      }
      
      // console.log(testPrice)

        return (
            <div key={idx}>    
              <div className='listBox'>

                <div className='checkLine'>

                  <input className="check" type="checkbox" onChange={handleChecked}

                  name={el.itemName}
                  // onChange={(e) => handleCheckList(e.currentTarget.checked, `${el.cartItemId}`)}
                  // defaultChecked={true}
                  />
                </div>

                <span>
                  <img className='listImg' src={el.thumbnail} alt="?????? ??????" />
                </span>

                <span className='listName itemAll'>{el.itemName}</span>

                <span className='listPrice itemAll'>{convertPrice(el.price * el.itemCnt)} ???</span>
                
                <span className='listCount itemAll'>

                  <span className='countChild cal' onClick={() => {
                    Minus(el.itemCnt)}}>
                      <FiMinusCircle />
                  </span>

                  <span className='countChild'>{el.itemCnt}</span>

                  <span className='countChild cal' onClick={() => {
                    Plus(el.itemCnt)}}>
                      <FiPlusCircle />
                      </span>
                </span>

                <span className='itemAll deleteBox'>
                  <span className='listDelete' onClick={deleteCartItemId}>
                    <ImCancelCircle />
                  </span>
                </span>

              </div>

                    
                
            </div>
      )
    })}

      <div className='totalBox'>

        <span className='totalPrice'>??? ?????? : {convertPrice(testPrice)} ???</span>
      </div>

      <div className='orderBox'>
        <StyledLink to={`/mypage/order`} state={ {list: checkLists} }>
          <span className='orderbutton'>????????????</span>
        </StyledLink>

      </div>

    </Wrapper>
  
  )
}

export default CartBody