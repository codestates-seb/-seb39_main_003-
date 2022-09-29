import React from 'react'
import styled from 'styled-components';
import faqData from '../dummytest/faqData'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Wrapper = styled.div`
box-sizing: border-box;
.faqterritory{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.faqbackground{
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
.faqtop{
  width: 95vw;
  margin-bottom: 20px;
  padding: 10px 10px;

  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;

  background-color: #9263FF;
}
.faqtext{
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
.faqbottom{
  padding: 10px;
  margin-top: 10px;

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.faqpage{
  padding: 5px;

  text-decoration: none;
  color: #000000;
}
`

function FAQ() {

  const navigate = useNavigate();

  return (
    <Wrapper>
        {/* <!-- 공지사항 목록 --> */}
        <div className="faqterritory">
            <div className="faqbackground">
            <div className='textbox'>
                <div className='text'>FAQ</div>
              </div>
                <div className="faqtop">
                    <span className="faqtext">순번</span>
                    <span className="faqtext">제목</span>
                    <span className="faqtext">작성자</span>
                    <span className="faqtext">날짜</span>
                </div>
                
                    <div>
                        
                        {faqData.map((el, index) => (

                            <div className="questions"
                                 key={index}
                                 onClick={
                                    () => {
                                    navigate(`/FAQ ${el.id}`)
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

                <div className="faqbottom">
                    <a href="/" className="faqpage">1</a>
                    <a href="/" className="faqpage">2</a>
                    <a href="/" className="faqpage">3</a>
                    <a href="/" className="faqpage">4</a>
                </div>
            </div>
        </div>
    </Wrapper>
  )
}

export default FAQ;