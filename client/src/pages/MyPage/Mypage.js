import React from 'react'
import styled from 'styled-components'
import { Link } from 'react-router-dom'

const Wrapper = styled.div`
.myPageTerritory{}
.mpBackground{}
.mpSideNav{}
.mpsnbackground{}
.mpCategory{}
.mpContent{}
`

const StyledLink = styled(Link)`
text-decoration: none;
`

function Mypage() {
  return (
    <Wrapper>
        {/* 마이페이지 화면 */}
        <div className='myPageTerritory'>
            <div className='mpBackground'>
                {/* 마이페이지 사이드 네비게이션 목록 */}
                <div className='mpSideNav'>
                    <div className='mpsnbackground'>
                        <div className='mpCategory'>내 정보 수정</div>
                        <div className='mpCategory'>장바구니</div>
                        <div className='mpCategory'>고객센터</div>
                        <div className='mpCategory'><StyledLink to="/mypage/signup">회원 가입</StyledLink></div>
                    </div>
                </div>
                {/* 마이페이지 본문 */}
                <div className='mpContent'>My Page content</div>
            </div>
        </div>
    </Wrapper>
  )
}

export default Mypage