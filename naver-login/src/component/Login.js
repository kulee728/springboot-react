import axios from "axios";
import "../css/Login.css";
import { useState } from "react";
const Login = () => {
    const [id,setId] = useState('');
    const [password,setPassword] = useState('');
    const [message,setMessage] = useState('');

    const loginAPIcall = ()=>{
        axios.post("http://localhost:9010/login",
        null,{params:{"id":id,"password":password}}
        )
            .then(response=>{
                if(response.status===200){
                    setMessage('로그인 성공!');
                    console.log("response : ",response);
                }
            })
            .catch(err=>{
                setMessage('로그인 중 문제가 발생했습니다.');
                console.log("error : ",err);
            })

            /*
            fetch('/login',{
                method:'POST',
                headers:{
                'Content-Type':'application/json'},
                body:JSON.stringify{id,password}
            })
                .then(
                    result=>{
                        setMessage(result)
                    }
                )
                .catch(err=>{
                    console.log("err!",err)
                });
            */
    }

  return (
    <div className="login-container">
      <h3>로그인하기</h3>
      <div>
        <label>
          아이디 :
          <input type="text" value={id} onChange={e=>setId(e.target.value)}
          placeholder="아이디를 입력하세요" />
        </label>
        <label>
          비밀번호 :
          <input type="password" value={password} onChange={e=>setPassword(e.target.value)} placeholder="비밀번호를 입력하세요" />
        </label>
        <button onClick={loginAPIcall}>로그인하기</button>
        {message && <p>{message}</p>}
        <div className="find-sign-buttons">
          <button>아이디찾기</button>
          <button>비밀번호찾기</button>
          <button>회원가입하기</button>
        </div>
      </div>
      <label>sns로 로그인하기 : <img src="/naver_img/btnD_round.png" style={{cursor:"pointer"}} className="naver-logo-img"/>
      </label>
      {/** 
      <button className="naver-login-button">
        <img src="/naver_img/btnD_round.png" />
        네이버로 로그인
      </button>
      */}
    </div>
  );
};
export default Login;