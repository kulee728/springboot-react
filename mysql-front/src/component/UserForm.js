import React,{useState} from "react";

const UserForm = ({addUser})=>{
    const [name,setName] = useState('');
    const [email,setEmail] = useState('');

    const handleSubmit = (e)=>{
        e.preventDefault();
        addUser({name,email});
        setName('');
        setEmail('');
    }

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label>이름 : </label>
                <input type="text" value={name}
                onChange={e=>setName(e.target.value)}
                required/><br/>
            </div>
            <div>
                <label>이메일 : </label>
                <input type="email" value={email}
                onChange={e=>setEmail(e.target.value)}
                required/><br/>
            </div>
            <button>새로운유저추가하기</button><br/>
            <button>네이버로그인을 통한 새 유저</button>
        </form>
    )
}
export default UserForm;