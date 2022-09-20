import React from 'react'
import styled from 'styled-components'
import { useState } from 'react'

const Wrapper = styled.div`
`

export default function Reply() {

  const [reply, setReply] = useState(["울집 갱쥐도 그럼"]);
  const [input, setInput] = useState("");

  return (
    <Wrapper>
    <div>
      {/* 댓글 작성란 */}
      <div className='replyterritory'>
        <input></input>
        <button></button>
      </div>
      {/* 작성된 댓글 목록 */}
      <div className='replylistterritory'>
        {reply.map(reply => {
          return <p>{reply}</p>
        })
        }
      </div>

    </div>
    </Wrapper>
  )
}
