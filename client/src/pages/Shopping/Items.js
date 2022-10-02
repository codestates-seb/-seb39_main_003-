/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from 'react'
import styled from 'styled-components';
import Minus from './images/icon-minus-line.svg';
import Plus from './images/icon-plus-line.svg';
import { useParams } from "react-router-dom";
import { useLocation } from 'react-router';
import { useNavigate } from 'react-router-dom';


const Wrapper = styled.div`

  display: flex;
  box-sizing: border-box;
  justify-content: center;
  flex-direction: column;

  .item_top {
    width: 100%;
    height: 40rem;
    border: 2px solid red;
    display: flex;
    flex-direction: column;
    margin-top: 20px;
    align-items: center;
  }

  .item_bottom {
    width: 100%;
    height: 20rem;
    /* border: 2px solid blue; */
    display: flex;
    flex-direction: column;
  }

  .item_imagebox {
    width : 40rem;
    height: 25rem;
    /* border: 2px solid blue; */
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .item_nameBox {
    width: 90%;
    height: 5rem;
    /* border: 2px solid green; */
    display: flex;
    justify-content: center;
    margin: 20px 0px 10px 0px;
    padding-left: 2rem;
    font-size: 2rem;
    font-weight: 600;
  }

  .item_infoBox {
    width: 90%;
    height: 5rem;
    /* border: 2px solid gray; */
    display: flex;
    justify-content: center;
    padding-left: 2rem;
    font-size: 1.5rem;
    font-weight: 500;
  }

  .amount {
  position: relative;
  width: 10rem;
  height: 4rem;
  border: 1px solid gray;
  border-radius: 5px;
  margin-bottom: 30px;
  margin-left: 70px;
}

.amount .minus {
  position: absolute;
  width: 20px;
  height: 20px;
  top: 50%;
  left: 14px;
  transform: translateY(-50%);
  cursor: pointer;
}

.amount .count {
  position: absolute;
  width: 56px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid #c4c4c4;
  border-top: none;
  border-bottom: none;
}

.amount .count span {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.amount .plus {
  position: absolute;
  width: 20px;
  height: 40px;
  top: 50%;
  right: 14px;
  transform: translateY(-50%);
  cursor: pointer;
}

.sum {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 30px;
  border: 2px solid blue;
}

.sum .sum_price {
  font-size: 2rem;
  font-weight: 500;
  line-height: 23px;
  color: #000000;
  padding-left: 70px;
}

.sum .total {
  font-size: 18px;
  line-height: 23px;
  color: #767676;
}

.sum .total_count {
  font-size: 18px;
  line-height: 23px;
  color: #19ce60;
}

.sum .total_count::after {
  content: "|";
  width: 5px;
  height: 23px;
  color: #c4c4c4;
  padding: 0px 11px;
}

.sum .total_price {
  font-size: 36px;
  line-height: 45px;
  color: #19ce60;
}

.sum .total_unit {
  font-size: 18px;
  line-height: 23px;
  color: #19ce60;
}

.btn .btn_buy {
  width: 416px;
  height: 60px;
  border-radius: 5px;
  background: #19ce60;
  border: none;
  cursor: pointer;
  font-size: 18px;
  font-weight: bold;
  line-height: 22px;
  color: #ffffff;
  margin-right: 44px;
  margin-left: 60px;
}

.btn {
  display: flex;
  justify-content: space-between;
}

.btn .btn_cart {
  width: 200px;
  height: 60px;
  border-radius: 5px;
  background: #767676;
  border: none;
  cursor: pointer;
  font-size: 18px;
  font-weight: bold;
  line-height: 22px;
  color: #ffffff;
  margin-right: 60px;
}

.total_info {
  padding-right: 70px;
}

.itemImage {
  background-size: cover;
  width : 90%;
  height: 25rem;
  padding-top: 40px;
  /* border: 2px solid blue; */
  display: flex;
  justify-content: center;
  align-items: center;
}

.deleteBox {
  height: 2.2rem;
  /* border: 1px solid red; */
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-top: 10px;
  margin-bottom: -10px;
}

.patch {
  border: 1px solid gray;
  padding: 5px 5px 5px 5px;
  border-radius: 5px;
  margin-right: 10px;
  cursor: pointer;
  background-color: #EEEEEE;

  &:hover {
    background-color: #CFD2CF;
    font-weight: 500;
  }
}

.delete {
  border: 1px solid gray;
  padding: 5px 5px 5px 5px;
  border-radius: 5px;
  margin-left: 10px;
  margin-right: 20px;
  cursor: pointer;
  background-color: #EEEEEE;

  &:hover {
    background-color: #CFD2CF;
    font-weight: 500;
  }
}
`;

