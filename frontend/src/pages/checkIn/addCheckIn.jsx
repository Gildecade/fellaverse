import React, { useState } from 'react';
import moment from 'moment/moment';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, DatePicker, TimePicker } from 'antd';
import axios from 'axios';
import { domain } from '../../config';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);

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

// eslint-disable-next-line arrow-body-style
const disabledDate = (current) => {
  // Can not select days before today and today
  return current < dayjs().endOf('day');
};
const AddCheckIn = () => {
  const [form] = Form.useForm();
  const [date, setDate] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const [startTimeString, setStartTimeString] = useState('');
  const [endTimeString, setEndTimeString] = useState('');
  const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');

  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
    setStartTimeString(dateString[0]);
    setEndTimeString(dateString[1]);
  };
  const data = {
    "id": {
      "id": null,
      "userId": userId
    },
    "user": {
      "id": userId
    },
    "startDateTime": dayjs().format('YYYY-MM-DD')+"T"+startTimeString+"Z",
    "endDateTime": dayjs().format('YYYY-MM-DD')+"T"+endTimeString+"Z",
    "weight": null
  };
  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    console.log('Received values of form: ', values);
    data.weight = values.weight;
    console.log('data',data);
    console.log(dayjs().format('YYYY-MM-DD'));
    try {
      const result = await axios.post(`${domain}checkin`, data);
      message.success("Add successfully.");
      const resultData = result.data.data;
      console.log(resultData);
      await delay(1000);
      const title = data;
      const subTitle = "Add new check in success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = `/checkIn`;
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
    name="Add check in"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="startDateTime"
      label="Date"
      rules={[
        {
        required: false,
        message: 'Please input date',
        },
      ]}
    >
      {/* <DatePicker
        format="YYYY-MM-DD"
        
        defaultPickerValue={dayjs()}
      /> */}
      <DatePicker  defaultValue={dayjs()} defaultPickerValue={dayjs()} disabled/>
    </Form.Item>

    <Form.Item
      name="time"
      label="Time"
      rules={[
        {
        required: true,
        message: 'Please input start time and end time',
        },
      ]}
    >
      <TimePicker.RangePicker defaultValue={[dayjs()]} defaultPickValue={[dayjs()]} onChange={onChange}/>
    </Form.Item>

    <Form.Item
      name="weight"
      label="Body weight"
      rules={[
        {
        message: 'Please input body weight! (optional)',
        whitespace: true,
        },
    ]}
    >
      <Input type="number" style={{width: 'calc(5%)',}}/> (optional)
    </Form.Item>

    <Form.Item {...tailFormItemLayout}>
      <Button type="primary" htmlType="submit" loading={loading}>
        Add
      </Button>
    </Form.Item>
</Form>
  );
};
export default AddCheckIn;


