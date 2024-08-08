import "./App.css";
import Board from "./Component/Board";
import Main from "./Component/Main";
import Profile from "./Component/Profile"
import Banner from "./Component/layout/Banner";
import Footer from "./Component/layout/Footer";
import Header from "./Component/layout/Header"
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';

function App() {
 
  return (
    <>
    <Router>  
      <Banner/>
      <Header/>
      <Routes>
        <Route path='/' element={<Main/>}/>
        <Route path='/board' element={<Board/>}/>
        <Route path='/profile' element={<Profile/>}/>
      </Routes>
      <Footer/>
    </Router>
    </>
  )
}

export default App;
