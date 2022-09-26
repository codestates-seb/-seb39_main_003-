import React from 'react'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`

  .health {
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
  
  .vita {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

  .allVita {
    transition: .3s;

    &:hover {
      color: #EE6983;
    font-size: 25px;
    }
  }

`;

function DropdownEat() {

  const navigate = useNavigate();

  const onClickVita = () => {
    navigate(`/shopping/vita`);
  }

  const onClickAllVita = () => {
    navigate(`/shopping/allVita`);
  }

  return (
    <Wrapper>
      <div className='health'>
        <span className='vita' onClick={onClickVita}>비타민</span>
        <span className='allVita' onClick={onClickAllVita}>종합영양제</span>
      </div>
    </Wrapper>
  );
};

export default DropdownEat