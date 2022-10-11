import React, { useState, useEffect } from 'react'
import styled from 'styled-components';
import { BsFillSuitHeartFill } from "react-icons/bs";
import { BiTrash } from "react-icons/bi";




const Wrapper = styled.div`

.commentAllBox {
  margin-top: 50px;
  height: 50rem;
  /* border: 1px solid red; */
  background-color: beige;
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
  /* border: 1px solid blue; */
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
  /* border: 1px solid blue; */
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

  const [write, setWrite] = useState("")
  const [comment, setComment] = useState([])
  const [reviewList, setReviewList] = useState([])
  // console.log(reviewList)

  useEffect(() => {
    fetch(`https://shopforourpets.shop:8080/api/v1/review?itemId=${itemId}&page=1&size=8`)
      .then(res => res.json())
      .then(res => {
        setReviewList(res.data)
        // console.log(res.data)
      })
    }, [])
  
  
  const handleButtonReview = () => {
    fetch(`https://shopforourpets.shop:8080/api/v1/review`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        starCnt: 4,
        photo: "string",
        reviewContent: write,
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
    fetch(`https://shopforourpets.shop:8080/api/v1/review?itemId=${itemId}&page=1&size=8`)
    .then(res => res.json())
    .then(res => {
      setComment(res.data)
      // console.log(res.data)
    })
    .catch(err => console.log(err))
  }, [])



    // useEffect(() => {
    // fetch(`https://shopforourpets.shop:8080/api/v1/review/${itemId}`)
    // .then((res) => {
    //   setReviewList(res.data)
    //   window.location.reload();
    // })
    // .catch(() => {
    //   alert('실패')
    // })
    // }, [])


     
    


  return (

    <Wrapper>
      <div className='commentAllBox'>
        <div className='commentBox'>
          <input type="text" placeholder='댓글을 입력하세요' className='comment' onChange={textInput} />
          <span className='commentButton' onClick={handleButtonReview}>등록</span>
        </div>

        {comment && comment.map((el, idx) => {

          const Delete = () => {
            fetch(`https://shopforourpets.shop:8080/api/v1/review/${el.reviewId}`, {
            method: 'DELETE'
            })
            .then(() => {
              alert('리뷰가 삭제되었습니다.')
              window.location.reload();
            })
            .catch(() => {
              alert('실패')
            })
          }
          return (
            <div className='reviewBox' key={idx}>
              <span className='reviews'>{el.nickName}</span>
              <span className='reviews'>{el.reviewContent}</span>
              <span className='reviews heart'>
                ⭐️
                ⭐️
                ⭐️
                ⭐️
                ⭐️
                {/* <span className='heartCount'>{el.startCnt}</span> */}
              </span>
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