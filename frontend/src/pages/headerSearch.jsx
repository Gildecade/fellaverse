import React from 'react';
import { Input, Space } from 'antd';
const { Search } = Input;

const onSearch = (value) => console.log(value);

const HeaderSearch = () => (
  <Search placeholder="input search text" onSearch={onSearch} enterButton allowClear style={{
    width: 600,
  }}/>
);
export default HeaderSearch;