import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { SearchOutlined } from '@ant-design/icons';
import { Button, Form, Input, Select, message, InputNumber, Upload, Typography, Tag, Space } from 'antd';
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
const EditCourse = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [users, setUsers] = useState([]);
  const courseId = useParams().id;
  const navigate = useNavigate();
  const parameters = useLocation();
  const record = parameters.state;

  const storageUrl = "https://fellaverse.blob.core.windows.net/";
  const imageContainerUrl = storageUrl + "product_images/";
  const videoContainerUrl = storageUrl + "course_videos/";
  // console.log(record);

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    try {

      if(imageSelected != null) {
        if (imageSelected.type == "image/png") {
          values.imageUrl = imageContainerUrl + uuidv4() + ".png";
        }
        else if (imageSelected.type == "image/jpeg") {
          values.imageUrl = imageContainerUrl + uuidv4() + ".jpeg";
        }
        else {
          values.imageUrl = imageContainerUrl + uuidv4();
        }
      }

      if (videoSelected != null) {
        if (videoSelected.type == "video/mp4") {
          values.videoUrl = videoContainerUrl + uuidv4() + ".mp4";
        }
        else {
          values.videoUrl = videoContainerUrl + uuidv4();
        }
      }

      const request = {...values, id: courseId};
      console.log('Received values of form: ', request);
    
      const result = await axios.put(`${domain}management/shop/course`, request);
      // if (request.roles != roleIds) {
      //   const roleResult = await axios.put(`${domain}management/course/` + courseId, request.roleIds);
      // }
      message.success("Update successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update course info success!";
      navigate(`/admin/success/${title}/${subTitle}`);
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
        message.error(msg);
      } else {
        message.error("Update failed. Internal server error.");}
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
  const uploadForm = (action, mode) => {
    if (mode == 0) {
      return <Input type="file" accept="image/*" bordered={false} onChange={action} key={inputKey || ''} />

    }
    else {
      return <Input type="file" accept="video/*" bordered={false} onChange={action} key={inputKey || ''} />
    }

  }

  const userOptions = (user) => {
    return {value : user.id, label: user.username}
  }

  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/user`);
        const users = result.data.data;
        // console.log(users);
        setUsers(users);
      } catch (error) {
        console.log(error);
      }
    }
    initialize();
  }, []);
  return (
    <div> 
      <Title level={3}>Edit course with id {record.id}</Title>
       <Form
          {...formItemLayout}
          form={form}
          name="Edit course"
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
            initialValue={record.productName}
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
            initialValue={record.description}
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
            initialValue={record.price}
            rules={[
              {
              required: true,
              message: 'Please input product price',
              },
          ]}
          >
          <InputNumber
            formatter={(value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
            // parser={(value) => value!.replace(/\$\s?|(,*)/g, '')}
            // onChange={onChange}
          />
          </Form.Item>
          
          <Form.Item
            name="imageUrl"
            label="Product Image"      
          >
            <Space direction="vertical" size="middle" style={{ display: 'flex' }}>
              {record.imageUrl != null &&
                <Tag>{record.imageUrl}</Tag>
              }
              {storageConfigured && !uploading && uploadForm(onImageChange, 0)}
            </Space>
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
            <Space direction="vertical" size="middle" style={{ display: 'flex' }}>
              {record.videoUrl != null &&
                <Tag>{record.videoUrl}</Tag>
              }
              {storageConfigured && !uploading && uploadForm(onVideoChange, 1)}
            </Space>
          </Form.Item>

          <Form.Item
            name="productStatus"
            label="Product Status"
            initialValue={record.productStatus}
            hasFeedback
            rules={[
              {
              required: true,
              message: 'Please input the product status',
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
            initialValue={record.id}
            rules={[
              {
                required: true,
              },
            ]}
          >
            <Select placeholder="Please select associated coach"
              options = {users.map(userOptions)}>
            </Select>
          </Form.Item>
          
          <Form.Item {...tailFormItemLayout}>
          <Button type="primary" htmlType="submit" loading={loading}>
              Submit
          </Button>
          </Form.Item>
      </Form>
    </div>
 
  );
};
export default EditCourse;

