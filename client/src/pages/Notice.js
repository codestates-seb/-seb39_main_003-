import React from 'react'
import styled from 'styled-components';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import noticeData from '../dummytest/noticeData';

const Wrapper = styled.div`
box-sizing: border-box;
.noticeterritory{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.nbackground{
  padding: 30px 0px;
  margin: 20px;
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #F9F9F9;
}
.textbox{
  width: 100%;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}
.text{
  margin: 20px;
  padding: 20px;

  font-size: x-large;
  font-weight: bolder;
}
.ntop{
  width: 95vw;
  margin-bottom: 20px;
  padding: 10px 10px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  background-color: #9263FF;
}
.ntext{
  color: #F9F9F9;
  font-weight: bold;
}
.questions{
  width: 90vw;
  margin: 20px 0px 20px 0px;
  padding: 10px 10px;
  border-radius: 20px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  cursor: pointer;
  background-color: #EEF1FF;
}
.article{}
.nbottom{
  padding: 10px;
  margin-top: 10px;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.npage{
  padding: 5px;

  text-decoration: none;
  color: #000000;
}
`

function Notice() {

  const navigate = useNavigate();

  return (
    <Wrapper>
        {/* <!-- 공지사항 목록 --> */}
        <div className="noticeterritory">
            <div className="nbackground">
              <div className='textbox'>
                <div className='text'>공지사항</div>
              </div>
                <div className="ntop">
                    <span className="ntext">순번</span>
                    <span className="ntext">제목</span>
                    <span className="ntext">작성자</span>
                    <span className="ntext">날짜</span>
                </div>
                
                    <div>
                        {noticeData.map((el, index) => (

                            <div className="questions"
                                 key={index}
                                 onClick={
                                    () => {
                                    navigate(`/notice ${el.id}`)
                                    }
                                    }
                                    >
                                <span className="article">{el.id}</span>
                                <span className="article">{el.title}</span>
                                <span className="article">{el.content}</span>
                                <span className="article">{el.date}</span>
                            </div>
                        ))}
                    </div>

                <div className="nbottom">
                    <a href="/" className="npage">1</a>
                    <a href="/" className="npage">2</a>
                    <a href="/" className="npage">3</a>
                    <a href="/" className="npage">4</a>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default Notice;