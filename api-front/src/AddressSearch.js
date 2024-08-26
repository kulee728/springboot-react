import { useState,useEffect } from 'react';
import axios from 'axios';
const AddressSearch = () => {
  const [address, setAddress] = useState('');
  const [tailAddress,setTailAddress] = useState('');
  const [finalAddress,setFinalAddress] = useState('');

    const apiFetcher = ()=>{
        fetch("http:localhost:8080/api/addUser",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({address:finalAddress}) //java 에서 String address로 받을 수 있다
        })
        .then(res=>{
            res.text();
            console.log(res.text());
        })
        .catch(err=>{
            console.log("err!",err);
        })

        axios.post("/api/addUser",{address:finalAddress});
        /*
                .then(res=>{
            res.text();
            console.log(res.text());
        })
        .catch(err=>{
            console.log("err!",err);
        })
       */
    }


  //벡엔드 api url 주소를 // /api/addUser 로 Restful 연결 하려 한다.
  const handleComplete = (data) => {
    let fullAddress = data.address; //사용자가 클릭한 검색 결과 주소
    let extraAddress = '';
    //R은 도로명주소. J는 지번주소.
    if (data.addressType === 'R') {  // 도로명 주소인 경우 검색하겠다는 뜻
      if (data.bname !== '') { 
        extraAddress += data.bname; //bname : 특정 동 추가
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }

    setAddress(fullAddress);
  };

  const openPostcode = () => {
    new window.daum.Postcode({ //새 창에서 스크립트 .daum 기능을 수행함 (index.html에 추가한 poscode script)
      oncomplete: handleComplete,
    }).open();
  };

  useEffect(()=>{
    setFinalAddress(address+", "+tailAddress);
  },[address,tailAddress])

  return (
    <div>
      <button onClick={openPostcode}>주소 검색</button>
      {address && 
        <div>
            선택한 주소: {address}
      <div>
        <input type='text' placeholder='추가 주소 입력 (예 : 아파트 동 / 호수)'
        value={tailAddress}
        onChange={e=>setTailAddress(e.target.value)}/>
      </div>
      </div>}
      {(address && tailAddress)&&
      (<>
        <p>최종 추가주소</p>
        <h5>{finalAddress}</h5>

        <input type='hidden' value={finalAddress}/> 
        {/* value 값을 DB에 넣어야 할 때 input 을 hidden으로 줘서 input 태그에는 넣되 사용자에게는 안보이게 */}
      </>)}
    </div>
  );
};

export default AddressSearch;