import React, { useState } from 'react'
import styled from 'styled-components';
import Minus from './images/icon-minus-line.svg';
import Plus from './images/icon-plus-line.svg';
import Cat from './images/image007.png';
import { useParams } from "react-router-dom";
// import ItemData from '../../dummytest/dummyData';


const Wrapper = styled.div`

  display: flex;
  box-sizing: border-box;
  justify-content: center;
  flex-direction: column;

  .item_top {
    width: 100%;
    height: 40rem;
    /* border: 2px solid red; */
    display: flex;
    flex-direction: column;
    padding-top: 50px;
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
    width : 90%;
    height: 25rem;
    /* border: 2px solid blue; */
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .item_namebox {
    width: 90%;
    height: 5rem;
    /* border: 2px solid green; */
    display: flex;
    align-items: center;
    padding-left: 2rem;
    font-size: 2rem;
    font-weight: 600;
  }

  .item_pricebox {
    width: 90%;
    height: 5rem;
    /* border: 2px solid gray; */
    display: flex;
    align-items: center;
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
  margin-bottom: 30px;
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

.catImage {
  background-size: cover;
  width : 90%;
  height: 25rem;
  /* border: 2px solid blue; */
  display: flex;
  justify-content: center;
  align-items: center;
}
`;

function Items( { convertPrice, cart, setCart } ) {

  // const [test, setTest] = useState(ItemData);
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [count, setCount] = useState(1);

  // 상세페이지에서 물건 수량 조절
  const handleQuantity = (type) => {
    if (type === "plus") {
      setCount(count + 1);
    } else {
      if (count === 1) return;
      setCount(count - 1);
    }
  };

  const setQuantity = (id, quantity) => {
    const found = cart.filter((el) => el.id === id)[0];
    const idx = cart.indexOf(found);
    const cartItem = {
      id: product.id,
      image: product.image,
      name: product.name,
      quantity: quantity,
      price: product.price,
      provider: product.provider,
    };
    setCart([...cart.slice(0, idx), cartItem, ...cart.slice(idx + 1)]);
  };

  const handleCart = () => {
    const cartItem = {
      id: product.id,
      image: product.image,
      name: product.name,
      quantity: count,
      price: product.price,
      provider: product.provider,
    };
    const found = cart.find((el) => el.id === cartItem.id);
    if (found) setQuantity(cartItem.id, found.quantity + count);
    else setCart([...cart, cartItem]);
  };
    // useEffect(() => {
  //   fetch(`도메인주소/${item.id}`)
  //   .then((res) => res.json())
  //   .then(res => {
  //     setTest(res)
  //   })
  // } , [])

  return (
    <Wrapper>
      
    <div className='item_top'>

      <div className='item_imagebox'>
        <img src={Cat} className='catImage' alt="cat"></img>
      </div>
      
      <div className='item_namebox'>고양이 장난감</div>
      <div className='item_pricebox'>3000</div>


    </div>





    <div className='item_bottom'>
      <div className='amount'>
        <img
          className='minus'
          src={Minus}
          alt="minus"
          onClick={() => handleQuantity("minus")}
        />

        <div className='count'>
          <span>{count}</span>
        </div>

        <img
          className='plus'
          src={Plus}
          alt="plus"
          onClick={() => handleQuantity("plus")}
        />
        </div>

    <div className='sum'>
          <div>
            <span className='sum_price'>총 상품 금액</span>
          </div>

          <div className='total_info'>
            <span className='total'>
              총 수량 <span className='total_count'>{count}개</span>
            </span>
            <span className='total_price'>
              {convertPrice(3000 * count)}
              <span className='total_unit'>원</span>
            </span>
          </div>
        </div>

        <div className='btn'>
          <button className='btn_buy'>바로 구매</button>
          <button
            className='btn_cart'
            onClick={() => {
              handleCart();
            }}
          >
            장바구니
          </button>
        </div>


    </div>
    
    </Wrapper>
  )
}

export default Items