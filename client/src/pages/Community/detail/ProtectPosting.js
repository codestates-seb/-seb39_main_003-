import styled from "styled-components";
import React from "react";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Wrapper = styled.div`
  //게시글 포스팅 등록 화면
  .postingterritory {
    padding: 10px;
    border-radius: 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #f9f9f9;
  }
  .pbackground {
    width: 90vw;
    height: 80vh;
    padding: 20px;
    margin-top: 30px;
    border-radius: 20px;

    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;

    background-color: #eef1ff;
  }
  //게시글 제목
  .posttitle {
    width: 90vw;
    padding: 0px 20px;

    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;

    background-color: #eef1ff;
  }
  .title {
    padding: 10px 20px;

    font-weight: bold;
  }
  .titletext {
    width: 80vw;

    border: none;
    outline: none;
    border-bottom: 1px solid #b1b2ff;
    background-color: #f9f9f9;
  }
  //게시글 내용
  .postcontent {
    width: 90vw;
    padding: 20px 20px;

    display: flex;
    flex: row;
    justify-content: center;
    align-items: center;
  }
  .contenttext {
    width: 85vw;
    height: 60vh;

    border: none;
    outline: none;
    border-bottom: 1px solid #b1b2ff;
    background-color: #f9f9f9;
  }
  //게시글 등록 버튼
  /* .postbutton{
  margin-bottom: 20px;
} */
.postbuttontext{
  width: 15vw;
  height: 10vh;
  border-radius: 10px;
  margin: 20px;
  box-shadow: 1px 2px 2px lightgray;

  border-style: none;
  background-color: #EEF1FF;
  color: #000000;
  font-weight: bold;
  &:hover{
    background-color: #9263FF;
    color: #f9f9f9;
  }
  }
`;
const StyledLink = styled(Link)`
  text-decoration: none;

  width: 10vw;
  margin-top: 20px;
  padding: 20px;
  border-radius: 20px;
  box-shadow: 1px 2px 2px lightgray;

  background-color: #eef1ff;
  text-align: center;
`;

function ProtectPosting() {

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [list, setList] = useState([]);
  const navigate = useNavigate();

  const titleInput = (e) => {
    setTitle(e.target.value);
  };
  const contentInput = (e) => {
    setContent(e.target.value);
  };

  const postContent = () => {
    fetch(`https://shopforourpets.shop:8080/api/v1/board`, {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify({
        "title" : title,
        "boardContents" : content,
        "memberId" : "000001",
        "categoryId" : 14
      }),
    })
      .then(() => {
        navigate(`/community/protect`);
      })
      .catch(() => {
        console.log("실패");
      });
  };

  return (
    <Wrapper>
      {/* 게시글 포스팅 등록 화면 */}
      <div className="postingterritory">
        <div className="pbackground">
          {/* 게시글 제목 */}
          <div className="posttitle">
            <span className="title">글 제목</span>
            <input
              className="titletext"
              type="text"
              onChange={titleInput}
              placeholder="내용을 입력해주세요."
            ></input>
          </div>
          {/* 게시글 내용 */}
          <div className="postcontent">
            <input
              className="contenttext"
              type="text"
              onChange={contentInput}
              placeholder="내용을 입력해주세요."
            ></input>
          </div>
        </div>
        {/* 게시글 등록 버튼 */}
        <button className="postbuttontext" onClick={postContent}>
          등록
        </button>
      </div>
    </Wrapper>
  )
}

export default ProtectPosting