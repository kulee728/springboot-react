import React,{useEffect,useState} from 'react';
import { Link } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
//useLocation : 버튼 클릭 없이 위치를 설정해줌.
/*
useLocation 
*/

function UserInfo(){

    const [userInfo,setUserInfo] = useState(null);
    const location = useLocation(); // 어떤 클릭이 없어도 userInfo 컴포넌트가
    const [loading,setLoading] = useState(true);
    const [isSignUp,setIsSignUp] = useState(false);
    const [userId,setUserId] = useState('');
    const [userPw,setUserPw] = useState('');
    //호출되면 자동으로 실행된다.

    useEffect(()=>{
        const a = new URLSearchParams(location.search);
        //URLSearchParam >> URL ? 뒤에 붙은 키-벨류 값을 가져온다.
        //controller에서 userinfo?access_token=값 했기 때문에

        const accessToken = a.get('access_token');
        //a.get으로 접근 한 이 값은 controller 에서 넘겨준 accessToken값이다.
        if(accessToken){
            setLoading(false);
            axios.get(`/userinfo?access_token=${accessToken}`)
            .then(response=>{
                setUserInfo(response.data);
            })
            .catch(err=>{
                console.log("axios get userinfo err!",err);
            })
        }
    },[location.search]) //location.search로 검색된 키-값 중 access_token = abc123
    // access_token 값을 가져오면 효과를 발동한다.

    const signUp = async ()=>{
        await axios.post(`/signup`,
            {
                id:userId,
                email:userInfo.response.email,
                name:userInfo.response.name,
                password:userPw,
                profileImage:userInfo.response.profile_image
            });
        setUserPw('');
        setUserId('');
        setIsSignUp(true);
    }

    if(loading)
        return(
            <div>
                데이터 정보 가져오는 중..
            </div>
        )

    return(
        <>
            <h1>유저 정보</h1>
            {userInfo ? (<div>
                <input type='text' value={userInfo.response.id} disabled/>
                {/*네이버에서 가져온 id값을 input 에 넣어주고 수정하지 못하게 막음 처리 한것 */}
                <input type='email' value={userInfo.response.email} disabled/>
                <input type='text' value={userInfo.response.nickname} disabled/>
                <input type='text' value={userInfo.response.name} disabled/>
                <img src={userInfo.response.profile_image}/>
                {JSON.stringify(userInfo.response)}
            </div>)

            :
            (<p>유저를 찾을 수 없습니다.</p>)}

            <div>
                <h2>회원가입에 필요한 아이디 및 비밀번호 작성하기</h2>
                {isSignUp ? (
                    <>
                    환영합니다 ~~님! 회원가입 날짜는 : %%%% 입니다.
                    </>
                ):(
                    <>
                    신규 가입하기 (*추후 네이버 로그인이 가능합니다.)
                    ID : <input type='text' value={userId} onChange={e=>setUserId(e.target.value)}/>
                    PASSWORD : <input type='password'  value={userPw} onChange={e=>setUserPw(e.target.value)}/><br/>
                    <button onClick={signUp}>가입하기</button>
                    </>
                )}

            </div>
        </>
    )

}
export default UserInfo;