function Items( { convertPrice, cart, setCart } ) {

  // const [test, setTest] = useState(ItemData);
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [count, setCount] = useState(1);

  const location = useLocation();

  const [itemInfo, setItemInfo] = useState([])

    useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/item/${location.state.id}`)
    .then((res) => res.json())
    .then(res => {
      setItemInfo(res.data)
      console.log(res.data)
    })
    .catch((err) => {
      console.log(err)
    })
  } , [])

  const final = `https://mypet-imaga.s3.ap-northeast-2.amazonaws.com/items/${location.state.thumbnail}`


  // 상세페이지에서 물건 수량 조절
  const handleQuantity = (type) => {
    if (type === "plus") {
      setCount(count + 1);
    } else {
      if (count === 1) return;
      setCount(count - 1);
    }
  };

  const setQuantity = (id) => {
    const found = cart.filter((el) => el.id === id)[0];
    const idx = cart.indexOf(found);
    const cartItem = {
      id: itemInfo.itemId,
      image: {final},
      name: itemInfo.itemName,
      price: itemInfo.price,
      quantity: count,
      info: itemInfo.info
    };
    setCart([...cart.slice(0, idx), cartItem, ...cart.slice(idx + 1)]);
  };

  const handleCart = () => {

    fetch(`http://211.58.40.128:8080/api/v1/cart`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        itemId: itemInfo.itemId,
        name: itemInfo.itemName, 
        price: itemInfo.itemPrice,
        image: `https://mypet-imaga.s3.ap-northeast-2.amazonaws.com/items/${itemInfo.thumbnail}`,
        itemCnt: count,
        memberId: "000001"
        // "itemCnt": 1,
        // "itemId": "000002",
        // "memberId": "000001"
      }),
    })
    .then((res) => res.json())  
    .then((result) => console.log(result))
    .then(() => navigate(`/mypage/cart`))
    .catch(alert('장바구니에 추가되었습니다 !!'))

    // const cartItem = {
    //   id: itemInfo.itemId,
    //   image: `${final}`,
    //   name: itemInfo.itemName,
    //   quantity: count,
    //   price: itemInfo.price,
    //   info: itemInfo.info
    // };
    // const found = cart.find((el) => el.id === cartItem.id);
    // if (found) setQuantity(cartItem.id, found.quantity + count);
    // else setCart([...cart, cartItem]);
    // console.log('장바구니')
  };

  // let allChange = {}

  // if(product.id === id) {
  //   allChange = {
  //     "id": product.id,
  //     "image": product.image,
  //     "itemName": product.name,
  //     "price": product.price,
  //     "stockCnt": product.stockCnt,
  //     "info": product.info
  //   }
  // }

  // const Patch = () => {
  //   fetch(`http://211.58.40.128:8080/api/v1/item/${itemInfo.itemId}`,{
  //     method: 'PATCH',
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //       body: JSON.stringify(allChange)
  //     })
  //     .then(() => {
  //       window.location.reload("/")
  //     })
  //     .catch(() => {
  //       console.log("실패")
  //     })
  // }

  // const Delete = () => {
  //   fetch(`http://211.58.40.128:8080/api/v1/item/${itemInfo.itemId}`, {
  //     method: 'DELETE'
  //   })
  //   .then(() => {
  //     // window.location.reload();
  //   })
  //   .catch(() => {
  //     alert('실패')
  //   })
  // }

  return (
    <Wrapper>
      
    {/* {sessionStorage.getItem('accessToken') ?
      <div className='deleteBox'>
      <span className='patch' onClick={Patch}>상품 수정</span>
      <span className='delete' onClick={Delete}>상품 삭제</span>
      </div>
      :
      undefined
    } */}

    <div className="item_top">
          <>
            <div className="item_imagebox">
              <img src={final}
               className="itemImage" alt="cat"></img>
            </div>
            
            <div className="item_nameBox">{itemInfo.itemName}</div>
            <div className="item_infoBox">{itemInfo.info}</div>
          </>


            <div className="item_bottom">
              <div className="amount">
                <img
                  className="minus"
                  src={Minus}
                  alt="minus"
                  onClick={() => handleQuantity("minus")}
                />

                <div className="count">
                  <span>{count}</span>
                </div>

                <img
                  className="plus"
                  src={Plus}
                  alt="plus"
                  onClick={() => handleQuantity("plus")}
                />
                </div>

            <div className="sum">
                  <div>
                    <span className="sum_price">총 상품 금액</span>
                  </div>

                  <div className="total_info">
                    
                    <span className="total">
                      총 수량 <span className="total_count">{count}개</span>
                    </span>

                    <span className="total_price">
                      {convertPrice(itemInfo.price * count)}
                      <span className="total_unit">원</span>
                    </span>

                  </div>
                </div>

                <div className="btn">
                  <button className="btn_buy">바로 구매</button>

                  <button
                    className="btn_cart"
                    onClick={handleCart}>
                    장바구니
                  </button>
                </div>

            </div>
    </div>

    
    </Wrapper>
  )
}

export default Items