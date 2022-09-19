import React from 'react'
import styled from 'styled-components';

const Wrapper = styled.div`

  .eat {
    width: 400px;
    height: 50px;
    display: flex;
    justify-content: space-around;
    border-radius: 15px;
    align-items: center;
    background-color: beige;
    font-size: 20px;
    font-weight: bold;
    cursor: pointer;
    }
  
  .meal {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

  .cookie {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

`;

function DropdownEat() {
  return (
    <Wrapper>
      <div className='eat'>
        <span className='meal'>사료</span>
        <span className='cookie'>간식</span>
      </div>
    </Wrapper>
  );
};

export default DropdownEat