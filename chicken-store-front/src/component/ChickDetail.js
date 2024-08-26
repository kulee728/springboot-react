import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import "../css/ChickDetail.css";
import isEqual from 'lodash/isEqual';
// axios useEffect 활용해서 데이터 불러오기

const ChickDetail = () => {
  // {} = 특정값을 받아오는 것 [] = 변수명을 설정하는 것
  const { id } = useParams();
  const navigate = useNavigate();
  console.log("id : ", id);
  const [chicken, setChicken] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [editData, setEditData] = useState({
    chickenName: "",
    description: "",
    price: "",
  });


  const useCustomHook=(obj)=>{
    const prevObj = useRef();

    useEffect(() => {
        if(!isEqual(prevObj.current,obj)){
        axios
          .get(`http://localhost:9090/api/chicken/${id}`)
          .then((response) => {
            setChicken(response.data);
          })
          .catch((e) => alert("불러오는데 문제가 발생했습니다."));
          prevObj.current = obj;
          console.log("hhhh");
        }
      }, [obj]);
  };


  // 만약에 치킨 데이터가 없으면 로딩중


  const handleChickenDelete = () =>{
    axios.delete(`http://localhost:9090/api/chicken/${id}`)
        .then(()=>{
            alert("삭제되었습니다.");
            navigate('/');
        })
        .catch(err=>{
            console.log("삭제 에러 발생 ",err);
        })
  }

  const handleChickenUpdate = () => {
    axios
      .get(`http://localhost:9090/api/chicken/${id}`)
      .then((response) => {
        setChicken(response.data);
        setIsEdit(true);
        setEditData({
          chickenName: response.data.chickenName,
          description: response.data.description,
          price: response.data.price,
        });
      });
  };

  const submitChickenUpdate = () =>{
        axios.put(`http://localhost:9090/api/chicken/${id}`, editData)
            .then(response=>{
                console.log("res",response);
                alert("수정 완료");
                setIsEdit(false);
            }
            )

  }
  useCustomHook(chicken);

  if (!chicken) {
    return <div>로딩중 ...</div>;
  }
  
  return (
    <div className="chicken-detail-container">
      {isEdit ? (
        <div className="chickenform-container">
          <label>
            메뉴 이름 :
            <input
              type="text"
              value={editData.chickenName}
              onChange={(e) => setEditData({
                ...editData,
                chickenName:e.target.value})}
            />
          </label>
          <label>
            메뉴 설명 :
            <textarea
              value={editData.description}
              onChange={(e) => setEditData({
                ...editData,
                description:e.target.value})}
            />
          </label>
          <label>
            가격 :
            <input
              type="number"
              value={editData.price}
              onChange={(e) => setEditData({
                ...editData,
                price:e.target.value})}
            />
          </label>
          <button onClick={submitChickenUpdate}>수정완료</button>
          <button onClick={()=>{setIsEdit(false)}}>돌아가기</button>
          <button onClick={handleChickenDelete}>삭제하기</button>
        </div>
      ) : (
        <div>
          <h1>{chicken.chickenName}</h1>
          <p>{chicken.description}</p>
          <p>{chicken.price}원</p>
          <button onClick={handleChickenUpdate}>수정하기</button>
          <button onClick={()=>{navigate('/')}}>메인으로 돌아가기</button>
        </div>
      )}
    </div>
  );
};


export default ChickDetail;
