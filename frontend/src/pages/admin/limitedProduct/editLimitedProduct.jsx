import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Button, Form, Input, Select, message,InputNumber, Space, DatePicker } from 'antd';
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
const EditLimitedProduct = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [statuses, setStatuses] = useState([]);
  const productId = useParams().id;
  const navigate = useNavigate();
  form.setFieldsValue({
    "statuses": statuses,
  })
  const parameters = useLocation();
  const record = parameters.state;
  // console.log(record);

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
    console.log(fileUploaded);
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
  
  // eslint-disable-next-line arrow-body-style
  const disabledDate = (current) => {
    // Can not select days before today
    return current && current < dayjs().startOf('day');
  };

  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
  };
  const onOk = (value) => {
    console.log('onOk: ', value);
  };

  const delay = ms => new Promise(res => setTimeout(res, ms));

  const onFinish = async (values) => {
    setLoading(true);
    let imgUrl = null;
    if (fileUploaded !== "") {
      imgUrl = azure + fileUploaded;
    }
    const request = {...values, id: productId, imageUrl: imgUrl};
    console.log('Received values of form: ', request);
    try {
      const result = await axios.put(`${domain}management/limitedProduct`, request);
      message.success("Update successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update limited product success!";
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
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/limitedProduct/status`);
        const statuses = result.data.data;
        // console.log(statuses);
        setStatuses(statuses);
        
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
    name="Edit limited product"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="productName"
      label="Product name"
      initialValue={record.productName}
      rules={[
        {
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
      <TextArea autoSize={true} maxLength={255} showCount={true} allowClear={true} />
    </Form.Item>
    <Form.Item
      name="quantity"
      label="Quantity"
      initialValue={record.quantity}
    >
    <InputNumber  min={0} />
    
    </Form.Item>
    <Form.Item
      name="price"
      label="Price"
      initialValue={record.price}
    >
    <InputNumber  min={0} />
    </Form.Item>

    <Form.Item
      name="img"
      label="Image"
      initialValue={record.imageUrl}
    >
      <div>
        {storageConfigured && !uploading && DisplayForm()}
        {storageConfigured && uploading && <div>Uploading</div>}
        {!storageConfigured && <div>Storage is not configured.</div>}
      </div>
    </Form.Item>

    <Form.Item
      name="createdDateTime"
      label="Create Time"
    >
    <DatePicker showTime onChange={onChange} onOk={onOk} />
    </Form.Item>

    <Form.Item
      name="saleDateTime"
      label="Sale Time"
    >
      <DatePicker
        format="YYYY-MM-DD HH:mm:ss"
        disabledDate={disabledDate}
        showTime={{
          defaultValue: dayjs('00:00:00', 'HH:mm:ss'),
        }}
      />
    </Form.Item>

    <Form.Item
      name="productStatus"
      label="Status"
      initialValue={record.productStatus}
    >
      <Select placeholder="Please select product status">
        {statuses.map(status => (
          <Option key={status}>{status}</Option>
        ))}
      </Select>
    </Form.Item>
    
    <Form.Item {...tailFormItemLayout}>
    <Button type="primary" htmlType="submit" loading={loading}>
        Submit
    </Button>
    </Form.Item>
</Form>
  );
};
export default EditLimitedProduct;

