import logo from './logo.svg';
import {useState} from 'react';
import './App.css';
import axios from 'axios';

function App() {
  const [username,setUsername] = useState('');
  const [password,setPassword] = useState('');
  const [email,setEmail] = useState('');
  const signUp=() =>{

    const formData = new FormData();
    formData.append("username",username);
    formData.append("email",email);
    formData.append("password",password);

    const user = {
      username:username,
      email:email,
      password:password
    };

    fetch("/api/register",{
      method:"POST",
      //body:formData
      headers:{
        'Content-Type':'application/json'
      },
      body:JSON.stringify(user)
    })
    .then(response=>{
      alert('회원가입 완료되었습니다.');
      console.log("response",response);
    })
    .then(data=>{
      console.log("data",data);
    })
    .catch(err=>{
      console.log("err",err);
    })
    /*
    axios.post("/api/register",formData)
      .then(response=>{
        console.log("response",response);
      })
      */
  }

  return (
    <div className="App">
      <h2>회원가입</h2>
      <label>닉네임 : </label>
      <input type='text' placeholder='닉네임 작성하기'
      value={username} onChange={e=>setUsername(e.target.value)}
      />
      <label>이메일 : </label>
      <input type='text' placeholder='이메일 작성하기'
      value={email} onChange={e=>setEmail(e.target.value)}
      />
      <label>비밀번호 : </label>
      <input type='text' placeholder='비밀번호 작성하기'
      value={password} onChange={e=>setPassword(e.target.value)}
      />
      <button onClick={signUp}>가입하기</button>
    </div>
  );
}

export default App;
