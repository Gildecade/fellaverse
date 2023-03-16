import React, { useState, useEffect } from "react";
import { useParams, useLocation, useNavigate } from 'react-router-dom';
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
const EditFunction = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const functionId = useParams().id;
  const parameters = useLocation();
  const record = parameters.state;
  const navigate = useNavigate();
  const [startTimeString, setStartTimeString] = useState('');
  const [endTimeString, setEndTimeString] = useState('');
  const [date, setDate] = useState('');
  //console.log('record',record);
  //console.log('date',dayjs(record.startDateTime).format('YYYY-MM-DD'));
  //console.log('time',dayjs(record.startDateTime).format('HH:mm:ss'));
  //setStartTimeString(dayjs(record.startDateTime).format('HH:mm:ss'))
  //setEndTimeString(dayjs(record.endDateTime).format('HH:mm:ss'))
  const onChange = (value, dateString) => {
    //console.log('Selected Time: ', value);
    //console.log('Formatted Selected Time: ', dateString);
    setStartTimeString(dateString[0]);
    setEndTimeString(dateString[1]);
  };
  const onChangeDate = (value, dataString) => {
    console.log(dataString);
    setDate(dataString); 
  }

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    record.startDateTime = date+"T"+startTimeString+"Z";
    record.endDateTime = date+"T"+endTimeString+"Z";
    record.weight = values.weight;
    const request = {...values, id: functionId};
    //console.log('Received values of form: ', request);
    console.log('Record: ', record);
    try {
      const result = await axios.put(`${domain}checkin`, record);
      message.success("Update successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update check in info success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = '/checkIn';
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
  // TODO initial value
// useEffect = (() => {
 // },[]);
  useEffect( ()=>{

    const initialize = async () => {
      try {
      setStartTimeString(dayjs(record.startDateTime).format('HH:mm:ss'));
      setEndTimeString(dayjs(record.endDateTime).format('HH:mm:ss'));
      setDate(dayjs(record.startDateTime).format('YYYY-MM-DD'));

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
    name="Edit check in"
    onFinish={onFinish}
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
      <DatePicker  defaultValue={dayjs(record.startDateTime)} defaultPickerValue={dayjs(record.startDateTime)} onChange={onChangeDate}/>
    </Form.Item>

    <Form.Item
      name="time"
      label="Time"
      rules={[
        {
        required: false,
        message: 'Please input start time and end time',
        },
      ]}
    >
      <TimePicker.RangePicker defaultValue={[dayjs(record.startDateTime), dayjs(record.endDateTime)]} defaultPickerValue={[dayjs(record.startDateTime), dayjs(record.endDateTime)]} onChange={onChange}/>
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
      <Input type="number" defaultValue={record.weight} style={{width: 'calc(5%)',}}/>(optional)
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

