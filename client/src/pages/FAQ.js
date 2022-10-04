import React from "react";
import styled from "styled-components";
// import faqData from '../dummytest/faqData'
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
// import FAQPost from './FAQ/FAQPost';

const Wrapper = styled.div`
  box-sizing: border-box;
  .faqterritory {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .faqbackground {
    padding: 30px 0px;
    margin: 20px;
    border-radius: 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
  }
  .textbox {
    width: 100%;

    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
  }
  .text {
    margin: 20px;
    padding: 20px;

    font-size: x-large;
    font-weight: bolder;
  }
  .faqtop {
    width: 95vw;
    margin-bottom: 20px;
    padding: 10px 10px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: #9263ff;
  }
  .faqtext {
    color: #f9f9f9;
    font-weight: bold;
  }
  .questions {
    width: 90vw;
    margin: 20px 0px 20px 0px;
    padding: 10px 10px;
    border-radius: 20px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    cursor: pointer;
    background-color: #eef1ff;
  }
  .article {
  }
  .faqbottom {
    padding: 10px;
    margin-top: 10px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  .faqpage {
    padding: 5px;

    text-decoration: none;
    color: #000000;
  }
  // 게시글 등록 버튼
  .postingbutton {
    width: 15%;

    padding: 5px;
    margin: 20px;
    border-radius: 20px;
    box-shadow: 1px 1px 1px lightgrey;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    background-color: #eef1ff;
    font-weight: bold;
  }
`;
const StyledLink = styled(Link)`
  color: black;
  text-decoration: none;

  &:hover {
    background-color: #9263ff;
    color: #f9f9f9;
  }
`;

function FAQ() {

  const navigate = useNavigate();

  const [list, setList] = useState([]);

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/board?tagIds=3`, {
      method: "GET",
      headers: {
        "content-Type": "application/json",
      },
      // body: JSON.stringify({"tagIds":[3]}),
    })
      .then((res) => res.json())
      .then((res) => {
        setList(res.data);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
  }, []);
  // useEffect(() => {
  //   fetch(`http://localhost:3000/FAQ`)
  //   .then(res => res.json())
  //   .then((res) => {
  //     setList(res)
  //   })
  //   .catch(() => console.log("실패"))
  // }, [])

  return (
    <Wrapper>
      {/* <!-- FAQ 목록 --> */}
      <div className="faqterritory">
        <div className="faqbackground">
          <div className="textbox">
            <div className="text">FAQ</div>
          </div>
          <div className="faqtop">
            <span className="faqtext">작성자</span>
            <span className="faqtext">제목</span>
            <span className="faqtext">순번</span>
            {/* <span className="faqtext">날짜</span> */}
          </div>

          {/* <div> */}

          {list && list.map((el, index) => {
              return (
                <div
                  className="questions"
                  key={index}
                  onClick={() => {
                    navigate(`/FAQ/${el.boardId}`);
                  }}
                >
                  <span className="article">{el.nickName}</span>
                  <span className="article">{el.title}</span>
                  <span className="article">{el.boardContents}</span>
                </div>
              );
            })}
          {/* </div> */}

          <div className="faqbottom">
            <a href="/" className="faqpage">
              1
            </a>
            <a href="/" className="faqpage">
              2
            </a>
            <a href="/" className="faqpage">
              3
            </a>
            <a href="/" className="faqpage">
              4
            </a>
          </div>

          {/* 게시글 등록 버튼 */}
          <StyledLink to="/FAQ/FAQPost" className="postingbutton">
            <div>게시글 등록</div>
          </StyledLink>
        </div>
      </div>
    </Wrapper>
  );
}

export default FAQ;
