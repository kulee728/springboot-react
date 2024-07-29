import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useEffect, useRef, useState } from 'react';
import UserTable from './component/UserTable';
import UserForm from './component/UserForm';
import EditUserForm from './component/EditUserForm';

function App() {
  const [users,setUsers] = useState([]);
  const userChangeState = useRef(false);
  
  //수정한 유저 정보를 잠시 담고 있을 변수 생성
  const [userToEdit,setUserToEdit] = useState(null);

  useEffect(()=>{
   // viewAllUser();
    viewAllUserAsync();
    userChangeState.current = false;
  },[])

  //1. axios success/fail 에 대한 결과처리
  const viewAllUser = () =>{
    axios.get("/users") //controller GetMapping 에서 /users 주소를 바라보기 때문에
      .then(res=>{
        setUsers(res.data);
      })
      .catch(err=>{
        console.log("axios.get /findAllUser err!",err);
      })
  }

  const viewAllUserAsync = async()=>{
    const res = await axios.get("/users");
    setUsers(res.data);
  };

  const addUser = async(user) =>{
    const res = await axios.post("/users",user);
    setUsers([...users],res.data);
    userChangeState.current = true;
  }

  const deleteUser = async(id) => {
    await axios.delete(`/users?id=${id}`);
    console.log(id);
    //await axios.delete("/users",{params:{id}}); 이건 requestParam 방식
    //await axios.delete("/users",id);
    console.log("dddd");
    /*
    배열에서 특정 조건에 따라 원소를 추출하는 filter
    */
    setUsers(users.filter(user=>user.id!==id));
    userChangeState.current = true;
}

const updateUser = async(user) => {
  await axios.put('/users',user);
  setUsers(users.map(u=>(u.id===user.id ?user:u)));
}

/*
유저 정보 수정 취소하기
*/
const cancelEdit = () =>{
  setUserToEdit(null);
}

const editUser =(user)=>{
  setUserToEdit(user);
}



  //2.async await 의 개념. axios 성공에 대한 결과만 보여주는 버전
  return (
    <div className='container'>
      <h1>유저 관리하기</h1>
      <UserForm addUser={addUser}/>
      <UserTable users={users} deleteUser={deleteUser} editUser={editUser}/>
      {userToEdit&&(
        <EditUserForm
          userToEdit={userToEdit}
          updateUser={updateUser}
          cancelEdit={cancelEdit}
          />
      )}
      
    </div>
  );
}

export default App;
