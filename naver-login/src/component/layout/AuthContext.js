import { createContext } from "react";

//로그인 한 멤버 저장하는 Context
//새로고침을 하더라도 로그인 정보가 풀리지 않는다.
const AuthContext = createContext({
    loginMember : null,
    setLoginMember : ()=>{}
    
})
export default AuthContext;
