import React from 'react'
import CommunityNav from '../../components/CommunityNav';
import styled from 'styled-components';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

const Wrapper = styled.div`
  box-sizing: border-box;

  // 게시글 목록
  .cpostterritory {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .cpostterritory > .cbackground {
    padding: 30px 0px;
    margin: 20px;
    border-radius: 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
  }
  .cposttop {
    width: 95vw;
    margin-bottom: 20px;
    padding: 10px 10px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: rgb(146 99 255);
  }
  .cposttext {
    color: #f9f9f9;
    font-weight: bold;
  }
  .cpostmiddle {
    width: 90vw;
    padding: 10px 10px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .questions {
    //(구)cpost
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
  .title {
  }
  .content {
    text-decoration: none;
    color: #000000;
  }
  .user {
  }
  .cpostbottom {
    padding: 10px;
    margin-top: 10px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  .cpostpage {
    padding: 5px;

    text-decoration: none;
    color: #000000;
  }

  .categories {
    display: flex;
    justify-content: center;
    font-size: 24px;
    font-weight: bold;
  }

  .article {
    padding: 0px 20px 0px 20px;
  }

  /* .questions{
    //(구)questions
    padding: 5px 0px 5px 0px;
    margin: 20px 0px 20px 0px;
    width: 90vw;
    border: 1px solid lightgray;
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    cursor: pointer;
    border: 1px solid red;
} */
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

function Protect() {

    const [plist, setPList] = useState([]);

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/board?categoryId=14`, {
      method: "GET",
      headers: {
        "content-Type": "application/json",
      },
      //get요청이라 body는 필요없어
    })
      .then((res) => res.json())
      .then((res) => {
        setPList(res.data);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <Wrapper>
      <CommunityNav />

      <div className="categories">
            <span>임시보호 게시판</span>
        </div>

      {/* <!-- 게시글 목록 --> */}
      <div className="cpostterritory">
        <div className="cbackground">
          <div className="cposttop">
            <span className="cposttext">작성자</span>
            <span className="cposttext">글 제목</span>
            <span className="cposttext">내용</span>
          </div>

          <div>
            {plist &&
              plist.map((el, index) => {
                return (
                  <StyledLink
                  to={`/community/post/${el.boardId}`}
                    className="questions"
                    key={index} // 고유번호
                    state={{
                        id: el.boardId,
                    }}
                    // onClick={() => {
                    //   // 눌렀을때 어떻게 되어야할지 설정해주는 곳
                    //   // setId(el.id)
                    //   navigate(`/community/post/${el.boardId}`);
                    // }}
                  >
                    <span className="article">{el.nickName}</span>
                    <span className="article">{el.title}</span>
                    <span className="article">{el.boardContent}</span>
                  </StyledLink>
                );
              })}
          </div>

          {/* <div className="cpostmiddle">
                    <div className="cpost">
                        <span className="title">글 제목222</span>
                        <a href="/" className="content">내용222</a>
                        <span className="user">작성자222</span>
                    </div>
                </div> */}

          <div className="cpostbottom">
            <a href="/" className="cpostpage">
              1
            </a>
            <a href="/" className="cpostpage">
              2
            </a>
            <a href="/" className="cpostpage">
              3
            </a>
            <a href="/" className="cpostpage">
              4
            </a>
          </div>

          {/* 게시글 등록 버튼 */}
          <StyledLink to="/community/protectposting" className="postingbutton">
            <div>게시글 등록</div>
          </StyledLink>

        </div>
      </div>
    </Wrapper>
  )
}

export default Protect