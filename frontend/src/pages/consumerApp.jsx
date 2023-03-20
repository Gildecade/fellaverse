import React, { useState, useEffect } from 'react';
import {
  ShopOutlined,
  FileOutlined,
  TeamOutlined,
  UserOutlined,
  HomeOutlined,
  FireOutlined,
} from '@ant-design/icons';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Space } from 'antd';
import {
  Routes,
  Route,
  Link
} from "react-router-dom";
// import logo from './images/title.png'

import Index from './index';
import LoginForm from './authentication/login';
import LogoutForm from './authentication/logout';
import RegisterForm from './authentication/register';
import HeaderSearch from './headerSearch';
import NotFound from './result/404';
// TODO: import your components here
import Success from './result/Success';
import ForgotPasswordForm from './authentication/forgotPassword';
import FlashSale from './flashSale/listFlashSale';
import DetailFlashSale from './flashSale/detailFlashSale';
import OrderSuccess from './flashSale/orderSuccess';

import ViewSchedule from './schedule/viewSchedule';
// import DetailFlashSale from './schedule/addSchedule';
// import OrderSuccess from './schedule/editSchedule';

import UserApp from './user/userApp';

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

const ConsumerApp = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [ username, setUsername] = useState(null);

  // useEffect: run this block when first rendering this page
  useEffect(() => {
    const username = localStorage.getItem('username') ? localStorage.getItem('username') : sessionStorage.getItem('username');
    setUsername(username);
  }, []);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  
  const items = username ?
  [
    // TODO: modify sider contents here
    getItem(<Link to='/'>{homePage}</Link>, '1', <HomeOutlined />),
    getItem(<Link to='/user'>Profile</Link>, 'p', <UserOutlined />),
    getItem('eShop', '2', <ShopOutlined />, [
      getItem('Home', '3'),
      getItem(<Link to='/flash-sale'>{'Flash sale'}</Link>, '4'),
    ]),
    getItem('Workout', 'sub1', <FireOutlined />, [
      getItem('Record', '5'),
      // getItem(<Link to='/schedule'>{'Schedule'}</Link>, '6'),
    ]),
    getItem('Team', 'sub2', <TeamOutlined />, [getItem('Team 1', '7'), getItem('Team 2', '8')]),
    getItem('Files', '9', <FileOutlined />),
  ] :
  [
    getItem(<Link to='/'>{homePage}</Link>, '1', <HomeOutlined />),
  ];
  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)} theme="light">
        <div
          style={{
            height: 32,
            margin: 16
          }}
        >
        <img src="./title.png" alt="title" style={{width:170,height:32}} />
        </div>
        <Menu theme="light" defaultSelectedKeys={['1']} mode="inline" items={items} />
      </Sider>
      <Layout className="site-layout" >
        <Header style={{
            padding: 0,
            background: colorBgContainer,
          }}
        >
          {username ? 
            (
              <Row>
                <Col lg={6} xxl={8}>
                </Col>
                <Col lg={6} xxl={8} style={{top:15,}}>
                  <HeaderSearch></HeaderSearch>
                </Col>
                <Col lg={9} xxl={6}>
                </Col>
                <Col lg={2} xxl={1}>
                  <LogoutForm></LogoutForm>
                </Col>
              </Row>
            ) : 
            (
              <Row>
                <Col lg={12} xxl={16}>
                </Col>
                <Col lg={8} xxl={5}>
                </Col>
                <Col lg={2} xxl={1}>
                  <LoginForm></LoginForm>
                </Col>
                <Col lg={2} xxl={1}>
                  <RegisterForm></RegisterForm>
                </Col>
              </Row>
            )
          }
          
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
              <Link to='/'>
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
              <Route path="/" element={<Index />} />
              <Route path='/user/*' element={<UserApp />}></Route>
              <Route path='/flash-sale' element={<FlashSale />}></Route>
              <Route path='/flash-sale/:id' element={<DetailFlashSale />} />
              <Route path='/flash-sale/:title/:subTitle' element={<OrderSuccess />}></Route>
              
              <Route path='/schedule/:id' element={<ViewSchedule />}></Route>
              {/* <Route path='/schedule/edit' element={<DetailFlashSale />} />
              <Route path='/flash-sale/:title/:subTitle' element={<OrderSuccess />}></Route>
               */}
              <Route path='/success/:title/:subTitle' element={<Success />} />
              <Route path='/forgotPassword' element={<ForgotPasswordForm />} />
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
  );
};
export default ConsumerApp;
