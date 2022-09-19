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
  
  .pad {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

  .toy {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

`;

function DropdownEat() {

  const navigate = useNavigate();

  const onClickPad = () => {
    navigate(`/shopping/pad`);
  }

  const onClickToy = () => {
    navigate(`/shopping/toy`);
  }

  return (
    <Wrapper>
      <div className='eat'>
        <span className='pad' onClick={onClickPad}>배변패드</span>
        <span className='toy' onClick={onClickToy}>장난감</span>
      </div>
    </Wrapper>
  );
};

export default DropdownEat