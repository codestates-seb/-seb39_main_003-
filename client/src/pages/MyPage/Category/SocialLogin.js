import React from 'react'
import styled from 'styled-components'

const Wrapper = styled.div`
box-sizing: border-box;
.background{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
//간편 로그인 텍스트
.socialLoginText{
  margin: 20px 0px;
  font-size: larger;
  font-weight: bolder;
}
// 간편 로그인 영역2
.socialLoginBox{
  width: 20vw;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
//네이버_카카오_구글 순사(큰 박스 및 배경색)
.loginbox1{
  width: 18vw;
  height: 5vh;

  padding: 5px 10px;
  border-radius: 20px;
  margin-bottom: 10px;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  text-decoration: none;
  color: #f9f9f9;
  background-color: #2DB400;
}
.loginbox2{
  width: 18vw;
  height: 5vh;

  padding: 5px 10px;
  border-radius: 20px;
  margin-bottom: 10px;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  text-decoration: none;
  color: #000000;
  background-color: #F7E600;
}
.loginbox3{
  width: 18vw;
  height: 5vh;
  
  padding: 5px 10px;
  border-radius: 20px;
  margin-bottom: 10px;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  text-decoration: none;
  color: #f9f9f9;
  background: linear-gradient( to right, #4285F4, #FBBC05, #EA4335);
}
//박스 안 세부사항(아이콘 > 로그인)
.icon{
  font-weight: 900;
}
.textbox{
  margin-left: 20px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.text{
  font-size: medium;
  font-weight: 600;
}
`

function SocialLogin() {
  return (
    <Wrapper>
        <div>
            <div className='background'>
                <div className='socialLoginText'>간편 로그인</div>
                {/* 간편로그인 영역2 */}
                <div className='socialLoginBox'>
                    <a href='https://shopforourpets.shop:8080/oauth2/authorization/naver' className='loginbox1'>
                        <div className='icon'>N</div>
                        <div className='textbox'>
                          <div className='text'>네이버 로그인</div>
                        </div>
                    </a>
                    <a href='https://shopforourpets.shop:8080/oauth2/authorization/kakao' className='loginbox2'>
                    <div className='icon'>K</div>
                        <div className='textbox'>
                          <div className='text'>카카오 로그인</div>
                        </div>
                    </a>
                    <a href='http://shopforourpets.shop:8080/oauth2/authorization/google' className='loginbox3'>
                    <div className='icon'>G</div>
                        <div className='textbox'>
                          <div className='text'>구글 로그인</div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default SocialLogin