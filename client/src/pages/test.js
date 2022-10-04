import styled from "styled-components";
import React from "react";

const Wrapper = styled.div`
  width: 100%;
  height: 300px;
  border: 3px solid red;
  background-color: green;
  display: flex;
  flex-direction: row;
  justify-content: space-around;

  .project {
    width: 300px;
    height: 300px;
    border: 3px solid blue;
    font-size: 50px;
    color: red;
  }

  #pro {
    width: 300px;
    height: 300px;
    border: 3px solid red;
    font-size: 100px;
    color: blue;
  }
`;

const Test = () => {
  
  return (
    <Wrapper>
      <span className="project">플젝 화이팅 !!</span>
      <span id="pro">플젝 화이팅 !!</span>
    </Wrapper>
  )
}

export default Test;