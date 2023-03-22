import React, { useEffect, useState } from 'react';
import moment from 'moment/moment';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, DatePicker, TimePicker, Space } from 'antd';
import axios from 'axios';
import { domain } from '../../config';
import { Link } from 'react-router-dom';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);
var utc = require('dayjs/plugin/utc')
var timezone = require('dayjs/plugin/timezone') // dependent on utc plugin
dayjs.extend(utc)
dayjs.extend(timezone)
const tz = "America/New_York"

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
  const [weightValue, setWeightValue] = useState(0);
  const [startTimeString, setStartTimeString] = useState('');
  const [endTimeString, setEndTimeString] = useState('');
  const [offset, setOffset] = useState(0);
  const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');

  const setWeight = (e) => {
    //setWeight(e.target.value);
    console.log("v", e.target.value);
    data.weight = e.target.value;
  }
  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
    setStartTimeString(dateString[0]);
    setEndTimeString(dateString[1]);
  };
  var data = {
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
    //console.log('Received values of form: ', values);
    console.log(values);
    //console.log(dayjs().format('YYYY-MM-DD'));
    try {
      const now = dayjs();
      const localUtcOffset = now.utcOffset();
      console.log(localUtcOffset / 60);
      //data.startDateTime = dayjs(data.startDateTime).subtract(-4, 'h');
      data.startDateTime = dayjs(data.startDateTime).subtract(localUtcOffset/60, 'h');
      //data.endDateTime = dayjs(data.endDateTime).subtract(-4, 'h');
      data.endDateTime = dayjs(data.endDateTime).subtract(localUtcOffset/60, 'h');
      console.log("start", data.startDateTime);
      console.log("end", data.endDateTime);
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


  useEffect( ()=>{
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;

      } catch (error) {
        console.log(error);
        message.error(error.response.data.message);
      }
    }
    initialize();
  } , [] );

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
      <Input type="number" min="0" style={{width: 'calc(10%)'}} onChange={setWeight}/> (optional)
    </Form.Item>

    <Form.Item {...tailFormItemLayout}>
      <Space size='small'>
        <Button type="primary" htmlType="submit" loading={loading}>
          Add
        </Button>
        <Link to={'/checkIn'}>
          <Button
            type="default"
          >
            Cancel
          </Button>
        </Link>
      </Space>
    </Form.Item>
</Form>
  );
};
export default AddCheckIn;


