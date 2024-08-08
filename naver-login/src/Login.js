import React,{useEffect,useState} from "react";

const Login = () => {
    
  return (
    <>
      {/*유저 정보가 만약 존재한다면 */}
      <div>
        <a href="http://localhost:9010/naverLogin">
            <img
              height="50"
              src="http://static.nid.naver.com/oauth/small_g_in.PNG"
              alt=""
            />
        </a>
      </div>
    </>
  );
};

export default Login;
