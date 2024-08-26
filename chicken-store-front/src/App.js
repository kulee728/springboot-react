import React from "react";
import {BrowserRouter as Router, Route, Routes} from'react-router-dom';
import ChickenList from "./component/ChickenList";
import ChickDetail from "./component/ChickDetail";
import MainRouter from "./MainRouter";
import SearchResult from "./component/SearchResult";
function App () {
    return(
        <Router>
            <Routes>
                <Route path="/" element={<MainRouter />} />
                <Route path="/chicken-detail/:id" element={<ChickDetail />} />
                <Route path="/search" element={<SearchResult />} />

            </Routes>

        </Router>
    )
}

export default App;