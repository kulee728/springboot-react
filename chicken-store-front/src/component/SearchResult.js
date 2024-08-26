import React,{useEffect,useState} from 'react';
import axios from 'axios';
import { useLocation} from 'react-router-dom';
import '../css/SearchResult.css';
const SearchResult = () =>{
    const location = useLocation();

    const [chickens,setChickens] = useState([]);
    const query = new URLSearchParams(location.search).get("query");

    //
    useEffect(()=>{
        if(query){
            axios.get(`http://localhost:9090/api/chicken/search?query=${query}`)
            .then(response=>setChickens(response.data))
            .catch(err=>console.error("문제가 발생하여 검색을 가져오지 못합니다.",err))
        }
    },[query])

    return (
        <div className='chicken-list'>
            <h2>검색 결과 : "{query}"</h2>
            {chickens.length > 0 ? 
            chickens.map((chicken)=>
            (<div key={chicken.id} className='chicken-item'>
                <h3>{chicken.chickenName}</h3>
                <h3>{chicken.description}</h3>
                <h3>{chicken.price}</h3>
            </div>)) :
            (<div>
                <p>검색 결과가 없습니다.</p>
            </div>)}
        </div>
    )
}
export default SearchResult;

