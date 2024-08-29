import axios from "axios";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
const PizzaResult = () => {
  const [pizzas, setPizzas] = useState([]);
  const location = useLocation();
  const query = new URLSearchParams(location.search).get("query");
  useEffect(() => {
    if (query) {
      axios
        .get("/api/pizza/search", query)
        .then((res) => setPizzas(res.data))
        .catch((e) => {
          console.error("검색 문제", e);
        });
    }
    return;
  }, query); //검색어가 바뀔 때 마다 재검색 하는 useEffect
  //urlsearchparams  : pathname, search, hash, state, key 가 존재한다.
  //그중에서 search 에 접근하였다. /search?query={값}
  return (
    <div className="pizza-search-list">
      <h1>검색 결과 : </h1>
      {pizzas.length > 0 ? (
        <div>
          {pizzas.map((pizza) => (
            <li key={pizza.id}>
              <div className="pizza-name">{pizza.name}</div>
              <div className="pizza-description">{pizza.description}</div>
              <div className="pizza-price">{pizza.price}</div>
              <button>상세보기</button>
            </li>
          ))}
        </div>
      ) : (
        <div>검색 결과가 존재하지 않습니다.</div>
      )}
    </div>
  );
};
export default PizzaResult;
