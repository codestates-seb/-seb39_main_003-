import React from 'react'
import styled from 'styled-components';

  const Wrapper = styled.div`
    display: flex;
    justify-content: left;

  `;

function UserInfo() {
  return (
    <Wrapper>
      <div className='infoBox'>
        <span className='info'>내 정보</span>
        <span className='info'>장바구니</span>
        <span className='info'>고객센터</span>
        <span className='info'>로그인</span>
      </div>
    </Wrapper>
  )
}

export default UserInfo