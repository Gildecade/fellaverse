import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, Select } from 'antd';
import axios from 'axios';
import { domain } from '../../../config';

const { Option } = Select;

const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 4,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 20,
    },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 4,
    },
  },
};
const AddAdmin = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [roles, setRoles] = useState([]);
  const navigate = useNavigate();

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}management/admin`, values);
      message.success("Add successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new admin success!";
      navigate(`/admin/success/${title}/${subTitle}`);
    } catch (error) {
      setLoading(false);
      console.log(error);
      if (error.response) {
        let msg = error.response.data.data.message;
        message.error(msg);
      } else {
        message.error("Add failed. Internal server error.");}
    }
  };
  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <div
        style={{
          width: 30,
        }}
      >
        +1
      </div>
    </Form.Item>
  );
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/role`);
        const roles = result.data.data;
        console.log(roles);
        setRoles(roles);
        
      } catch (error) {
        console.log(error);
      }
    }
    initialize();
  }, []);
  return (
  <Form
    {...formItemLayout}
    form={form}
    name="Add admin"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="username"
      label="Username"
      tooltip="What do you want others to call you?"
      rules={[
        {
        required: true,
        message: 'Please input your username!',
        whitespace: true,
        },
    ]}
    >
    <Input />
    </Form.Item>

    <Form.Item
      name="email"
      label="E-mail"
      rules={[
        {
        type: 'email',
        message: 'The input is not valid E-mail!',
        },
        {
        required: true,
        message: 'Please input your E-mail!',
        },
    ]}
    >
    <Input />
    </Form.Item>
    <Form.Item
      name="phoneNumber"
      label="Phone Number"
      rules={[
        {
        required: true,
        message: 'Please input your phone number!',
        },
    ]}
    >
    <Input
      addonBefore={prefixSelector}
      style={{
      width: '100%',
      }}
    />
    </Form.Item>
    <Form.Item
      name="password"
      label="Password"
      rules={[
        {
        required: true,
        message: 'Please input your password!',
        },
    ]}
    hasFeedback
    >
      <Input.Password />
    </Form.Item>

    <Form.Item
      name="confirm"
      label="Confirm Password"
      dependencies={['password']}
      hasFeedback
      rules={[
        {
        required: true,
        message: 'Please confirm your password!',
        },
        ({ getFieldValue }) => ({
        validator(_, value) {
            if (!value || getFieldValue('password') === value) {
            return Promise.resolve();
            }
            return Promise.reject(new Error('The two passwords that you entered do not match!'));
        },
        }),
    ]}
    >
      <Input.Password />
    </Form.Item>

    <Form.Item
      name="roleIds"
      label="Roles"
      rules={[
        {
          required: false,
          type: 'array',
        },
      ]}
    >
      <Select mode="multiple" placeholder="Please select admin roles">
        {roles.map(role => (
          <Option key={role.id} value={role.id}>{role.roleName}</Option>
        ))}
      </Select>
    </Form.Item>
    
    <Form.Item {...tailFormItemLayout}>
    <Button type="primary" htmlType="submit" loading={loading}>
        Add
    </Button>
    </Form.Item>
</Form>
  );
};
export default AddAdmin;

