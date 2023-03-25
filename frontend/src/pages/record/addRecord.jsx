import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, Select} from 'antd';
import axios from 'axios';
import { domain } from '../../config';
import { useEffect } from 'react';
import moment from 'moment';

const { Option } = Select;

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
  const [exercises, setExercises] = useState([]);

  const delay = ms => new Promise(res => setTimeout(res, ms));
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;

        // TODO: get by user id
        const result = await axios.get(`${domain}record/exercise`); // TODO

        const exerciseList = result.data.data.map(f => {  // changed to record list
          return {...f, key: f.id};
        });
        setExercises(exerciseList);
        console.log(exerciseList);
      } catch (error) {
        console.log(error);
        let msg = "Internal server error."
        if (error.response.data.message) {
          msg = error.response.data.message;
        } else if (error.response.data) {
          msg = error.response.data;
        }
        message.error(msg);
      }

    }
    initialize();
  }, []);

  const onFinish = async (values) => {
    setLoading(true);
    try {
      const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
      values = {...values, userId : userId, createDateTime : moment()};

      console.log('Received values of form: ', values);
      const result = await axios.post(`${domain}record`, values);

      message.success("Add record successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new record success!";
      navigate(`/success/${title}/${subTitle}`); // TODO
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
      name="numOfSets"
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

    <Form.Item
      name="exerciseId"
      label="Exercise"
      // initialValue={roleIds}
      rules={[
        {
          required: true,
        },
      ]}
    >
      <Select mode="single" placeholder="Please select exercise">
        {exercises.map(exercise => (
          <Option key={exercise.id} value={exercise.id}>{exercise.exerciseName}</Option>
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

export default AddRecord;