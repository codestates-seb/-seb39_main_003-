import React from 'react'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`

  .eatBox {
    width: 100%;
    display: flex;
    background-color: beige;
  }

  .cookie {
    width: 50%;
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-left: 2px solid purple;
    background-color: #EEE3CB;
    font-size: 23px;
    color: #850E35;
    font-weight: bold;
    cursor: pointer;
  }

  .eat {
    width: 50%;
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    font-weight: 500;
    cursor: pointer;

    &:hover {
      background-color: #EEE3CB;
      font-size: 23px;
      color: #850E35;
      font-weight: bold;
    }
  }

  .categoryTab {
    width: 100%;
    height: 40px;
    display: flex;
  }

  .otherCategory {
    width: 100%;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
    font-weight: 500;
    border-bottom: 1.5px solid lightgray;
    cursor: pointer;

    &:hover {
      color: purple;
      font-weight: bold;
      background-color: #EEEEEE;
    }
  }

  .line {
    border-right: 2px solid purple;
  }
`;

function Cookie() {

  const navigate = useNavigate();

  const onClickPresent = () => {
    navigate(`/shopping/meal`)
  }

  const onClickVita = () => {
    navigate(`/shopping/vita`)
  }

  const onClickPad = () => {
    navigate(`/shopping/pad`)
  }

  const onClickMeal = () => {
    navigate(`/shopping/meal`)
  }

  return (

    <Wrapper>

      <div className='categoryTab'>
        <span className='otherCategory line' onClick={onClickPresent}>먹거리</span>
        <span className='otherCategory line' onClick={onClickVita}>건강관리</span>
        <span className='otherCategory' onClick={onClickPad}>각종용품</span>
      </div>


      <div className='eatBox'>
        <span className='eat' onClick={onClickMeal}>사료</span>
        <span className='cookie'>간식</span>
      </div>
    </Wrapper>
  
    )
}

export default Cookie