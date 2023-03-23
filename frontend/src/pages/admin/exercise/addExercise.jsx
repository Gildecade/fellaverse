import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message } from 'antd';
import axios from 'axios';
import { domain } from '../../../config';

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
const AddExercise = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}management/exercise`, values);
      message.success("Add successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new exercise success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = `/admin/exercise`;
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
    name="Add exercise"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="exerciseName"
      label="Exercise name"
      rules={[
        {
        required: true,
        message: 'Please input exercise name!',
        whitespace: true,
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
export default AddExercise;

