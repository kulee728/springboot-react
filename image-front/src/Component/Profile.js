import { React, useState,useEffect } from "react";
import '../css/Profile.css'
import axios from 'axios';

const Profile = () => {
  const [files, setFiles] = useState([]);
  const [username, setUsername] = useState("");
  const [profile,setProfile] = useState([]);
  const [userId,setUserId] = useState(null);

    const nicknameUpdater = (p)=>{
        setUserId(p.userId); //수정할 사용자 id
        setUsername(p.username);

    }
    const fileChanger= async (e)=>{
        setFiles(Array.from(e.target.files));

        console.log("files : ",Array.from(e.target.files));
        console.log("e target files : ",e.target.files);
        console.log("e target values : ",e.target.values);
    }
    
    const imageUpload1 = () =>{
        const formData = new FormData();
        //이건 안된다 formData.append("files",files);
        console.log("fetching files",files);
        Array.from(files).forEach(file=>{
            formData.append("files",file);
        })
        formData.append("username",username);
        fetch("http://localhost:9007/profile/upload",{
            method:"POST",
            body:formData
        })
            .then(response=>{
                console.log("profile insert response : ",response);
                return response.json();
            })
            .then(data=>{
                pageRefresh(); //DB에 저장된 프로필, 닉네임 보여주기 위해 새로고침
            })
    }
    const imageUpload2 = async () =>{
        const formData = new FormData();
        console.log("fetching files",files);
        Array.from(files).forEach(file=>{
            formData.append("files",file);
        })
        formData.append("username",username);

       const res = await axios.post("http://localhost:9007/profile/upload",formData);
       if(res)
        pageRefresh();

    }

    const imageUpload3 = () =>{
        const formData = new FormData();
        console.log("fetching files",files);
        Array.from(files).forEach(file=>{
            formData.append("files",file);
        })
        formData.append("username",username);

        axios.post("http://localhost:9007/profile/upload",formData)
            .then(response=>{
                console.log("axios profile insert response : ",response); //axios 는 response return 이 불필요하다.
                pageRefresh(); 
            })
    }

    const pageRefresh = () => {
        axios.get("http://localhost:9007/profile/watching")
                            .then(response=>{
                                setProfile(response.data);
                            })
        setUsername('');
        setFiles('');
    }

    useEffect(()=>{
        pageRefresh();
    },[])

  return (
    <div>
      <h1>프로필 이미지 업로드</h1>
      <div className="profile-thumbnail">
        {files.length>0 && files.map((file,index)=>(
            <div key={index}>
                <img src={URL.createObjectURL(file)}/>
            </div>
        ))}
      </div>
      <input type="file" onChange={fileChanger} />
      <input
        type="text"
        placeholder="닉네임을 입력하세요"
        onChange={(e) => setUsername(e.target.value)}
        value={username}
      />
      <button onClick={imageUpload3}>프로필 저장하기</button>
    <hr></hr>
    <h3>프로필 상세페이지</h3>
    <div>
        {profile && profile.map((p)=>(
            <div key={p.userId}>
                <p>{p.username}</p>
                <p>{/*p.profileImageUrl*/}</p>
                <p>{p.createdAt}</p>
                {p.profileImageUrl && p.profileImageUrl.split(',').map(image=>(
                    <img key={image} src={`http://localhost:9007/images/${image}`}/>
                ))}
                <button>프로필 이미지 변경하기</button>
            </div>
        ))}
    </div>
    </div>
  );
};
export default Profile;
