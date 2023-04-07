import React, { useState, useEffect } from 'react';
import {
  Routes,
  Route,
  Link
} from "react-router-dom";
import { Breadcrumb, Col, Layout, Menu, Row, theme } from 'antd';
import {
  ShopOutlined,
  HomeOutlined,
  ContactsOutlined,
  ShoppingOutlined,
  ShoppingCartOutlined,
  ApartmentOutlined,
  UserOutlined,
  UngroupOutlined,
  SubnodeOutlined,
  AuditOutlined
} from '@ant-design/icons';

import AdminIndex from './adminIndex';
import LogoutForm from '../authentication/logout';
import Success from '../result/Success';
import NotFound from '../result/404';
// TODO: import your components here
import AdminManagement from './admin/listAdmin';
import AddAdmin from './admin/addAdmin';
import EditAdmin from './admin/editAdmin';
import RoleManagement from './role/listRole';
import AddRole from './role/addRole';
import EditRole from './role/editRole';
import LimitedProductManagement from './limitedProduct/listLimitedProduct';
import AddLimitedProduct from './limitedProduct/addLimitedProduct';
import EditLimitedProduct from './limitedProduct/editLimitedProduct';
import FlashSaleOrderManagement from './flashSaleOrder/listOrder';
import DetailSaleOrder from './flashSaleOrder/detailOrder';
// user
import UserManagement from './user/listUser';
import EditUserFunction from '../admin/user/editUserFunction';
// function
import FunctionManagement from './function/listFunction';
import AddFunction from './function/addFunction';
import EditFunction from './function/editFunction';
// exercise
import ExerciseManagement from './exercise/listExercise';
import AddExercise from './exercise/addExercise';
import EditExercise from './exercise/editExercise';
// proList
import ProListManagement from './proList/listProList';
import AddProList from './proList/addProList';
import EditProList from './proList/editProList';

import ShopOrderManagement from './order/listOrder';
import DetailOrderManagement from './order/detailOrder';
import CourseManagement from './course/listCourse';
import AddCourse from './course/addCourse';
import EditCourse from './course/editCourse';

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
    if (roles == null) {
      return;
    }
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
      items.push(getItem(<Link to='/admin/prolist'>Announcement Management</Link>, '7', <AuditOutlined />));
    }
    if ((roles.indexOf("SuperAdmin") != -1) || (roles.indexOf("ShopAdmin") != -1)) {
      items.push(getItem("Shop Management", 'sub1', <ShopOutlined />, [
        getItem(<Link to='/admin/shop/course'>Course</Link>, '8', <ShoppingOutlined />),
        getItem(<Link to='/admin/order'>Order</Link>, '9', <ShoppingCartOutlined />),
      ]));
    }
    if ((roles.indexOf("SuperAdmin") != -1) || (roles.indexOf("ShopAdmin") != -1)) {
      items.push(getItem("Sale Management", 'sub2', <ShopOutlined />, [
        getItem(<Link to='/admin/limitedProduct'>Product</Link>, '10', <ShoppingCartOutlined />),
        getItem(<Link to='/admin/saleOrder'>Order</Link>, '11', <ShoppingCartOutlined />),
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
              margin: 16
            }}
          >
          <img id="project-image" src="./title.png" alt="title" style={{width:170,height:32}} />
          </div>
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
                paddingTop: 1
              }}
            >
              <Routes>
                {/* TODO: link your components(element) with route paths here */}
                <Route path="/" element={<AdminIndex />} />
                <Route path='/success/:title/:subTitle' element={<Success />} />
                {roles.indexOf("SuperAdmin") != -1 &&
                  <Route path="/admin" element={<AdminManagement/>} /> &&
                  <Route path="/admin/add" element={<AddAdmin/>} /> &&
                  <Route path="/admin/edit/:id" element={<EditAdmin/>} /> &&
                  <Route path="/role" element={<RoleManagement/>} /> &&
                  <Route path="/role/add" element={<AddRole/>} /> &&
                  <Route path="/role/edit/:id" element={<EditRole/>} />}
                {(roles.indexOf("SuperAdmin") != -1) || (roles.indexOf("ShopAdmin") != -1) ?
                <>
                    <Route path="/shop/course" element={<CourseManagement />} />
                    <Route path="/shop/course/add" element={<AddCourse/>} />
                    <Route path="/shop/course/edit/:id" element={<EditCourse/>} />
                    <Route path="/limitedProduct" element={<LimitedProductManagement/>} />
                    <Route path="/limitedProduct/add" element={<AddLimitedProduct/>} />
                    <Route path="/limitedProduct/edit/:id" element={<EditLimitedProduct/>} />
                    <Route path="/saleOrder" element={<FlashSaleOrderManagement/>} />
                    <Route path="/saleOrder/detail/:id" element={<DetailSaleOrder/>} />
                    <Route path="/order" element={<ShopOrderManagement/>} />
                    <Route path="/order/detail/:id" element={<DetailOrderManagement/>} />
                </>
                :<></>}
                <Route path="/user" element={<UserManagement/>} />
                <Route path="/user/edit/:id" element={<EditUserFunction/>} />
                <Route path="/function" element={<FunctionManagement/>} />
                <Route path="/function/add" element={<AddFunction/>} />
                <Route path="/function/edit/:id" element={<EditFunction/>} />
                <Route path="/exercise" element={<ExerciseManagement/>} />
                <Route path="/exercise/add" element={<AddExercise/>} />
                <Route path="/exercise/edit/:id" element={<EditExercise/>} />
                <Route path="/prolist" element={<ProListManagement/>} />
                <Route path="/prolist/add" element={<AddProList/>} />
                <Route path="/prolist/edit/:id" element={<EditProList/>} />
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