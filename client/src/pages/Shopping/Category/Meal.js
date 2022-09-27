import React, { useState } from 'react'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import ItemData from '../../../dummytest/ItemData';


const Wrapper = styled.div`

  .eatBox {
    width: 100%;
    display: flex;
    background-color: beige;
  }

  .eat {
    width: 50%;
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-right: 2px solid purple;
    background-color: #EEE3CB;
    font-size: 23px;
    color: #850E35;
    font-weight: bold;
    cursor: pointer;
  }

  .cookie {
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

  /* .test {
    width: 200px;
    height: 200px;
    border: 1px solid red;
  } */

  .item_list_box {
    width: 100%;
    height: 100%;
    border: 2px solid red;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
  }

  .item_box {
    width: 20%;
    height: 15rem;
    border: 1px solid gray;
    display: flex;
    flex-direction: column;
    justify-content: end;
    align-items: center;
    overflow-x: hidden;
    cursor: pointer;
    margin: 15px;
  }

  .test1 {
    width: 100%;
    display: flex;
    align-items: center;
    font-size: 1.1rem;
    font-weight: 500;
  }

  .test2 {
    width: 100%;
    /* border: 1px solid black; */
    font-size: 1.2rem;
    font-weight: bold;
  }

  .image {
    width: 16rem;
    height: 10rem;
    position: relative;
    bottom: 5%;
    border: 1px solid lightgray;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .item_list {
    margin: 0px 10px 0px 10px;
  }
`;





function Meal() {

  const [test, setTest] = useState(ItemData);

    // useEffect(() => {
  //   fetch(`http://ec2-52-79-180-182.ap-northeast-2.compute.amazonaws.com:8080/api/v1/member/1`)
  //   .then((res) => res.json())
  //   .then(res => {
  //     setTest(res)
  //   })
  // } , [])

  const navigate = useNavigate();

  const onClickCookie = () => {
    navigate(`/shopping/cookie`)
  }

  const onClickEat = () => {
    navigate(`/shopping/meal`)
  }

  const onClickVita = () => {
    navigate(`/shopping/vita`)
  }

  const onClickPad = () => {
    navigate(`/shopping/pad`)
  }
  
  let params = useParams();

  return (


    <Wrapper>

      <div className='categoryTab'>
        {/* 클릭된 카테고리 focus 효과 주기 */}
        <span className='otherCategory line' onClick={onClickEat}>먹거리</span>
        <span className='otherCategory line' onClick={onClickVita}>건강관리</span>
        <span className='otherCategory' onClick={onClickPad}>각종용품</span>
      </div>

      <div className='eatBox'>
        <span className='eat'>사료</span>
        <span className='cookie' onClick={onClickCookie}>간식</span>
      </div>

      {/* <div className='test'>{params.itemId}</div> */}

      <div className="item_list_box">
            {test.map((item, idx) => {
              return (

                <div key={idx} className='item_box' onClick={() => {
                  navigate(`/shopping/item/${item.id}`)
                }}>

                    <div className='image'>
                        <div>{item.image}</div>
                    </div>

                    <div className='test1'>
                        <div className="item_list">{item.name}</div>
                    </div>

                    <div className='test2'>
                        <div className="item_list">{item.price}원</div>
                    </div>

                </div>
              )
            })}
          </div>

    </Wrapper>
  
    )
}

export default Meal