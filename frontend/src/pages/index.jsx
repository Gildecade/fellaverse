import React, { useState, useEffect } from 'react';
import { domain } from '../config';
import axios from 'axios';


const Index = () => {
    const [ msg, setMsg] = useState("");
  
  
    useEffect(() => {
      const initialize = async () => {
        const response = await axios.get(`${domain}/`);
        const message = response.data;
        setMsg(message);
      }
      initialize();
    }, []);
  
    
  
    return (
      <div>
        <h1>
            {msg}
        </h1>
      </div>
    );
  }

  export default Index;