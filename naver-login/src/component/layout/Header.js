import { useContext } from "react";
import AuthContext from "./AuthContext";
import { Link } from "react-router-dom";
const Header = () => {
  const { loginMember, setLoginMember } = useContext(AuthContext);
  //대괄호 : 반환 값들에 대한 변수를 새로 설정(정의 하는 것).
  //중괄호 : AuthContext.loginMember 와 같다. 외부 정의된 변수를 가져오는것
  
  const handleLogout = () =>{
    setLoginMember(null);
    localStorage.removeItem("loginMember");
  }
  //구글 크롬 로그인 처럼. localStorage는 컴퓨터를 껏다 켜도 유지된다.
  //로그아웃, 캐시 지우지 않는 한 유지된다.

  return (
    <header>
      <h1>헤더</h1>
      <nav>
        {loginMember? (
            <div>
                <span>{loginMember.nickname}님 환영합니다.</span>
                <button onClick={handleLogout}>로그아웃하기</button>
            </div>
            ) :(<>
        <Link to="/login">로그인하기</Link>
        <Link to="/api/naver">회원가입하기</Link>
        </>)}
      </nav>
    </header>
  );
};

export default Header;
