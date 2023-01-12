import React, { useState, useEffect } from 'react';
import { domain } from '../config';
import axios from 'axios';

// function(component) name
const Index = () => {
    // declare varibles and corresponding setter here
    // useState means varibles' initialized value, like "", [], false
    const [ msg, setMsg] = useState("");
  
  
    // useEffect: get data from backend when first rendering this page
    useEffect(() => {
      // ajax to render other components while waiting for data
      const initialize = async () => {
        // console.log(response) if you do not know what it is, could be json
        const response = await axios.get(`${domain}/`);
        // response.data means get the value of key "data" in json response
        const message = response.data;
        setMsg(message);
      }
      initialize();
    }, []);
  
    
    // return real html code here
    return (
      <div>
        <h1>
            {msg}
        </h1>
      </div>
    );
  }

  // remember to export your component
  export default Index;