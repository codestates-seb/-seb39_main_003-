import styled from "styled-components";
import React from "react";
import Reply from "./Reply";
import { useState, useEffect } from "react";
import { useLocation } from "react-router";
import LostDelete from "./LostDelete";

const Wrapper = styled.div`
  //게시글 확인 화면
  .replytettitory {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
  }
  .rbackground {
    flex: auto;
  }
  //게시글 내용
  .questions {
    width: 95vw;
    margin: 20px;
    padding: 20px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
  }
  .tquestions {
    width: 95vw;
    margin: 10px;
    padding: 10px;
    border-radius: 10px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    background-color: #eef1ff;
  }
  .tarticle {
    padding: 0px 30px;
    text-align: center;
    font-size: large;
    font-weight: 600;
  }
  .article {
    width: 95vw;
    padding: 30px;
    text-align: start;
  }
`;

function LostPost() {

  const location = useLocation();

  console.log(location);

  const [lostList, setLostList] = useState([]);

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/board/${location.state.id}`)
    .then((res) => res.json())
    .then((res) => {
      setLostList(res.data);
    })
    .catch((err) => console.log(err));
  }, []);

  return (
    <Wrapper>
      {/* 게시글 확인 화면 */}
      <div className="pagetettitory">

        <div className="pagebackground">

          <div className="questions">
            <div className="tquestions">
              <span className="tarticle">{lostList.nickName}</span>
              <span className="tarticle">{lostList.title}</span>
            </div>
              <span className="article">{lostList.boardContents}</span>
          </div>

          <LostDelete boardId={location.state.id}></LostDelete>
          {/* 댓글 작성란 및 작성된 댓글 목록 */}
          <Reply boardId={location.state.id}/>
        </div>
      </div>
    </Wrapper>
  )
}

export default LostPost