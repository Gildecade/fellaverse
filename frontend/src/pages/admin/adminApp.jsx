import React, { useState, useEffect } from 'react';
import {
  Routes,
  Route,
  Link
} from "react-router-dom";
import { Breadcrumb, Col, Layout, Menu, Row, theme, Space } from 'antd';
import {
  ShopOutlined,
  HomeOutlined,
  ContactsOutlined,
  ShoppingOutlined,
  ShoppingCartOutlined,
  ApartmentOutlined,
  UserOutlined,
  UngroupOutlined,
  SubnodeOutlined
} from '@ant-design/icons';
import { domain } from '../../config';
import axios from 'axios';

import AdminIndex from './adminIndex';
import LogoutForm from '../authentication/logout';
import Success from '../result/Success';
import NotFound from '../result/404';
// TODO: import your components here
import AdminManagement from './admin/listAdmin';
import AddAdmin from './admin/addAdmin';
import EditAdmin from './admin/editAdmin';
import UserManagement from './user/listUser';
import EditUserFunction from './user/editUserFunction';
import RoleManagement from './role/listRole';
import AddRole from './role/addRole';
import EditRole from './role/editRole';
// user
import FunctionManagement from './function/listFunction';
import AddFunction from './function/addFunction';
import EditFunction from './function/editFunction';
// exercise
import ExerciseManagement from './exercise/listExercise';
import AddExercise from './exercise/addExercise';
import EditExercise from './exercise/editExercise';

const { Header, Content, Footer, Sider } = Layout;
function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}
const homePage = 'Home';

// function(component) name
const AdminApp = () => {
  // declare varibles and corresponding setter here
  // useState means varibles' initialized value, like "", [], false
  const [username, setUsername] = useState(null);
  const [roles, setRoles] = useState(null);
  const [collapsed, setCollapsed] = useState(false);
  const [prevPage, setPrevpage] = useState(0);
  var [items, setItems] = useState([]);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  
  // useEffect: run this block when first rendering this page
  useEffect(() => {
    const username = localStorage.getItem('username') ? localStorage.getItem('username') : sessionStorage.getItem('username');
    setUsername(username);
    const roles = JSON.parse(localStorage.getItem('roles') ? localStorage.getItem('roles') : sessionStorage.getItem('roles'));
    setRoles(roles);
    var items = [
      getItem(<Link to='/admin'>{homePage}</Link>, '1', <HomeOutlined />),
    ];
    // TODO: modify sider contents here
    if (roles.indexOf("SuperAdmin") != -1) {    
      items.push(getItem(<Link to='/admin/admin'>Admin Management</Link>, '2', <ContactsOutlined />));
      items.push(getItem(<Link to='/admin/role'>Role Management</Link>, '3', <ApartmentOutlined />));
      items.push(getItem(<Link to='/admin/user'>User Management</Link>, '4', <UserOutlined />));
      items.push(getItem(<Link to='/admin/function'>Function Management</Link>, '5', <UngroupOutlined />));
      items.push(getItem(<Link to='/admin/exercise'>Exercise Management</Link>, '6', <SubnodeOutlined />));
    }
    if ((roles.indexOf("SuperAdmin") != -1) || (roles.indexOf("ShopAdmin") != -1)) {
      items.push(getItem("Shop Management", 'sub1', <ShopOutlined />, [
        getItem(<Link to='/admin'>Product</Link>, '5', <ShoppingOutlined />),
        getItem(<Link to='/admin'>Order</Link>, '6', <ShoppingCartOutlined />),
      ]));
    }
    setItems(items);
  }, []);
  return (
    roles ? 
    (
      <Layout
        style={{
          minHeight: '100vh',
        }}
      >
        <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)} width={"220"}>
          <div
            style={{
              height: 32,
              margin: 16,
              background: 'rgba(255, 255, 255, 0.2)',
            }}
          />
          {/* need to handle previous page to correct the side bar */}
          <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} />
        </Sider>
        <Layout className="site-layout">
          <Header style={{
              padding: 0,
              background: colorBgContainer,
            }}
          >
            <Row>
              <Col lg={21} xxl={22}>
              </Col>
              <Col lg={2} xxl={1}>
                <LogoutForm></LogoutForm>
              </Col>
            </Row>
          </Header>
          <Content
            style={{
              margin: '0 16px',
            }}
          >
            <Breadcrumb
              style={{
                margin: '16px 0',
              }}
            >
              <Breadcrumb.Item>
                <Link to='/admin'>
                  {homePage}
                </Link>
              </Breadcrumb.Item>
              {/* <Breadcrumb.Item>Bill</Breadcrumb.Item> */}
            </Breadcrumb>
            <div
              style={{
                padding: 24,
                minHeight: 360,
                background: colorBgContainer,
              }}
            >
              <Routes>
                {/* TODO: link your components(element) with route paths here */}
                <Route path="/" element={<AdminIndex />} />
                <Route path='/success/:title/:subTitle' element={<Success />} />
                <Route path="/admin" element={<AdminManagement/>} />
                <Route path="/admin/add" element={<AddAdmin/>} />
                <Route path="/admin/edit/:id" element={<EditAdmin/>} />
                <Route path="/role" element={<RoleManagement/>} />
                <Route path="/role/add" element={<AddRole/>} />
                <Route path="/role/edit/:id" element={<EditRole/>} />
                <Route path="/user" element={<UserManagement/>} />
                <Route path="/user/edit/:id" element={<EditUserFunction/>} />
                <Route path="/function" element={<FunctionManagement/>} />
                <Route path="/function/add" element={<AddFunction/>} />
                <Route path="/function/edit/:id" element={<EditFunction/>} />
                <Route path="/exercise" element={<ExerciseManagement/>} />
                <Route path="/exercise/add" element={<AddExercise/>} />
                <Route path="/exercise/edit/:id" element={<EditExercise/>} />
                <Route path="*" element={<NotFound />} />
              </Routes>
            </div>
          </Content>
          <Footer
            style={{
              textAlign: 'center',
            }}
          >
            Fellaverse ©2023 Created by Group One
          </Footer>
        </Layout>
      </Layout>
    ) : 
    (
      <NotFound></NotFound>
    )
  );
}
  // remember to export your component
  export default AdminApp;