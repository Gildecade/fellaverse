import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { UserAddOutlined } from '@ant-design/icons';
import { Button, Col, Drawer, Form, Input, Row, Space, Select, message } from 'antd';
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
const EditAdmin = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [roles, setRoles] = useState([]);
  const [roleIds, setRoleIds] = useState([]);
  const adminId = useParams().id;
  form.setFieldsValue({
    "roleIds": roleIds,
  })
  const parameters = useLocation();
  const record = parameters.state;
  // console.log(record);

  const delay = ms => new Promise(res => setTimeout(res, ms));
  const onFinish = async (values) => {
    setLoading(true);
    const request = {...values, id: adminId};
    console.log('Received values of form: ', request);
    try {
      const result = await axios.put(`${domain}management/admin`, request);
      if (request.roles != roleIds) {
        const roleResult = await axios.put(`${domain}management/admin/` + adminId, request.roleIds);
      }
      message.success("Update successfully.");
      const data = result.data.data;
      console.log(data);
      await delay(1000);
      const title = data;
      const subTitle = "Update admin info success!";
      window.location.href = `/admin/success/${title}/${subTitle}`;
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
  const getRoleIdByRoleName = (rname, rlist) => rlist.find(item => item.roleName == rname).id;
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
        const result = await axios.get(`${domain}management/role`);
        const roles = result.data.data;
        // console.log(roles);
        setRoles(roles);
        setRoleIds(record.roles.map(role=>getRoleIdByRoleName(role, roles)))
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
    name="Add admin"
    onFinish={onFinish}
    initialValues={{
    prefix: '1',
    }}
    scrollToFirstError
  >
    <Form.Item
      name="username"
      label="Username"
      tooltip="What do you want others to call you?"
      initialValue={record.username}
      rules={[
        {
        whitespace: true,
        },
    ]}
    >
    <Input />
    </Form.Item>

    <Form.Item
      name="email"
      label="E-mail"
      initialValue={record.email}
      rules={[
        {
        type: 'email',
        message: 'The input is not valid E-mail!',
        },
        {
        message: 'Please input your E-mail!',
        },
    ]}
    >
    <Input />
    </Form.Item>
    <Form.Item
      name="phoneNumber"
      label="Phone Number"
      initialValue={record.phoneNumber}
    >
    <Input
      addonBefore={prefixSelector}
      style={{
      width: '100%',
      }}
    />
    </Form.Item>
    <Form.Item
      name="password"
      label="Password"
    hasFeedback
    >
      <Input.Password />
    </Form.Item>

    <Form.Item
      name="confirm"
      label="Confirm Password"
      dependencies={['password']}
      hasFeedback
      rules={[
        ({ getFieldValue }) => ({
        validator(_, value) {
            if (!value || getFieldValue('password') === value) {
            return Promise.resolve();
            }
            return Promise.reject(new Error('The two passwords that you entered do not match!'));
        },
        }),
    ]}
    >
    <Input.Password />
    </Form.Item>

    <Form.Item
      name="roleIds"
      label="Roles"
      // initialValue={roleIds}
      rules={[
        {
          required: false,
          type: 'array',
        },
      ]}
    >
      <Select mode="multiple" placeholder="Please select admin roles">
        {roles.map(role => (
              <Option key={role.id} value={role.id}>{role.roleName}</Option>
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
export default EditAdmin;

