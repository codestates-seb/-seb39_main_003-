import React from "react";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import NoticePage from "./Notice/NoticePage";

const Wrapper = styled.div`
  box-sizing: border-box;
  .noticeterritory {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .nbackground {
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
  .ntop {
    width: 95vw;
    margin-bottom: 20px;
    padding: 10px 10px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: #9263ff;
  }
  .ntext {
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
  .nbottom {
    padding: 10px;
    margin-top: 10px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  .npage {
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
    box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
    color: black;
    font-weight: 600;
  }
`;

function Notice() {
  const navigate = useNavigate();

  const [list, setList] = useState([]);

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/board?page=1&size=10&categoryId=21`)
      .then((res) => res.json())
      .then((res) => {
        setList(res.data);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <Wrapper>
      {/* <!-- 공지사항 목록 --> */}
      <div className="noticeterritory">
        <div className="nbackground">
          <div className="textbox">
            <div className="text">공지사항</div>
          </div>
          <div className="ntop">
            <span className="ntext">작성자</span>
            <span className="ntext">제목</span>
            <span className="ntext">순번</span>
            {/* <span className="ntext">날짜</span> */}
          </div>

          {/* <div> */}
          {list &&
            list.map((el, index) => {
              return (
                <StyledLink
                  to={`/notice/noticePage/${el.boardId}`}
                  className="questions"
                  key={index}
                  state={{
                    id: el.boardId,
                  }}
                  // onClick={() => {
                  //   navigate(`/notice/noticePost/${el.boardId}`);
                  // }}
                >
                  <span className="article">{el.nickName}</span>
                  <span className="article">{el.title}</span>
                </StyledLink>
              );
            })}
          {/* </div> */}

          {/* <div className="nbottom">
            <a href="/" className="npage">
              1
            </a>
            <a href="/" className="npage">
              2
            </a>
            <a href="/" className="npage">
              3
            </a>
            <a href="/" className="npage">
              4
            </a>
          </div> */}

          {/* 게시글 등록 버튼 */}
          <StyledLink to="/notice/noticepost" className="postingbutton">
            <div>게시글 등록</div>
          </StyledLink>
        </div>
      </div>
    </Wrapper>
  );
}

export default Notice;
