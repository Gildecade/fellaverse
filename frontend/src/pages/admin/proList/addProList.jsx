import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { Button, Form, Input, InputNumber, message, Select, Space, DatePicker } from 'antd';
import axios from 'axios';
import { domain } from '../../../config';
import uploadFileToBlob, { isStorageConfigured } from '../upload/azure-storage-blob';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
dayjs.extend(customParseFormat);
const storageConfigured = isStorageConfigured();


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
const AddProList = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [statuses, setStatuses] = useState([]);
  const navigate = useNavigate();
  const azure = "https://fellaverse.blob.core.windows.net/product-images/";

  // all blobs in container
  const [blobList, setBlobList] = useState([]);

  // current file to upload into container
  const [fileSelected, setFileSelected] = useState();
  const [fileUploaded, setFileUploaded] = useState('');

  // UI/form management
  const [uploading, setUploading] = useState(false);
  const [inputKey, setInputKey] = useState(Math.random().toString(36));

  const onFileChange = (event) => {
    // capture file into state
    setFileSelected(event.target.files[0]);
  };

  const onFileUpload = async () => {

    if(fileSelected && fileSelected?.name){
    // prepare UI
    // setUploading(true);

    // *** UPLOAD TO AZURE STORAGE ***
    await uploadFileToBlob(fileSelected);

    // reset state/form
    setFileSelected(null);
    setFileUploaded(fileSelected.name);
    setUploading(false);
    setInputKey(Math.random().toString(36));
    console.log("??????",fileUploaded);
    message.success("Upload succssfully!");
    }

  };

  // display form
  const DisplayForm = () => (
    <Space>
      <Input type="file" bordered={false} onChange={onFileChange} key={inputKey || ''} />
      <Button onClick={onFileUpload} style={{backgroundColor:"#1E90FF", color:"white"}}>
        Upload
      </Button>
    </Space>
  )

  const delay = ms => new Promise(res => setTimeout(res, ms));
  
  // eslint-disable-next-line arrow-body-style
  const disabledDate = (current) => {
    // Can not select days before today and today
    return current && current < dayjs().endOf('day');
  };

  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
  };
  const onOk = (value) => {
    console.log('onOk: ', value);
  };

  const onFinish = async (values) => {
    if (fileUploaded === '') {
      message.error("Please upload image!");
      return;
    }
    // setLoading(true);
    values = {...values, extra: azure + fileUploaded};
    if (!values.description) {
      values.description = dayjs();
    }
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}management/prolist`, values);
      message.success("Add successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Add new announcement success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = `/admin/prolist`;
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
        // console.log(statuses);
        
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
    name="Add announcement"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="title"
      label="Title"
      rules={[
        {
        required: true,
        message: 'Please input title!',
        whitespace: true,
        },
    ]}
    >
    <Input />
    </Form.Item>

    <Form.Item
      name="content"
      label="Content"
      rules={[
        {
        required: true,
        message: 'Please input content',
        },
    ]}
    >
      <TextArea rows={4} showCount={true} allowClear={true} />
    </Form.Item>

    <Form.Item
      name="extra"
      label="Image"
      rules={[
        {
        required: true,
        message: 'Please upload image',
        },
    ]}
    >
      <div>
        {storageConfigured && !uploading && DisplayForm()}
        {storageConfigured && uploading && <div>Uploading</div>}
        {!storageConfigured && <div>Storage is not configured.</div>}
      </div>
    </Form.Item>

    <Form.Item
      name="description"
      label="Create Time"
      rules={[
    ]}
    >
    <DatePicker showTime onChange={onChange} defaultValue={dayjs()} defaultPickerValue={dayjs()} onOk={onOk} />
    </Form.Item>

    <Form.Item {...tailFormItemLayout}>
      <Space size='small'>
        <Button type="primary" htmlType="submit" loading={loading}>
            Add
        </Button>
        <Link to={'/admin/prolist'}>
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
export default AddProList;

