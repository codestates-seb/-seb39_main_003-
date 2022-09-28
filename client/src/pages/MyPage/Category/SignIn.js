/* eslint-disable no-use-before-define */
import React from 'react'
import styled from 'styled-components'
// import { Link } from 'react-router-dom'
import { useForm } from "react-hook-form";
import axios from "axios";
import { useNavigate } from 'react-router-dom';


const Wrapper = styled.div`
box-sizing: border-box;
// 로그인 페이지
.signInTerritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #EEF1FF;
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
// 회원가입 본문
.text{
  margin: 30px;
  text-align: center;
  font-weight: bolder;
}
.siBackground{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.sibox{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.siback{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
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
// 로그인 버튼
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
`
// const StyledLink = styled(Link)`
// text-decoration: none;
// `

function SignIn() {

  let sessionStorage = window.sessionStorage;

  console.log(sessionStorage.getItem("accessToken"))

  const navigate = useNavigate();

  const { register, handleSubmit } = useForm();
  axios.defaults.withCredentials = true;
  
  const onSubmit = (data) => {
    axios.post(`http://211.58.40.128:8080/login`, data)
    .then(response => {
      const accessToken = response.headers.authorization;
      const refreshToken = response.headers.refresh;
      
      // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
      axios.defaults.headers.common['Authorization'] = `${accessToken}`;
      axios.defaults.headers.common['Authorization'] = `${refreshToken}`;
      
      sessionStorage.setItem('accessToken', `${accessToken}`);
      sessionStorage.setItem('refreshToken', `${refreshToken}`);
      
      
    })

    .then(() => {
      navigate('/');
      window.location.reload();
    })
    
    // .catch((err) => {
    //   console.log(err.response.headers.Authorization);
    //   return "이메일 혹은 비밀번호를 확인하세요";
    // })
    // .catch(err => console.err(err));
  }

  return (
    <Wrapper>
      {/* 로그인 페이지 */}
      <div className='signInTerritory'>
        <div className='signInBackground'>
          
          {/* 로그인 본문 */}
          <h2 className='text'>Sign In</h2>
          <div className='siBackground'>
            <form className='siBackground' onSubmit={handleSubmit(onSubmit)}>
              <div className='sibox'>
                <div className='siback'>
                  <label>아이디</label>
                  <input className="sitext" type='email' {...register("email")}></input>
                </div>

                <div className='siback'>
                  <label>비밀번호</label>
                  <input className="sitext" type='password' {...register("password")}></input>
                </div>
            </div>
              {/* 로그인 버튼 */}
              <input className='siButton' type="submit"></input>
            </form>
          </div>
        </div>
      </div>
    </Wrapper>
  )
}

export default SignIn