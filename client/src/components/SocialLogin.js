import React from 'react'
import styled from 'styled-components'

const Wrapper = styled.div`
box-sizing: border-box;

`

function SocialLogin() {
  return (
    <Wrapper>
        <div>
            <div className='background'>
                <div>간편 로그인</div>
                <div className='socialLoginBox'>
                    <div className='loginbox1'>
                        
                    </div>
                    <div className='loginbox2'></div>
                    <div className='loginbox3'></div>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default SocialLogin