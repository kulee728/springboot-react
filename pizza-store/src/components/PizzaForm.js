import { useState } from "react";
import axios from "axios";

const PizzaForm = () =>{
    const [name,setName] = useState('');
    const [description,setDescription] = useState('');
    const [price,setPrice] = useState('');
    const pizzaObject={
        name,
        description,
        price
    }
    const handleRegister = () =>{

        //const obj = new URLSearchParams(pizzaObject);
        axios.post('/api/pizza/save',pizzaObject)
        .then(res=>{
            console.log("성공!",res);
        })
        
        setName('');
        setDescription('');
        setPrice('');
    }

    return(
        <div className="pizzaform-container">
            <label>
                메뉴 이름 : 
                <input value={name} onChange={e=>setName(e.target.value)} />
            </label>
            <label>
                메뉴 설명 : 
                <input value={description} onChange={e=>setDescription(e.target.value)} />
            </label>
            <label>
                메뉴 가격 : 
                <input value={price} onChange={e=>setPrice(e.target.value)} />
            </label>
            <button onClick={handleRegister}>등록하기</button>
        </div>
    )
}





export default PizzaForm;