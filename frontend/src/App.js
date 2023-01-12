import React, { useState } from 'react';
import {
  ShopOutlined,
  FileOutlined,
  PieChartOutlined,
  TeamOutlined,
  UserOutlined,
  HomeOutlined,
} from '@ant-design/icons';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Space } from 'antd';
import Index from './pages';
import {
  Routes,
  Route,
  Link
} from "react-router-dom";
import logo from './images/title.png'
import LoginForm from './pages/authentication/login';
import RegisterForm from './pages/authentication/register';
import HeaderSearch from './pages/headerSearch';

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
const items = [
  getItem(<Link to='/'>{homePage}</Link>, '1', <HomeOutlined />),
  getItem('eShop', '2', <ShopOutlined />),
  getItem('User', 'sub1', <UserOutlined />, [
    getItem('Record', '3'),
    getItem('Schedule', '4'),
    getItem('Alex', '5'),
  ]),
  getItem('Team', 'sub2', <TeamOutlined />, [getItem('Team 1', '6'), getItem('Team 2', '8')]),
  getItem('Files', '9', <FileOutlined />),
];
const App = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
        <div
          style={{
            height: 32,
            margin: 16,
            background: 'rgba(255, 255, 255, 0.2)',
          }}
        />
        <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} />
      </Sider>
      <Layout className="site-layout">
        <Header style={{
            padding: 0,
            background: colorBgContainer,
          }}
        >
          <Row>
            <Col span={4} style={{top:15,}} offset={6}>
              <HeaderSearch></HeaderSearch>
            </Col>
            <Col span={1} offset={10}>
              <LoginForm></LoginForm>
            </Col>
            <Col span={1} >
              <RegisterForm></RegisterForm>
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
              <Route path="/" element={<Index />} />
            </Routes>
          </div>
        </Content>
        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          Fellaverse ©2023 Created by Fellas
        </Footer>
      </Layout>
    </Layout>
  );
};
export default App;
