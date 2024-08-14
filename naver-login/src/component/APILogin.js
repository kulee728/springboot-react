import React,{useEffect,useState} from "react";

const Login = () => {
    const [userInfo,setUserInfo] = useState(null);
    

    useEffect(()=>{
        const getUserInfo = () =>{
        fetch('http://localhost:9010/usernfo'/*,{
            method:'GET',
            headers:{"Content-Type":"application/json"}
        }*/)
        .then(res=>{
            return res.json;
        })
        .then(data=>{
            setUserInfo(data);
        })
        .catch(err=>{
            console.error("Error userInfo : " , err);
        })
        getUserInfo();
    }
    },[]);

  return (
    <>
      {/*유저 정보가 만약 존재한다면 */}
      <div>
        {userInfo? '존재한다면 보여줄 코드' : 'userInfo 존재X'}
        
        {userInfo? (<div>
          <h1>로그인 완료!</h1>
          {JSON.stringify(userInfo,null,2)} </div>):
        
        
        (
        <a href="http://localhost:9010/naverLogin">
            <img
              height="50"
              src="http://static.nid.naver.com/oauth/small_g_in.PNG"
            />
        </a>)
          }

      </div>
    </>
  );
};

export default Login;
