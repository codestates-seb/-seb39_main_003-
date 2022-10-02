import React, { useEffect, useState } from 'react'
import styled from 'styled-components';
import { ImCancelCircle } from "react-icons/im";
import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";


const Wrapper = styled.div`
  width: 100%;
  margin: 40px 0px 20px 0px;
  /* border: 1px solid red; */

  .listBox {
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
    font-weight: 400;
    font-size: 1.2rem;
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
`;

// useState로 cartList setCartList 설정해서 초기값 배열로 담아주고
// GET 요청으로 장바구니 데이터에 있는 상품들 불러온 다음 
// map 함수 돌려서 장바구니 데이터에 추가된 상품들 하나씩 조회 가능하게끔 하기
function CartBody() {

  const [cartList, setCartList ] = useState([])

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/cart?memberId=000001&page=1&size=10`)
    .then(res => res.json())
    .then(res => {
      setCartList(res.data)
      console.log(res.data)
    })
  }, [])

  // const [isCount, setIsCount] = useState(1)

  // const handleButtonPlus = () => {
  //   setIsCount( count =>  count + 1)
  // }

  // const handleButtonMinus = () => {
  //   if (  isCount === 1 ) return;
  //   else (
  //     setIsCount( count =>  count - 1)
  //   )
  // }

  
  return (
    
    <Wrapper>

      {cartList && cartList.map((el, idx) => {
      const Image = `https://mypet-imaga.s3.ap-northeast-2.amazonaws.com/items/${el.thumbnail}`
      const readCartItemId = () => {
        fetch(`http://211.58.40.128:8080/api/v1/cart/${el.cartItemId}`, {
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
        fetch(`http://211.58.40.128:8080/api/v1/cart/${el.cartItemId}`, {
          method: 'PATCH',
          headers: {
            "content-type": "application/json"
          },
          body: JSON.stringify({
            "itemCnt" : count + 1
          })
        })
      }

      const Minus = (count) => {
        fetch(`http://211.58.40.128:8080/api/v1/cart/${el.cartItemId}`, {
          method: 'PATCH',
          headers: {
            "content-type": "application/json"
          },
          body: JSON.stringify({
            itemCnt: count - 1
          })
        })
      }

        return (
            <div key={idx}>    
              <div className='listBox'>

                <span>
                  <img className='listImg' src={Image} alt="상품 사진" />
                </span>

                <span className='listName itemAll'>{el.itemName}</span>

                <span className='listPrice itemAll'>{el.price}</span>
                
                <span className='listCount itemAll'>
                  <span className='countChild cal' onClick={() => {
                    Minus(el.itemCnt)
                  }}><FiMinusCircle /></span>
                  <span className='countChild'>{el.itemCnt}</span>
                  <span className='countChild cal' onClick={() => {
                    Plus(el.itemCnt)
                    }}><FiPlusCircle /></span>
                </span>

                <span className='itemAll deleteBox'>
                  <span className='listDelete' onClick={readCartItemId}>
                    <ImCancelCircle />
                  </span>
                </span>

              </div>
            </div>
      )
      })}


    </Wrapper>
  
  )
}

export default CartBody