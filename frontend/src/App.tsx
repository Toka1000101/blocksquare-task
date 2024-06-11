import React from 'react';
import './App.css';
import NavBar from './components/NavBar/NavBar';
import Home from './components/Home/Home';
import { Route, Routes } from 'react-router-dom';
import Editor from './components/Editor/Editor';

function App() {
  return (
    <div className="App">
      <NavBar/>
      <div className="container">
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/editor" element={<Editor/>}/>
        </Routes>
      </div>
    </div>
  );
}

export default App;
