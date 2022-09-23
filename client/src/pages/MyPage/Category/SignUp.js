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
  height: 120vh;

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
.suBackground{

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.suback{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.sutext{
  width: 30vw;
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
            <form className='suBackground' onSubmit={handleSubmit(onSubmit)}>
              <div className='subox'>
                <label>이메일</label>
                <input className="sutext" type='email' {...register("email")}></input>

                <label>아이디</label>
                <input className="sutext" type='id' {...register("memberId")}></input>
              
                <label>비밀번호</label>
                <input className="sutext" type='password' {...register("password")}></input>

                <label>이름</label>
                <input className="sutext" type='text' {...register("memberName")}></input>

                <label>닉네임</label>
                <input className="sutext" type='text' {...register("nickName")}></input>
              
                <label>주소</label>
                <input className="sutext" type='text' {...register("address")}></input>

                <label>전화번호</label>
                <input className="sutext" type='text' {...register("phone")}></input>
            </div>

              {/* <label>프로필 이미지</label>
              <imput className="sutext" type="img" {...register("profile_img")}></imput> */}

              {/* 회원가입 버튼 */}
              <input className='suButton' type="submit"></input>
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