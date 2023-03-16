import { Badge, Descriptions, message, Statistic, Menu, } from 'antd';
import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import CountUp from 'react-countup';
import axios from 'axios';
import { domain } from '../../config';

const Profile = () => {
  const [user, setUser] = useState([]);
  const formatter = (value) => <CountUp end={value} separator="," />;
  const items = [
    {
      key: 1,
      label: 'Home',
    },
    {
      key: 2,
      label: 'Flash Sale Order',
    },
  ];

  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}user/${userId}`);
        setUser(result.data.data);
        
      } catch (error) {
        console.log(error);
        let msg = null;
        if (error.response.data.data) {
          msg = error.response.data.data.message;
        } else if (error.response.data.message) {
          msg = error.response.data.message;
        } else if (error.response) {
          msg = error.response.data;
        } else {
          msg = "Internal server error.";
        }
        message.error(msg);
      }

    }
    initialize();
  }, []);
  return (
    <div>
      <Descriptions title="User Information" bordered column={5}>
        <Descriptions.Item label="User ID">{user.id}</Descriptions.Item>
        <Descriptions.Item label="Username">{user.username}</Descriptions.Item>
        <Descriptions.Item label="Email">{user.email }</Descriptions.Item>
        <Descriptions.Item label="Wallet"><Statistic value={user.wallet} precision={2} formatter={formatter} valueStyle={{fontSize:14}} /></Descriptions.Item>
      </Descriptions>
    </div>
);
}; 
export default Profile;