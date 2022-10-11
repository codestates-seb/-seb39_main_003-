import React from 'react'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`

  .vitaBox {
    width: 100%;
    display: flex;
    background-color: beige;
  }

  .allVita {
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

  .vita {
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

function AllVita() {

  const navigate = useNavigate();

  const onClickEat = () => {
    navigate(`/shopping/meal`)
  }

  const onClickVita = () => {
    navigate(`/shopping/vita`)
  }

  const onClickAllVita = () => {
    navigate(`/shopping/allVita`)
  }

  const onClickPad = () => {
    navigate(`/shopping/pad`)
  }

  return (

    <Wrapper>

      <div className='categoryTab'>
        <span className='otherCategory line' onClick={onClickEat}>먹거리</span>
        <span className='otherCategory line' onClick={onClickVita}>건강관리</span>
        <span className='otherCategory' onClick={onClickPad}>각종용품</span>
      </div>

      <div className='vitaBox'>
        <span className='vita' onClick={onClickVita}>비타민</span>
        <span className='allVita' onClick={onClickAllVita}>종합영양제</span>
      </div>

    </Wrapper>
  
    )
}

export default AllVita;