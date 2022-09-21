import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'


const Wrapper = styled.div`
box-sizing: border-box;
.signUpTerritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #EEF1FF;
}
.signUpBackground{
  width: 30vw;
  height: 600px;

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
.text{
  margin: 20px;
  text-align: center;
  font-weight: bolder;
}
.suBackground{
  margin: 20px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.sutext{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  text-align: start;
}
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
  return (
    <Wrapper>
      {/* 회원가입 페이지 */}
      <div className='signUpTerritory'>
        <div className='signUpBackground'>
          {/* 회원가입 본문 */}
          <h2 className='text'>Sign Up</h2>
          <div className='suBackground'>
            <input type="sutext" placeholder="아이디를 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input type="sutext" placeholder="비밀번호를 입력하세요"></input>
          </div>
          <div className='suBackground'>
            <input type="sutext" placeholder="닉네임을 입력하세요"></input>
          </div>
          {/* 회원가입 버튼 */}
          <StyledLink to="/"><button className='suButton'>Sign Up</button></StyledLink>
        </div>
      </div>
    </Wrapper>
  )
}

export default SignUp