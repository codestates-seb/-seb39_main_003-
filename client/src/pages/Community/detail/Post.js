import styled from "styled-components";
import React from "react";
import Reply from "./Reply";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router";
import Delete from "./Delete";

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

function Post() {
  const navigate = useNavigate();
  const location = useLocation();

  console.log(location);

  const [communityList, setCommunityList] = useState([]);

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/board/${location.state.id}`)
      .then((res) => res.json())
      .then((res) => {
        setCommunityList(res.data);
        // console.log(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <Wrapper>
      {/* 게시글 확인 화면 */}
      <div className="replytettitory">
        <div className="rbackground">
          {/* 게시글 제목 */}
          {/* <div className="cposttop">
                    <span className="cposttext">글 제목</span>
                    <span className="cposttext">내용</span>
                    <span className="cposttext">작성자</span>
                </div> */}

          {/* 게시글 내용 */}
          {/* <div className='cpcontent'>
                  <span className='cpostcontent'>FAQPage 개별 포스트 확인</span>
                </div> */}

          {/* {faqList &&faqList.map((el, index) => { */}

          {/* return ( */}
          <div
            className="questions"
            //   key={index} // 고유번호
          >
            <div className="tquestions">
              <span className="tarticle">{communityList.nickName}</span>
              <span className="tarticle">{communityList.title}</span>
            </div>
            <span className="article">{communityList.boardContents}</span>
          </div>
          {/* ); */}
          {/* })} */}

          <Delete boardId={location.state.id}></Delete>
          {/* 댓글 작성란 및 작성된 댓글 목록 */}
          <Reply boardId={location.state.id} />
        </div>
      </div>
    </Wrapper>
  );
}

export default Post;
