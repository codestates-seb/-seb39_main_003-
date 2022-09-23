import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'
import { useForm } from "react-hook-form";

const Wrapper = styled.div`
box-sizing: border-box;
// 회원가입 페이지
.signUpTerritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #EEF1FF;
}
.signUpBackground{
  width: 40vw;
  height: 100vh;

  margin: 30px 0px;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.7);
  overflow-y: scroll;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #F9F9F9;
}
// 회원가입 본문
.text{
  margin: 20px;
  text-align: center;
  font-weight: bolder;
}
.suBackground{

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.sutext{
  margin: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  text-align: start;
}
// 회원가입 버튼
.suButton{
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
const StyledLink = styled(Link)`
text-decoration: none;
`

function SignUp() {
  const { register, handleSubmit } = useForm();
  const onSubmit = (data) => console.log(data)

  return (
    <Wrapper>
      {/* 회원가입 페이지 */}
      <div className='signUpTerritory'>
        <div className='signUpBackground'>
          
          {/* 회원가입 본문 */}
          <h2 className='text'>Sign Up</h2>
          <div className='suBackground'>
            <form onSubmit={handleSubmit(onSubmit)}>
              <label>이메일</label>
              <input className="sutext" type='email' {...register("email")}></input>
              <input type="submit" />
            </form>
          </div>
          {/* <div className='suBackground'>
            <input id="id" className="sutext" type='id' placeholder="사용할 아이디를 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input id="password" className="sutext" type='password' placeholder="비밀번호를 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input id="passwordCheck" className="sutext" type='password' placeholder="비밀번호 입력 확인"></input>
          </div>
          <div className='suBackground'>
            <input id="userName" className="sutext" type='text' placeholder="이름을 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input id="nickName" className="sutext" type='text' placeholder="닉네임을 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input id="address" className="sutext" type='text' placeholder="주소를 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input id="phoneNumber" className="sutext" type='tel' placeholder="전화번호를 입력하세요"></input>
          </div> */}

          {/* 회원가입 버튼 */}
          {/* <StyledLink to="/"><button className='suButton'>Sign Up</button></StyledLink> */}
        </div>
      </div>
    </Wrapper>
  )
}

export default SignUp