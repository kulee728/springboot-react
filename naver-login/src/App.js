import './App.css';
import NaverAPI from './component/NaverAPI';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import NaverSignUp from './component/NaverSignUp';
import { useEffect, useState } from 'react';
import Header from './component/layout/Header';
import AuthContext from './component/layout/AuthContext';
import Login from './component/Login';
// html파일이 1개 밖에 없는 React에서는 
// Router를 이용해서 각 js파일의 경로를 설정
// BrowserRouter = Router : 웹에 전체적인 경로음
// Switch   -> Routes     : 경로들
// Route                  : 경로
function App() {
  const [loginMember,setLoginMember] = useState(null);
  
  //로그인 정보 존재시 localStorage 저장
  useEffect(()=>{
    if(loginMember){
      localStorage.setItem("loginMember",JSON.stringify(loginMember));
    }
  },[loginMember]);
  
  useEffect(()=>{
    const savedMember = localStorage.getItem("loginMember");
    if(savedMember){
      setLoginMember(JSON.parse(savedMember)); //데이터 손실 우려, JSON 변환
    }
  },[])

  return (
    <AuthContext.Provider value={{loginMember,setLoginMember}}>
      <Router>
        <Header/>
        <Routes>
          <Route path='/login' element={<Login/>  } />
          <Route path='/api/naver' element={<NaverAPI     />  } />
          <Route path='/signup/naver' element= { <NaverSignUp    />} />
        </Routes>
      </Router>
    </AuthContext.Provider>
    
  );
}

export default App;