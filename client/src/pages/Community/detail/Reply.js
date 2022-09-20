import React from 'react'
import styled from 'styled-components'
// import { useState } from 'react'
import CommuData from '../../../dummytest/dummyData';
import { Link } from "react-router-dom";


const Wrapper = styled.div`

`;

const StyledLink = styled(Link)`
    text-decoration: none;
    padding: 5px 0px 5px 0px;
    /* margin: 20px 0px 20px 0px; */
    width: 90vw;
    /* border: 1px solid lightgray; */
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    color: black;
`;
// 1. 각 게시물마다 달린 댓글들이 다 다를것이다.
// 2. 각 게시물마다 고유의 아이디(key값)이 필요할 것이다.
// 2-1. 해당 아이디에 속하는 댓글들을 불러와야 하기 때문이다.
// 3. 댓글들을 저장해둔 더미데이터를 불러와서 사용해야 한다.

export default function Reply() {

  // const [reply, setReply] = useState(["울집 갱쥐도 그럼"]);
  // const [input, setInput] = useState("");

  return (
    <Wrapper>
      <div>

        {/* 댓글 작성란 */}
        <div className='replyTerritory'>
          <input type="text"></input>
          <button>등록</button>
        </div>

        {/* 작성된 댓글 목록 */}
        <div className='replyListTerritory'>
          
          {/* {reply.map(reply => {
            return <p>{reply}</p>
          })
          } */}
          {CommuData.map(
            (el, index) => (
            <div className="questions" key={index}>
                <span className="article">{el.username}</span>
                <span className="article">{el.content}</span>
            </div>
            ))}
        </div>

      </div>
    </Wrapper>
  )
}
