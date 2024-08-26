import logo from "./logo.svg";
import "./App.css";
import ChickenList from "./component/ChickenList";
import ChickenForm from "./component/ChickenForm";
import Modal from "./component/Modal";
import {useNavigate} from 'react-router-dom';
import { useState } from "react";
import './css/SearchResult.css';
const MainRouter = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchTerm,setSearchTerm] = useState('');
  const navigate = useNavigate();
  //사용자가 보고싶을 때 볼 수 있도록 처음에는 false 보이지않음 설정 해줌

  // 오픈 true 닫음 false
  // const에서 동작하는 기능이 1개일 때 {} 중괄호 생략 가능
  const openModal = () => setIsModalOpen(true);

  const closeModal = () => {
    setIsModalOpen(false);
  }

  const handleSearch = () =>{
    navigate(`/search?query=${searchTerm}`);

  }

  return (
    <div className="app-container">
      <h1>치킨 가게 메뉴 관리</h1>
      <div className="search-container">
        <input type="text" placeholder="검색하고 싶은 치킨 메뉴를 작성해주세요"
        value={searchTerm} onChange={e=>setSearchTerm(e.target.value)}
        className="search-input"
        />
        <button className="search-button" onClick={handleSearch}>검색하기</button>
      </div>
      <button className="chicken-register-button" onClick={openModal}>메뉴등록하기</button>
      <ChickenList />

      <Modal isOpen={isModalOpen} onClose={closeModal}>
          <ChickenForm />
      </Modal>
    </div>
  );
};

export default MainRouter;