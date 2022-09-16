import React from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  background-color: beige;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;

  div {
    font-size: 100px;
    font-weight: bold;
    color: #7FB77E;
    padding: 20px 0px 20px 0px;
  }
`;

function Main() {
  return (
    <Wrapper>
      <div>얼추 캐러셀 넣고 밑에</div>
      <div>각 메뉴별 설명이 포함된</div>
      <div>컨테이너 박스 들어갈거임</div>
    </Wrapper>
  )
}

export default Main;