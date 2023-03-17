import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, Select, InputNumber, Upload, Typography } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import axios from 'axios';
import { domain } from '../../../config';

const { Option } = Select;

const { Title } = Typography;
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
const AddCourse = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}management/shop/course`, values);
      message.success("Add successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new course success!";
      navigate(`/course/success/${title}/${subTitle}`);
    } catch (error) {
      setLoading(false);
      console.log(error);
      let msg = null;
      if (error.response.data.data) {
        msg = error.response.data.data.message;
      } else if (error.response.data.message) {
        msg = error.response.data.message;
      } else if (error.response) {
        msg = error.response.data;
      } else {
        msg = "Add failed. Internal server error.";
      }
      message.error(msg);
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
        const result = await axios.get(`${domain}management/user`);
        const users = result.data.data;
        console.log(users);
        setUsers(users);
        
      } catch (error) {
        console.log(error);
      }
    }
    initialize();
  }, []);
  return (
    <div>

      <Title level={3}>Add new course</Title>
      <Form
        {...formItemLayout}
        form={form}
        name="Add course"
        onFinish={onFinish}
        initialValues={{
        prefix: '1',
        }}
        scrollToFirstError
      >
        <Form.Item
          name="productName"
          label="Product Name"
          tooltip="Name of the product"
          rules={[
            {
            required: true,
            message: 'Please input the product name!',
            whitespace: true,
            },
        ]}
        >
        <Input />
        </Form.Item>

        <Form.Item
          name="description"
          label="Description"
          rules={[
            {
            required: true,
            message: 'Please input product description',
            },
        ]}
        >
        <Input />
        </Form.Item>
        <Form.Item
          name="price"
          label="Price"
          rules={[
            {
            required: true,
            message: 'Please input product price',
            },
        ]}
        >
        <InputNumber
          defaultValue={100}
          formatter={(value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
          // parser={(value) => value!.replace(/\$\s?|(,*)/g, '')}
          // onChange={onChange}
        />
        </Form.Item>
        
        <Form.Item
          name="imageUrl"
          label="Product Image"      
        >
          <Upload action="/upload.do" listType="picture-card">
            <div>
              <PlusOutlined />
              <div style={{ marginTop: 8 }}>Upload</div>
            </div>
          </Upload>
        </Form.Item>

        <Form.Item
          name="videoUrl"
          label="Video"
          hasFeedback
          rules={[
            {
            required: true,
            message: 'Please upload the course video',
            },
        ]}
        >
          <Upload action="/upload.do" listType="picture-card">
            <div>
              <PlusOutlined />
              <div style={{ marginTop: 8 }}>Upload</div>
            </div>
          </Upload>
        </Form.Item>

        <Form.Item
          name="productStatus"
          label="Product Status"
          hasFeedback
          rules={[
            {
            required: true,
            message: 'Please input the video Url',
            },
        ]}
        >
          <Select>
            <Select.Option value="0">Active</Select.Option>
            <Select.Option value="1">Unavailable</Select.Option>
            <Select.Option value="2">Hide</Select.Option>
            <Select.Option value="3">Other</Select.Option>
          </Select>
        </Form.Item>

        <Form.Item
          name="userId"
          label="User"
          rules={[
            {
              required: true,
            },
          ]}
        >
          <Select placeholder="Please select associated coach">
            {users.map(user => (
              <Option key={user.id} value={user.id}>{user.userName}</Option>
            ))}
          </Select>
        </Form.Item>
        
        <Form.Item {...tailFormItemLayout}>
        <Button type="primary" htmlType="submit" loading={loading}>
            Add
        </Button>
        </Form.Item>
      </Form>
    </div>
  );
};
export default AddCourse;

