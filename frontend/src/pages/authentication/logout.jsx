import React from 'react';
import { Button, message } from 'antd';
import { LogoutOutlined } from '@ant-design/icons';

const delay = ms => new Promise(res => setTimeout(res, ms));
const LogoutForm = () => {
  const logout = async () => {
    message.success("Successfully logged out");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("roles");
    sessionStorage.removeItem("functions");
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("userId");
    localStorage.removeItem("roles");
    localStorage.removeItem("functions");
    await delay(1000);
    window.location.href = `/`;
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