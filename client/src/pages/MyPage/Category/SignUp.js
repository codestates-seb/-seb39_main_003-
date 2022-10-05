import React, { useState } from 'react'
import styled from 'styled-components'
// import { Link } from 'react-router-dom'
import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import DaumPostcode from 'react-daum-postcode';


const Wrapper = styled.div`
box-sizing: border-box;
// 회원가입 페이지
.signUpTerritory{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #EEF1FF;
}
.signUpBackground{
  width: 40vw;
  height: 120vh;

  margin: 30px 0px;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.7);

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #F9F9F9;
}
// 회원가입 본문
.text{
  margin: 30px;
  text-align: center;
  font-weight: bolder;
}
.suBackground{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.subox{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.suback{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.sutext{
  width: 20vw;
  margin: 10px;
  border: none;
  outline:none;
  border-bottom: 2px solid #EEF1FF;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  text-align: start;
  background-color: #F9F9F9;
}
// 회원가입 버튼
.suButton{
  width: 10vw;
  height: 8vh;
  margin-top: 30px;

  /* box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.4); */
  border-radius: 5px;
  border-style: none;

  background-color: #000000;
  color: #9263FF;
  text-align: center;
  font-size: larger;
  font-weight: bolder;
  
  &:hover{
    background-color: #9263FF;
    color: #000000;
  }
}
`
// const StyledLink = styled(Link)`
// text-decoration: none;
// `

function SignUp() {
  
  
  const { register, handleSubmit, formState:{errors} } = useForm();
  
    const onSubmit = (data) => {
      console.log(data)
    const formData = new FormData();
    for(const key in data) {
      formData.append(key, data[key]);
    }
    formData.delete("multipartFiles");
    formData.append("multipartFiles", data.multipartFiles[0]);

    fetch(`http://211.58.40.128:8080/api/v1/member`, {
    method: "POST",
    body: formData
  })

    .then(() => {
      navigate('/')
    })
    .catch(() => {
      console.log("오류")
    })
  };

  const navigate = useNavigate();

  return (
    <Wrapper>
      {/* 회원가입 페이지 */}
      <div className='signUpTerritory'>
        <div className='signUpBackground'>
          
          {/* 회원가입 본문 */}
          <h2 className='text'>Sign Up</h2>
          <div className='suBackground'>
            <form className='suBackground' onSubmit={handleSubmit(onSubmit)}>
              <div className='subox'>

                <div className='suback'>
                  <label>아이디 (Email)</label>
                  <input className="sutext" type='email' {...register("email", {
                    required: "이메일을 입력하세요.",
                    pattern:{
                      value: /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/,
                      message: "이메일 형식으로 입력하세요."
                    }
                  })}></input>
                </div>
                {errors.email && <span>{errors.email.message}</span>}

                <div className='suback'>
                  <label>비밀번호</label>
                  <input className="sutext" type='password' {...register("password", {
                    required: "비밀번호를 입력해주세요.",
                    minLength:{
                      value: 5,
                      message: '5자 이상의 비밀번호를 입력해주세요.'
                    },
                    maxLength:{
                      value: 15,
                      message: '15자 이하의 비밀번호를 입력해주세요.'
                    }
                  })}></input>
                </div>
                {/* 비밀번호: 5자 이상 + 15자 이하*/}
                {errors.password && <span>{errors.password.message}</span>}

                
                <div className='suback'>
                  <label>이름</label>
                  <input className="sutext" type='text' {...register("memberName", {
                    required: "이름을 입력해주세요.",
                    minLength:{
                      value: 2,
                      message: '2자 이상의 이름을 입력해주세요.'
                    },
                    maxLength:{
                      value: 8,
                      message: '8자 이하의 이름을 입력해주세요.'
                    },
                    pattern:{
                      value: /^[가-힣]+$/,
                      message: "한글 이름을 입력해주세요."
                    }
                  })}></input>
                </div>

                {/* 이름: 2자 이상 + 8자 이하 */}
                {errors.memberName && <span>{errors.memberName.message}</span>}
                
                <div className='suback'>
                  <label>닉네임</label>
                  <input className="sutext" type='text' {...register("nickName",{
                    required: "별명을 입력해주세요.",
                    minLength:{
                      value: 1,
                      message: '1자 이상의 별명을 입력해주세요.'
                    },
                    maxLength:{
                      value: 8,
                      message: '8자 이하의 별명을 입력해주세요.'
                    },
                    pattern:{
                      value: /^[가-힣]+$/,
                      message: "한글 별명을 입력해주세요."
                    }
                  })}></input>
                </div>
                {/* 별명: 1자 이상 + 8자 이하 */}
                {errors.nickname && <span>{errors.nickname.message}</span>}
                
                <div className='suback'>
                  <label>주소</label>
                  <input className="sutext" type='text' {...register("address", {
                    required: "주소를 입력해주세요.",
                    pattern:{
                      value: /(([가-힣A-Za-z·\d~\-\.]{2,}(로|길).[\d]+)|([가-힣A-Za-z·\d~\-\.]+(읍|동)\s)[\d]+)/,
                      message: "도로명 주소를 입력해주세요."
                    }
                  })}>
                  </input>

                </div>
                {/* 주소: 한글/영어/숫자로 조합 */}
                {errors.address && <span>{errors.address.message}</span>}
                
                <div className='suback'>
                  <label>전화번호</label>
                  <input className="sutext" type='text' {...register("phone", {
                    required: "전화번호를 입력해주세요.",
                    pattern:{
                      value: /^\d{3}-\d{3,4}-\d{4}$/,
                      message: "-을 포함한 번호를 입력해주세요."
                    }
                  })}></input>
                </div>

                {/* 전화번호: - 기호를 포함한 + 10자 이상 + 11자 미만의 숫자 */}
                {errors.phone && <span>{errors.phone.message}</span>}

                <div className='suback'>
                  <label>프로필 사진</label>
                  <input className="sutext" type='file' {...register("multipartFiles")}></input>
                </div>
            </div>

              {/* 회원가입 버튼 */}
              <input className='suButton' type="submit"></input>
            </form>
          </div>

          {/* 회원가입 버튼 */}
          {/* <StyledLink to="/"><button className='suButton'>Sign Up</button></StyledLink> */}
        </div>
      </div>
    </Wrapper>
  )
}

export default SignUp