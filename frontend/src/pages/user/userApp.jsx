import React, { useState, useEffect } from 'react';
import {
  HomeOutlined,
  UnorderedListOutlined,
} from '@ant-design/icons';
import { Layout, Menu, theme } from 'antd';
import {
  Routes,
  Route,
  Link
} from "react-router-dom";
// TODO: import your components here
import Profile from './profile';
import PersonalFlashSaleOrder from './flashSaleOrder';
import PersonalDetailSaleOrder from './detailFlashSaleOrder';
function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}
const homePage = 'Home';

const UserApp = () => {
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
  
  const items = 
  [
    // TODO: modify sider contents here
    getItem(<Link to='/user'>{homePage}</Link>, '1', <HomeOutlined />),
    getItem(<Link to='/user/flashSaleOrder'>Flash sale order</Link>, '2', <UnorderedListOutlined />),
  ];
  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Menu theme="light"  defaultSelectedKeys={['1']} mode="horizontal" items={items} />
    
      <div
        style={{
          padding: 24,
          minHeight: 360,
          background: colorBgContainer,
        }}
      >
        <Routes>
          {/* TODO: link your components(element) with route paths here */}
          <Route path='/' element={<Profile />}></Route>
          <Route path='/flashSaleOrder' element={<PersonalFlashSaleOrder />}></Route>
          <Route path="/flashSaleOrder/detail/:id" element={<PersonalDetailSaleOrder/>} />
        </Routes>
      </div>
    </Layout>
  );
};
export default UserApp;
