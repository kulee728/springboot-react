import React,{useState,useEffect} from "react";

const EditUserForm=({userToEdit,updateUser,cancelEdit})=>{
    //기존 이름과 변경할 이름, 이메일 담을 변수명 설정
    const[name,setName] = useState('');
    const[email,setEmail] = useState('');

    useEffect(()=>{
        if(userToEdit){
            setName(userToEdit.name);
            setEmail(userToEdit.email);
        }
    },[]);
    return(
        <div>
            {userToEdit?(
                <form>
                    <h2>유저 정보 수정하기</h2>
                    <label>
                        이름 : <input type="text" value={name} onChange={e=>setName(e.target.value)}/>
                    </label>
                    <label>
                        이메일 : <input type="email" value={email} onChange={e=>setName(e.target.value)}/>
                    </label>
                    <button>저장하기</button>
                    <button>취소하기</button>
                </form>
            ):(<h1>ㅇㅇ</h1>)}
        </div>
    )
}
export default EditUserForm;