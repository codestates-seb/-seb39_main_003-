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
  /* border: 1px solid green; */
}

.siback{
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding-top: 50px;
  /* border: 1px solid red; */
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

  /* border: 1px solid blue; */
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
  
  // const Token = sessionStorage.getItem('accessToken')


  const onSubmit = (data) => {
    const formData = new FormData();
    for(const key in data) {
      formData.append(key, data[key]);
    }
    formData.delete("mainImg");
    formData.append("mainImg", data.mainImg[0]);

    fetch(`http://211.58.40.128:8080/api/v1/item`, {
      method: "POST",
      body: formData
    })

      .then(() => {
        navigate('/shopping/meal')
        // window.location.reload();
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
                <label>회원 번호</label>
                <input className="sutext" type='text' {...register("memberId")}></input>
              </div>

              <div className='siback'>
                <label>상품 이름</label>
                <input className="sutext" type='text' {...register("itemName")}></input>
              </div>

              <div className='siback'>
                <label>상품 가격</label>
                <input className="sutext" type='text' {...register("price")}></input>
              </div>

              <div className='siback'>
                <label>재고량</label>
                <input className="sutext" type="text" {...register("stockCnt")} />
              </div>

              <div className='siback'>
                <label>제품 상세설명</label>
                <input className="sutext" type="text" {...register("info")} />
              </div>

              <div className='siback'>
                <label>카테고리</label>
                <input className="sutext" type="text" {...register("itemCategoryId")} />
              </div>

              <div className='siback'>
                <label>상품 이미지</label>
                <input className="sutext" type="file" {...register("mainImg")} />
              </div>

          </div>
              {/* 제출 버튼 */}
              <input className='siButton' type="submit"></input>
       </form>
    </Wrapper>
  
    )
}



export default AddProduct