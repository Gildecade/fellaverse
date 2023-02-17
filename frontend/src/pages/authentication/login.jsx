import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { LoginOutlined, EyeInvisibleOutlined, EyeTwoTone, UserOutlined, LockOutlined } from '@ant-design/icons';
import { Button, Drawer, Form, Input, Checkbox, message } from 'antd';
import axios from 'axios';
import { domain } from '../../config';

const LoginForm = () => {
  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const showDrawer = () => {
    setOpen(true);
  };
  const onClose = () => {
    setOpen(false);
  };
  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    let remember = values.remember;
    try {
      const result = await axios.post(`${domain}auth/login`, values);
      message.success("Login successfully.");
      const data = result.data.data;
      console.log(data);
      const token = data.token;
      const username = data.username;
      const roles = data.roles;
      const functions = data.functions;
      if (remember) {
        localStorage.setItem("token", token);
        localStorage.setItem("username", username);
        if (roles) {
          localStorage.setItem("roles", JSON.stringify(roles));
        } else {
          localStorage.setItem("functions", JSON.stringify(functions));
        }
      } else {
        sessionStorage.setItem("token", token);
        sessionStorage.setItem("username", username);
        if (roles) {
          sessionStorage.setItem("roles", JSON.stringify(roles));
        } else {
          sessionStorage.setItem("functions", JSON.stringify(functions));
        }
      }
      await delay(1000);
      if (roles) {
        navigate(`/admin`);
      } else {
        navigate(`/`);
      }
    } catch (error) {
      setLoading(false);
      console.log(error);
      if (error.response) {
        let msg = error.response.data.message;
        message.error(msg);
      } else {
        console.log("Check whether nginx booted.");
        message.error("Login failed. Internal server error.");}
    }
  };
  return (
    <>
      <Button type="primary" onClick={showDrawer} icon={<LoginOutlined />}>
        Sign in
      </Button>
      <Drawer
        title="Sign in"
        width={540}
        onClose={onClose}
        open={open}
        bodyStyle={{
          paddingBottom: 80,
        }}
        // extra={
        //   <Space>
        //     <Button onClick={onClose}>Cancel</Button>
        //     <Button onClick={onClose} type="primary">
        //       Submit
        //     </Button>
        //   </Space>
        // }
      >
        <Form
      name="normal_login"
      className="login-form"
      initialValues={{
        remember: true,
      }}
      onFinish={onFinish}
    >
      <Form.Item
        name="email"
        rules={[
          {
            required: true,
            message: 'Please input your Email!',
          },
        ]}
      >
        <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Email" />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[
          {
            required: true,
            message: 'Please input your Password!',
          },
        ]}
      >
        <Input.Password
          prefix={<LockOutlined className="site-form-item-icon" />}
          type="password"
          placeholder="Password"
          iconRender={(visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
        />
      </Form.Item>
      <Form.Item>
        <Form.Item name="remember" valuePropName="checked" noStyle>
          <Checkbox>Remember me</Checkbox>
        </Form.Item>
          <a className="login-form-forgot" style={{marginLeft: '240px',}} onClick={onClose} href={'/forgotPassword'}>
            Forgot password
          </a>
      </Form.Item>

      <Form.Item>
        <Button type="primary" htmlType="submit" className="login-form-button" loading={loading}>
          Log in
        </Button>
      </Form.Item>
    </Form>
      </Drawer>
    </>
  );
};
export default LoginForm;

