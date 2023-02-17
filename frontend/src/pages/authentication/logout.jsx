import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, message } from 'antd';
import { LogoutOutlined } from '@ant-design/icons';

const delay = ms => new Promise(res => setTimeout(res, ms));
const LogoutForm = () => {
  const navigate = useNavigate();
  const logout = async () => {
    message.success("Successfully logged out");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("roles");
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("roles");
    await delay(1000);
    navigate(`/`);
  };
  return (
    <>
      <Button type="primary" onClick={logout} icon={<LogoutOutlined />} danger>
        Logout
      </Button>
    </>
  );
}

export default LogoutForm;