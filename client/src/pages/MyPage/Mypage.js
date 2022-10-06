import React, { useState, useEffect } from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const Wrapper = styled.div`
// 마이페이지 화면
.myPageTerritory{
    height: 100vh;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    background-color: #B1B2FF;
}
.mpBackground{
    width: 90vw;
    height:90vh;
    margin: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.3);

    display: flex;
    flex-direction: row;
    justify-content: start;
    align-items: center;
    
    background-color: #f9f9f9;
}
// 마이페이지 사이드 네이게이션 목록
.mpSideNav{
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}
.mpsnbackground{
    width: 20vw;
    height: 90vh;
    border-radius: 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #EEF1FF;
}
.mpcBox{
    width: 100%;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.mpCategory{
    margin: 5px;
    padding: 20px;

    text-align: center;
    font-weight: 600;
}
// 내 정보 본문
.myinfoterritory{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.mitextbox{
  width: 100%;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}
.mitext{
  margin: 20px;
  padding: 20px;

  font-size: x-large;
  font-weight: bolder;
}
.micontentbox{
  width: 90%;
  padding: 20px;
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #EEF1FF;
}
// 사진 수정
.micontent{
  width: 100%;
  margin-top: 10px;
  padding: 10px;
  
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.misort{
  font-weight: bold;
}
.minow{}
.miedit{}
`

const StyledLink = styled(Link)`
    width: 100%;

    text-decoration: none;
    color: #000000;
    &:hover{
        background-color: #B1B2ff;
        color: #f9f9f9;
}
`
function Mypage() {

  const token = sessionStorage.getItem('accessToken');
  const realToken = token.slice(7)
  // console.log(realToken)
  
  window.Buffer = window.Buffer || require("buffer").Buffer; 
  
  const base64Payload = realToken.split('.')[1]; //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
  const payload = Buffer.from(base64Payload, 'base64'); 
  const result = JSON.parse(payload.toString())

  // console.log(result);


    const [info, setInfo] = useState([]);
    
    useEffect(() => {

      fetch(`http://211.58.40.128:8080/api/v1/member/${result.memberId}`)

      .then(res => res.json())
      .then(res => {
        setInfo(res)
        // console.log(res)
      })
    } , [])
    
    const navigate = useNavigate();


    const handleButtonLogout = () => {
      axios.post(`http://211.58.40.128:8080/logout?test@naver.com`, 
        sessionStorage.removeItem('refreshToken'),
        sessionStorage.removeItem('accessToken'),
        navigate('/'),
        window.location.reload()
      )
    };

  return (
    <Wrapper>
        {/* 마이페이지 화면 */}
        <div className='myPageTerritory'>
            <div className='mpBackground'>
                {/* 마이페이지 사이드 네비게이션 목록 */}
                <div className='mpSideNav'>
                    <div className='mpsnbackground'>
                        
                        <StyledLink to='/mypage'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>마이페이지</div>
                            </div>
                        </StyledLink>

                        {/* <StyledLink to='/mypage/myinfo'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>내 정보 수정</div>
                            </div>
                        </StyledLink> */}

                        <StyledLink to='/mypage/cart'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>장바구니</div>
                            </div>
                        </StyledLink>

                        <StyledLink to='/mypage/wish'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>찜 목록</div>
                            </div>
                        </StyledLink>

                        <StyledLink to='/mypage/buy'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>내 주문 내역</div>
                            </div>
                        </StyledLink>

                        <StyledLink to='/'>
                            <div className='mpcBox'  onClick={handleButtonLogout}>
                                <div className='mpCategory'> 로그아웃 </div>
                            </div>
                        </StyledLink>
                    </div>
                </div>
                {/* 내 정보 본문 */}
                <div className='myinfoterritory'>
                  <div className='mitextbox'>
                    <div className='mitext'>기본 회원 정보</div>
                  </div>

                  <div className='micontentbox'>
                    
                    {/* 사진 수정 */}
                    <div className='micontent'>
                      <span className='misort'>사진</span>
                      <span className='minow'>dd</span>
                      {/* <button className='miedit'>수정</button> */}
                    </div>
                    
                    {/* 이메일 수정 */}
                    <div className='micontent'>
                      <span className='misort'>이메일</span>
                      <span className='minow'>{info.email}</span>

                      {/* <button className='miedit'>수정</button> */}

                    </div>

                    {/* 비밀번호 수정 */}
                    <div className='micontent'>
                      <span className='misort'>비밀번호</span>
                      <span className='minow'>dd</span>
                      {/* <button className='miedit'>수정</button> */}
                    </div>

                    {/* 닉네임 수정 */}
                    <div className='micontent'>
                      <span className='misort'>닉네임</span>
                      <span className='minow'>{info.nickName}</span>
                      {/* <button className='miedit'>수정</button> */}
                    </div>

                    {/* 실명 수정 */}
                    <div className='micontent'>
                      <span className='misort'>실명</span>
                      <span className='minow'>dd</span>
                      {/* <button className='miedit'>수정</button> */}
                    </div>

                    {/* 전화번호 수정 */}
                    <div className='micontent'>
                      <span className='misort'>전화번호</span>
                      <span className='minow'>dd</span>
                      {/* <button className='miedit'>수정</button> */}
                    </div>

                    <button onClick={() => {
                      navigate(`/mypage/change`)
                    }}>회원 정보 수정</button>
                  </div>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default Mypage