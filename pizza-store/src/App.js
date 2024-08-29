import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route}  from 'react-router-dom';
import PizzaList from './components/PizzaList';
import PizzaForm from './components/PizzaForm';
import Modal from './components/Modal';
import PizzaRouter from './components/PizzaRouter';
import PizzaResult from './components/PizzaResult';

function App() {
  return (
    <Router>
      <PizzaRouter/>
      <Routes>
        <Route path='/' element={<PizzaList />} />
        <Route path='/search' element={<PizzaResult />} />
      </Routes>
    </Router>
  );
}

export default App;
