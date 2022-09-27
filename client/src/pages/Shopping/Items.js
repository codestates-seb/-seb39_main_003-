import React from 'react'
import styled from 'styled-components';

const Wrapper = styled.div`

  display: flex;
  box-sizing: border-box;
  justify-content: center;

 .test {
   width: 30rem;
   height: 30rem;
   border: 2px solid red;
 }

`;

function Items() {

  // const [test, setTest] = useState(ItemData);


    // useEffect(() => {
  //   fetch(`도메인주소/${item.id}`)
  //   .then((res) => res.json())
  //   .then(res => {
  //     setTest(res)
  //   })
  // } , [])

  return (
    <Wrapper>
      <div className='test'></div>
    </Wrapper>
  )
}

export default Items