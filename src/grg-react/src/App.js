import { Routes, Route } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useContext, useEffect, useState } from "react";
import Home from './Components/Home';
import Create from './Components/Create';
import Login from './Components/Login';
import User from './Components/User';

function App() {
  const navigate = useNavigate();

  return (
    <Routes>
      <Route path='/' element={<Home navigate={navigate}/>}/> 
      <Route path="/Sign" element={<Create navigate={navigate}/>}/>
      <Route path="/Login" element={<Login navigate={navigate}/>}/>
      {/* maybe add some dynamic stuff in there
      read this : https://stackoverflow.com/questions/57058879/how-to-create-dynamic-routes-with-react-router-dom */}
      <Route path="/User" element={<User navigate={navigate}/>}/>
    </Routes>
  );
}

export default App;