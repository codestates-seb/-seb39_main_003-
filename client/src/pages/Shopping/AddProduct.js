import React from 'react'
import styled from 'styled-components'
import { useForm } from "react-hook-form";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`

.siBackground {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.signInBackground{
  width: 40vw;
  height: 90vh;

  margin: 30px 0px;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.7);

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #F9F9F9;
}

.sibox{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.siback{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  padding-top: 50px;
}

.sitext{
  width: 20vw;
  margin: 10px;
  border: none;
  outline:none;
  border-bottom: 2px solid #EEF1FF;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  text-align: start;
  background-color: #F9F9F9;
}
// 제출 버튼
.siButton{
  width: 10vw;
  height: 8vh;
  margin-top: 30px;

  /* box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.4); */
  border-radius: 5px;
  border-style: none;

  background-color: #000000;
  color: #9263FF;
  text-align: center;
  font-size: larger;
  font-weight: bolder;
  
  &:hover{
    background-color: #9263FF;
    color: #000000;
  }
}

.sutext{
  width: 20vw;
  margin: 10px;
  border: none;
  outline:none;
  border-bottom: 2px solid #EEF1FF;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  text-align: start;
  background-color: #F9F9F9;
}
`;

function AddProduct() {

  const navigate = useNavigate();
  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    fetch(`사료 데이터 주소`, {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(data)
    })

      .then(() => {
        navigate('/shopping/meal')
      })
      .catch(() => {
        console.log("오류남 다시하셈")
      });

    // axios.post(`사료 데이터 주소`, data)
    // .then(response => {
    //   const accessToken = response.headers.authorization;
    //   const refreshToken = response.headers.refresh;
      
    //   // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
    //   axios.defaults.headers.common['Authorization'] = `${accessToken}`;
    //   axios.defaults.headers.common['Authorization'] = `${refreshToken}`;
      
    //   sessionStorage.setItem('accessToken', `${accessToken}`);
    //   sessionStorage.setItem('refreshToken', `${refreshToken}`);
      
      
    // })

    // .then(() => {
    //   navigate('/');
    //   window.location.reload();
    // })
  }

  return (

    <Wrapper>
      <form className='siBackground' onSubmit={handleSubmit(onSubmit)}>
          <div className='sibox'>
              <div className='siback'>
                <label>상품 이름</label>
                <input className="sitext" type='text' {...register("itemName")}></input>
              </div>

              <div className='siback'>
                <label>상품 가격</label>
                <input className="sitext" type='text' {...register("itemPrice")}></input>
              </div>

              <div className='siback siback2'>
                <label>상품 이미지</label>
                <input className="sutext" type="file" {...register("image")} />
              </div>

          </div>
              {/* 제출 버튼 */}
              <input className='siButton' type="submit"></input>
       </form>
    </Wrapper>
  
    )
}



export default AddProduct