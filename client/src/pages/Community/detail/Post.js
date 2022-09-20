import styled from 'styled-components';
import React from 'react'
import Reply from './Reply';

const Wrapper = styled.div`
//게시글 확인 화면
.replytettitory{} 
.rbackground{}
//게시글 제목
.cposttop{
    width: 90vw;
    padding: 10px 10px;
    margin-bottom: 30px;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;

    background-color: rgb(146 99 255);
}
.cposttext{
    color: #F9F9F9;
    font-weight: bold;
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
                  <span className='cpostcontent'>내용</span>
                </div>
                
                {/* 댓글 작성란 및 작성된 댓글 목록 */}
                <Reply />

            </div>
        </div>
    </Wrapper>
  )
}

export default Post