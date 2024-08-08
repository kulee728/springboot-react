import logo from './logo.svg';
import './App.css';
import Login from './APILogin';
import {BrowserRouter,Route,Routes} from 'react-router-dom';
import UserInfo from './UserInfo';
//react 에서는 html 파일이 1개이므로 각 js 파일의 경로를 router 를 이용해서 설정한다.
//BrowserRouter, Router는 웹 전체적인 경로를 뜻한다
//Switch는  경로들
//BrowserRouter - Routes - Route1, Rout2 ...구조 혹은
//Router - switch - Route1,.. 구조이다.
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route>
            <Route path='/' element={<Login/>}></Route>
            <Route path='/userinfo' element={<UserInfo/>}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
