import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Input, message, Select, InputNumber, Upload, Typography } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import axios from 'axios';
import { domain } from '../../../config';
import uploadFileToBlob, { isStorageConfigured } from '../upload/azure-storage-blob';
import { v4 as uuidv4 } from 'uuid';
const storageConfigured = isStorageConfigured();
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
  const storageUrl = "https://fellaverse.blob.core.windows.net/";
  const imageContainerUrl = storageUrl + "product_images/";
  const videoContainerUrl = storageUrl + "course_videos/";

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    values = {...values, imageUrl: imageContainerUrl + uuidv4()+".png", videoUrl: videoContainerUrl + uuidv4() + ".png", createdDateTime: new Date()};
    console.log('Received values of form: ', values);
    try {
      const result = await axios.post(`${domain}management/shop/course`, values);
      message.success("Add successfully.");
      const data = result.data.data;
      onFileUpload(imageSelected);
      onFileUpload(videoSelected);
      console.log(data);

      resetState(imageSelected, 0) 
      resetState(videoSelected, 1) 
      
      await delay(1000);

      const title = data;
      const subTitle = "Add new course success!";
      navigate(`/admin/success/${title}/${subTitle}`);
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

  
  // current file to upload into container
  const [imageSelected, setImageSelected] = useState();
  const [videoSelected, setVideoSelected] = useState();
  const [fileUploaded, setFileUploaded] = useState('');
  
  // UI/form management
  const [uploading, setUploading] = useState(false);
  const [inputKey, setInputKey] = useState(Math.random().toString(36));

  const onImageChange = (event) => {
    // capture file into state
    setImageSelected(event.target.files[0]);
  };

  const onVideoChange = (event) => {
    // capture file into state
    setVideoSelected(event.target.files[0]);
  };

  const onFileUpload = async (file) => {

    if(file && file?.name){
      // prepare UI
      // setUploading(true);

      // *** UPLOAD TO AZURE STORAGE ***
      await uploadFileToBlob(file);
    }
  };

  const resetState = (file, mode) => {
    if (mode == 0) {
      setImageSelected(null);
    }
    else {
      setVideoSelected(null);
    } 
    //setUploading(false);
    //setInputKey(Math.random().toString(36));
    console.log(fileUploaded);
    message.success("Upload " + file.name +" successfully!");
  }

  // upload product images form
  const uploadForm = (action) => (
    // <div class="ant-upload ant-upload-select">
    //   <span tabindex="0" class="ant-upload" role="button">
    //     <input type="file" accept="" style={{ display: "none" }} onChange={action} key={inputKey || ''}/>
    //     <button type="button" class="ant-btn css-ph9edi ant-btn-default">
    //       <span role="img" aria-label="upload" class="anticon anticon-upload">
    //         <svg viewBox="64 64 896 896" focusable="false" data-icon="upload" width="1em" height="1em" fill="currentColor" aria-hidden="true">
    //           <path d="M400 317.7h73.9V656c0 4.4 3.6 8 8 8h60c4.4 0 8-3.6 8-8V317.7H624c6.7 0 10.4-7.7 6.3-12.9L518.3 163a8 8 0 00-12.6 0l-112 141.7c-4.1 5.3-.4 13 6.3 13zM878 626h-60c-4.4 0-8 3.6-8 8v154H214V634c0-4.4-3.6-8-8-8h-60c-4.4 0-8 3.6-8 8v198c0 17.7 14.3 32 32 32h684c17.7 0 32-14.3 32-32V634c0-4.4-3.6-8-8-8z"></path>
    //         </svg>
    //       </span>
    //       <span>Click to Upload</span>
    //     </button>
    //   </span>
    // </div>
    <Input type="file" bordered={false} onChange={action} key={inputKey || ''} />

  )

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
          {storageConfigured && !uploading && uploadForm(onImageChange)}
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
          {storageConfigured && !uploading && uploadForm(onVideoChange)}

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
              <Option key={user.id} value={user.username} label={user.username}/>
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

