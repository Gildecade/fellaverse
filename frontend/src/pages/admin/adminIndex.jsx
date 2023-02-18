import React, { useState, useEffect } from 'react';

// function(component) name
const AdminIndex = () => {
    // declare varibles and corresponding setter here
    // useState means varibles' initialized value, like "", [], false
    const [ username, setUsername] = useState(null);
  
    // useEffect: run this block when first rendering this page
    useEffect(() => {
      const username = localStorage.getItem('username') ? localStorage.getItem('username') : sessionStorage.getItem('username');
      setUsername(username);
    }, []);
  
    
    // return real html code here
    return (
      <div>
        { username ? 
          (
            <h1 style={{marginLeft: '40px',}}>
                Welcome back, {username}.
            </h1>
          ) :
          (
            <h1>
                Something went wrong, please Login again.
            </h1>
          )
        }
      </div>
    );
  }

  // remember to export your component
  export default AdminIndex;