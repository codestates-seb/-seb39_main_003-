import React, { useState } from 'react'
import styled from 'styled-components'

const Wrapper = styled.div`
 
 
 .write {
  width: 100%;
  height: 50px;
  border: 2px solid red;
 }
`;

function Appcopy() {

  const [array, setArray] = useState([])

  const [write, setWrite] = useState("")

  const handleButtonSubmit = () => {
    // 댓글 적은 부분이 array에 추가가 되도록 하면 될듯
    setArray.push(write)
  }

  return (
    <Wrapper>
      <form>
        <input type='text' onChange={setWrite}/>
        <button onClick={handleButtonSubmit}>게시</button>
              <div className='write'>
        {array.map((el) => {
          return (
              <div>{array}</div>
            )
          })}
              </div>
      </form>
    </Wrapper>


  )
}

export default Appcopy