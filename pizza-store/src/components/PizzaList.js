import React,{useState,useEffect} from 'react';
import axios from 'axios';
const PizzaList = () =>{
    const [pizzaList,setPizzaList] = useState([]);

    useEffect(()=>{
        axios.get('/api/pizza/all')
            .then(res=>{
                console.log("/all get response : ",res);
                setPizzaList(res.data);
            })
            .catch(err=>{
                console.log("server response err! ",err);
            })
    },[])

    return(
        <div className='pizza-container'>
            <h1>피자 메뉴</h1>
            <ul>
                {pizzaList.map((pizza,index) =>(
                    <li key={index}>
                        <div className='pizza-name'>{pizza.name}</div>
                        <div className='pizza-description'>{pizza.description}</div>
                        <div className='pizza-price'>{pizza.price}</div>
                        <button>상세보기</button>
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default PizzaList;