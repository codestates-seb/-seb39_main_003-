import React from 'react'
import styled from 'styled-components';

const Wrapper = styled.div`
  width: 100%;
  height: 20rem;
  margin: 40px 0px 20px 0px;
  border: 1px solid red;

  .listBox {
    border: 1px solid blue;
  }
`;

// useState로 cartList setCartList 설정해서 초기값 배열로 담아주고
// GET 요청으로 장바구니 데이터에 있는 상품들 불러온 다음 
// map 함수 돌려서 장바구니 데이터에 추가된 상품들 하나씩 조회 가능하게끔 하기
function CartBody() {
  return (

    <Wrapper>
      <div className='listBox'>가나다라</div>
    </Wrapper>
  
  )
}

export default CartBody