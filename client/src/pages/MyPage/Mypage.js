import React from 'react'
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
// 마이페이지 본문
.mpContent{
    padding: 30px;
    overflow-y: scroll;
}
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

                        <StyledLink to='/mypage/myinfo'>
                            <div className='mpcBox'>
                                <div className='mpCategory'>내 정보 수정</div>
                            </div>
                        </StyledLink>

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

                        <StyledLink to='/mypage/order'>
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
                {/* 마이페이지 본문 */}
                <div className='mpContent'>My Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page contentMy Page content</div>
            </div>
        </div>
    </Wrapper>
  )
}

export default Mypage