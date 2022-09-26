import styled from 'styled-components';
import React from 'react'
import Reply from './Reply';

const Wrapper = styled.div`
//게시글 확인 화면
.replytettitory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #F9F9F9;
} 
.rbackground{
  flex: auto;
}
//게시글 제목
.cposttop{
    width: 95vw;
    margin-top: 30px;
    padding: 10px 10px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: rgb(146 99 255);
}
.cposttext{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  color: #F9F9F9;
  font-weight: bold;
}
//게시글 내용
.cpcontent{
  padding: 30px;
  
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}
.cpostcontent{
  text-align: start;
  font-weight: 500;
}
`

function Post(props) {
  return (
    <Wrapper>
        {/* 게시글 확인 화면 */}
        <div className='replytettitory'>
            <div className='rbackground'>

                {/* 게시글 제목 */}
                <div className="cposttop">
                    <span className="cposttext">글 제목11</span>
                    <span className="cposttext">내용11</span>
                    <span className="cposttext">작성자11</span>
                </div>

                {/* 게시글 내용 */}
                <div className='cpcontent'>
                  <span className='cpostcontent'>My dog is a fluffy puppy. When I come back from school, it's woof woof woof. My dog is a fluffy puppy. When I come back from school, it's woof woof woof.</span>
                </div>
                
                {/* 댓글 작성란 및 작성된 댓글 목록 */}
                <Reply />

            </div>
        </div>
    </Wrapper>
  )
}

export default Post