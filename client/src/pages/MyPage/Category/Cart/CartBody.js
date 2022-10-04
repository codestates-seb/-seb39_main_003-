import React, { useEffect, useState } from 'react'
import styled from 'styled-components';
import { ImCancelCircle } from "react-icons/im";
import { FiMinusCircle } from "react-icons/fi";
import { FiPlusCircle } from "react-icons/fi";
import Cat from '../../../Shopping/images/cat.png';


const Wrapper = styled.div`
  width: 100%;
  margin: 40px 0px 20px 0px;
  /* border: 1px solid red; */

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
    border: 1px solid red;
  }

  .totalPrice {
    font-size: 1.5rem;
    font-weight: 400
  }

  .checkBox {
    height: 2rem;
    border: 1px solid gray;
    display: flex;
    align-items: center;
    justify-content: space-around;
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
`;

// useState로 cartList setCartList 설정해서 초기값 배열로 담아주고
// GET 요청으로 장바구니 데이터에 있는 상품들 불러온 다음 
// map 함수 돌려서 장바구니 데이터에 추가된 상품들 하나씩 조회 가능하게끔 하기
function CartBody( {convertPrice} ) {

  const [cartList, setCartList ] = useState([])
  const [checkLists, setCheckLists] = useState([]);
  const [checkedArr, setCheckedArr] = useState(false)
  const [test, setTest] = useState([])
  const [testPrice, setTestsPrice] = useState(0)

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/cart?memberId=000001&page=1&size=10`)
    .then(res => res.json())
    .then(res => {
      setCartList(res.data)
      console.log(res.data)
    })
  }, [])

    let totalPrice = 0
    for(let i = 0; i < cartList.length; i++) {
      totalPrice += cartList[i].price * cartList[i].itemCnt
    }
  
  return (
    
    <Wrapper>

      <div className='checkBox'>
        <span>박스</span>
        <span>이미지</span>
        <span>가격</span>
        <span>수량</span>
      </div>

      {cartList && cartList.map((el, idx) => {
        
      // const Image = `https://mypet-imaga.s3.ap-northeast-2.amazonaws.com/items/${el.thumbnail}`

      const deleteCartItemId = () => {
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
        fetch(`http://211.58.40.128:8080/api/v1/cart/${el.cartItemId}`, {
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

      const handleCheckList = (checked, id) => {
        if (checked) {
          setCheckLists([...checkLists, id]);
        } else {
          setCheckLists(checkLists.filter((check) => check !== id));
        }
      };

        return (
            <div key={idx}>    
              <div className='listBox'>

                <div className='checkLine'>
                  <input className="check" type="checkBox"
                  onChange={(e) => handleCheckList(e.currentTarget.checked, `${el.cartItemId}`)}
                  defaultChecked={true} />
                </div>

                <span>
                  <img className='listImg' src={Cat} alt="상품 사진" />
                </span>

                <span className='listName itemAll'>{el.itemName}</span>

                <span className='listPrice itemAll'>{el.price} 원</span>
                
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
        <span className='totalPrice'>총 금액 : {totalPrice} 원</span>
      </div>

    </Wrapper>
  
  )
}

export default CartBody