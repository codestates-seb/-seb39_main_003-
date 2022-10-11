import React, { useState, useEffect } from 'react'
import styled from 'styled-components';
import { BsFillSuitHeartFill } from "react-icons/bs";
import { BiTrash } from "react-icons/bi";

const Wrapper = styled.div`

.commentAllBox {
  margin-top: 50px;
  height: 50rem;
  /* border: 1px solid red; */
  background-color: #FFF5E4;
}

.commentBox {
  width: 100%;
  height: 6rem;
  /* border: 2px solid green; */
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.comment {
  width: 85%;
  height: 4rem;
  border-radius: 10px;
  padding-left: 10px;
}

.commentButton {
  width: 10%;
  height: 4rem;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.3rem;
  background-color: #ECC5FB;
  border-radius: 10px;
  cursor: pointer;
}

.reviewBox {
  height: 4rem;
  border: 1px solid lightgray;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.reviews {
  width: 22%;
  /* border: 1px solid red; */
}

.heart {
  color: red;
}

.heartCount {
  color: black;
  padding-left: 10px;
}
`

function Review( {itemId, memberId} ) {

  // const [title, setTitle] = useState("")
  const [write, setWrite] = useState("")
  const [comment, setComment] = useState([])
  // console.log(comment)

  const handleButtonReview = () => {
    fetch(`http://211.58.40.128:8080/api/v1/qna/question`, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          questionTitle: "질문제목",
          questionContent: write,
          memberId: "000001",
          itemId: itemId
        }),
      })
      .then((res) => res.json())  
      .then(res => console.log(res))
      .then(() => {
        setWrite("")
      })
      .then(() => {
        window.location.reload();
      })
      .catch(err => console.log(err))
  }

  const textInput = (e) => {
    setWrite(e.target.value)
  };

  useEffect(() => {
    fetch(`http://211.58.40.128:8080/api/v1/qna/question?itemId=${itemId}&page=1&size=8`)
    .then(res => res.json())
    .then(res => {
      setComment(res.data)
      // console.log(res.data)
    })
    .catch(err => console.log(err))
  }, [])

  return (

    <Wrapper>
      <div className='commentAllBox'>
        <div className='commentBox'>
          <input type="text" placeholder='질문을 등록해주세요' className='comment' onChange={textInput} />
          <span className='commentButton' onClick={handleButtonReview}>등록</span>
        </div>

        {comment && comment.map((el, idx) => {
          const Delete = () => {
            fetch(`http://211.58.40.128:8080/api/v1/qna/question/${el.questionId}`, {
             method: 'DELETE'
            })
            .then(() => {
              alert('질문이 삭제되었습니다.')
              window.location.reload();
            })
            .catch(() => {
              alert('실패')
            })
          }
          return (
            <div className='reviewBox' key={idx}>
              <span className='reviews'>{el.nickName}</span>
              <span className='reviews'>{el.questionContent}</span>
              <span className='reviews'>{el.createAt}</span>
              <span className='delete' onClick={Delete}><BiTrash /></span>
            </div>
          )
        })}

      </div>
    </Wrapper>
  
  )
}

export default Review