import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message } from 'antd';
import axios from 'axios';
import { domain } from '../../config';

const { TextArea } = Input;
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
const AddRecord = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}record`, values);
      message.success("Add record successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new record success!";
      navigate(`/admin/success/${title}/${subTitle}`); // TODO
    } catch (error) {
      setLoading(false);
      console.log(error);
      let msg = null;
      if (error.response) {
        if (error.response.data.message) {
          msg = error.response.data.message;
        } else {
          msg = error.response.data;
        }
      } else {
        msg = "Add failed. Internal server error.";
      }
      message.error(msg);
    }
  };
  return (
  <Form
    {...formItemLayout}
    form={form}
    name="Add record"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    {/* <Form.Item // TODO 
      name="recordTime"
      label="Record Time"
      rules={[
        {
        required: true,
        message: 'Please input record time!',
        whitespace: true,
        },
    ]}
    >
      <Input />
    </Form.Item> */}

    <Form.Item
      name="weights"
      label="Weights"
      rules={[
        {
        required: true,
        message: 'Please input weights!',
        },
    ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      name="quantity"
      label="Quantity"
      rules={[
        {
        required: true,
        message: 'Please input quantity!',
        },
    ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      name="sets"
      label="Sets"
      rules={[
        {
        required: true,
        message: 'Please input sets!',
        },
    ]}
    >
      <Input />
    </Form.Item>

    <Form.Item // TODO
      name="exercise"
      label="Exercise"
      rules={[
        {
        required: true,
        message: 'Please input exercise!',
        },
    ]}
    >
      <Input />
    </Form.Item>

    <Form.Item {...tailFormItemLayout}>
      <Button type="primary" htmlType="submit" loading={loading}>
        Add
      </Button>
    </Form.Item>
</Form>
  );
};
export default AddRecord;