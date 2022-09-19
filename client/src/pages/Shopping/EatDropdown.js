import React from 'react'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

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

  const navigate = useNavigate();

  const onClickMeal = () => {
    navigate(`/shopping/meal`);
  }

  const onClickCookie = () => {
    navigate(`/shopping/cookie`);
  }

  return (
    <Wrapper>
      <div className='eat'>
        <span className='meal' onClick={onClickMeal}>사료</span>
        <span className='cookie' onClick={onClickCookie}>간식</span>
      </div>
    </Wrapper>
  );
};

export default DropdownEat