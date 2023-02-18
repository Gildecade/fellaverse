import React from 'react';
import {
  Routes,
  Route,
} from "react-router-dom";

// TODO: import your components here
import AdminApp from './pages/admin/adminApp';
import ConsumerApp from './pages/consumerApp';

const App = () => {
  return (
    <Routes>
      <Route exact path='*' element={<ConsumerApp />} ></Route>
      <Route path='/admin/*' element={<AdminApp />}></Route>
    </Routes>
  );
};
export default App;
