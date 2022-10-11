import React from 'react'
import styled from "styled-components";
import { useState } from "react";
import { useLocation } from "react-router-dom";

const Wrapper = styled.div`
.deleteBackground{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;

}
.deleteButton{
    width: 20%;

    padding: 5px;
    margin: 20px;
    border-radius: 20px;
    border: none;
    box-shadow: 1px 1px 1px lightgrey;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    background-color: #eef1ff;
    font-weight: bold;

    &:hover {
    box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
    color: black;
    font-weight: 600;
  }
}`;

function InfoDelete() {
    const [faqlist, setFaqList] = useState([]);
    const location = useLocation();
  
    const deleteContent = () => {
      fetch(`http://211.58.40.128:8080/api/v1/board/${location.state.id}`, {
        method: "DELETE",
        headers: {
          // 'Authorization' : Token,
          "content-Type": "application/json",
        },
        body: JSON.stringify({
          memberId: "000001",
          categoryId: 12,
        }),
      })
        .then(() => {
          window.location.assign('/community/info');
        })
        .catch(() => {
          console.log("실패");
        });
    };

  return (
    <Wrapper>
        <div className="deleteBackground">
          <button className="deleteButton" onClick={deleteContent}>
            게시글 삭제
          </button>
        </div>
      </Wrapper>
  )
}

export default InfoDelete