import React, { useState } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
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
const EditFunction = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const functionId = useParams().id;
  const parameters = useLocation();
  const record = parameters.state;
  const navigate = useNavigate();
  // console.log(record);

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    const request = {...values, id: functionId};
    console.log('Received values of form: ', request);
    try {
      const result = await axios.put(`${domain}management/function`, request);
      message.success("Update successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update function info success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = '/admin/function';
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
        msg = "Update failed. Internal server error.";
      }
      message.error(msg);
    }
  };
  return (
  <Form
    {...formItemLayout}
    form={form}
    name="Edit function"
    onFinish={onFinish}
    scrollToFirstError
  >
    <Form.Item
      name="functionName"
      label="Function name"
      initialValue={record.functionName}
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

    <Form.Item
      name="description"
      label="Description"
      initialValue={record.description}
    >
      <TextArea showCount rows={4} placeholder="maxLength is 250" maxLength={250} />
    </Form.Item>
    
    <Form.Item {...tailFormItemLayout}>
      <Button type="primary" htmlType="submit" loading={loading}>
        Submit
      </Button>
    </Form.Item>
</Form>
  );
};
export default EditFunction;

