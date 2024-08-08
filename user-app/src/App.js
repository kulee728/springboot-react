import {useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [users,setUsers] = useState([]);
  const [name,setName] = useState('');
  const addUser = ()=>{
    setUsers([...users,name]);
    setName('');
  }
  const deleteUser =(index)=>{
    const newUserList = users.filter((user,i)=>i!==index);
    setUsers(newUserList);

  }

  return (
    <div className="App">
      <h1>유저 리스트</h1>
      <h3>유저 추가하기</h3>
      <input type='text'
      value={name}
      onChange={e=>setName(e.target.value)}
      />
      <button onClick={addUser}>추가하기</button>

      <h3>유저 목록 살펴보기</h3>
      {users.map((user,index)=>(
        <li key={index}>
          {index} -
          {user} - &nbsp;
          <button onClick={()=>deleteUser(index)}>삭제하기</button>
        </li>
      ))}
    </div>
  );
}

export default App;
  