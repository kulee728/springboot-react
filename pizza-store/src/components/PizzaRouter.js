import { useState } from "react";
import {useNavigate} from 'react-router-dom';
import Modal from "./Modal";
import PizzaForm from "./PizzaForm";
const PizzaRouter = ()=>{
    const [search,setSearch] =useState('');
    const [isModalOpen,setIsModalOpen] = useState(false);
    const navigate = useNavigate();

    const openModal=()=>{
        setIsModalOpen(true);
    }

    const closeModal = ()=>{
        setIsModalOpen(false);
    }
    const handleSearch = () =>{
        navigate(`/search?query=${search}`);
    }

    return(
        <div className="app-container">
            <h1>피자 메뉴 검색하기</h1>
            <div className="search-container">
                <input type="text" placeholder="검색하고 싶은 피자 메뉴를 작성해주세요" 
                value={search}
                onChange={e=>setSearch(e.target.value)}
                />
            <button onClick={handleSearch}>메뉴 검색하기</button>
            </div>
            <button onClick={openModal}>메뉴 등록하기</button>
            <Modal isModal={isModalOpen} onClose={closeModal}>
                <PizzaForm/>
            </Modal>
        </div>
    )
}
export default PizzaRouter;