import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Button, Form, Select, message } from 'antd';
import axios from 'axios';
import { domain } from '../../../config';

const { Option } = Select;

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
const EditUserFunc = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [funcs, setFuncs] = useState([]);
  const [funcIds, setFuncIds] = useState([]);
  const userId = useParams().id;
  const navigate = useNavigate();
  form.setFieldsValue({
    "funcIds": funcIds,
  })
  const parameters = useLocation();
  const record = parameters.state;
  // console.log(record);

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    const request = {...values, id: userId};
    console.log('Received values of form: ', request);
    try {
      const funcResult = await axios.put(`${domain}management/user/func/` + request.id, request.funcIds);
      message.success("Update successfully.");
      const data = funcResult.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update admin info success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = `/admin/user`;
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
  //const getFuncIdByFuncName = (fname, flist) => flist.find(item => item.functionName == fname).id;
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
        const result = await axios.get(`${domain}management/function`);
        const funcs = result.data.data;
        setFuncs(funcs);
        const funcUserHas = await axios.get(`${domain}management/user/func/`+ record.id);
        console.log("user function:", funcUserHas.data.data);
        setFuncIds(funcUserHas.data.data.map(f => f.id));
        //setFuncIds(funcUserHas.data.data.map(func=>getFuncIdByFuncName(func, funcs)))
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
    name="Edit user"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="funcIds"
      label="Funcs"
      // initialValue={roleIds}
      rules={[
        {
          required: false,
          type: 'array',
        },
      ]}
    >
      <Select mode="multiple" placeholder="Please select user functions">
        {funcs.map(func => (
          <Option key={func.id} value={func.id}>{func.functionName}</Option>
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
export default EditUserFunc;

