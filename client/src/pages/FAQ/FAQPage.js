import styled from "styled-components";
import React from "react";
import Reply from "../Community/detail/Reply";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router";
import FAQDelete from "./FAQDelete";

const Wrapper = styled.div`
  //게시글 확인 화면
  .pagetettitory {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color: #f9f9f9;
  }
  .pagebackground {
    flex: auto;
  }
  /* //게시글 제목
  .cposttop {
    width: 95vw;
    margin-top: 30px;
    padding: 10px 10px;

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;

    background-color: rgb(146 99 255);
  }
  .cposttext {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    color: #f9f9f9;
    font-weight: bold;
  }
  //게시글 내용
  .cpcontent {
    padding: 30px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
  }
  .cpostcontent {
    text-align: start;
    font-weight: 500;
  }
  */
  //
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

function FAQPage() {
  const navigate = useNavigate();
  const location = useLocation();

  console.log(location);

  const [faqList, setFaqList] = useState([]);

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/board/${location.state.id}`)
      .then((res) => res.json())
      .then((res) => {
        setFaqList(res.data);
        // console.log(res.data);
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
              <span className="tarticle">{faqList.nickName}</span>
              <span className="tarticle">{faqList.title}</span>
            </div>

            <span className="article">{faqList.boardContents}</span>
          </div>

          <FAQDelete boardId={location.state.id}></FAQDelete>
          {/* 댓글 작성란 및 작성된 댓글 목록 */}
          <Reply boardId={location.state.id} />
        </div>
      </div>
    </Wrapper>
  );
}

export default FAQPage;