import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, Popconfirm, message } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const AdminManagement = () => {
  const [admins, setAdmins] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const navigate = useNavigate();
  var colors = ["magenta", "red", "volcano", "volcano", "gold", "lime", "green", "cyan", "blue", "geekblue", "purple"];
  var colorDict = new Array();

  const getExtract = (array) => {
    const random = (min, max) => Math.floor(Math.random() * (max - min) + min);
    let index=random(0, array.length);
    return array.splice(index, 1);
  }
  const handleDelete = async (key) => {
    try {
      const result = await axios.delete(`${domain}management/admin/` + key);
      message.success("Delete successfully.");
      const data = result.data.data;
      // console.log(data);
      const title = data;
      const subTitle = "Delete admin info success!";
      navigate(`/admin/success/${title}/${subTitle}`);
    } catch (error) {
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
  const handleSearch = (selectedKeys, confirm, dataIndex) => {
    confirm();
    setSearchText(selectedKeys[0]);
    setSearchedColumn(dataIndex);
  };
  const handleReset = (clearFilters) => {
    clearFilters();
    setSearchText('');
  };
  const getColumnSearchProps = (dataIndex) => ({
    filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters, close }) => (
      <div
        style={{
          padding: 8,
        }}
        onKeyDown={(e) => e.stopPropagation()}
      >
        <Input
          ref={searchInput}
          placeholder={`Search ${dataIndex}`}
          value={selectedKeys[0]}
          onChange={(e) => setSelectedKeys(e.target.value ? [e.target.value] : [])}
          onPressEnter={() => handleSearch(selectedKeys, confirm, dataIndex)}
          style={{
            marginBottom: 8,
            display: 'block',
          }}
        />
        <Space>
          <Button
            type="primary"
            onClick={() => handleSearch(selectedKeys, confirm, dataIndex)}
            icon={<SearchOutlined />}
            size="small"
            style={{
              width: 90,
            }}
          >
            Search
          </Button>
          <Button
            onClick={() => clearFilters && handleReset(clearFilters)}
            size="small"
            style={{
              width: 90,
            }}
          >
            Reset
          </Button>
          <Button
            type="link"
            size="small"
            onClick={() => {
              confirm({
                closeDropdown: false,
              });
              setSearchText(selectedKeys[0]);
              setSearchedColumn(dataIndex);
            }}
          >
            Filter
          </Button>
          <Button
            type="link"
            size="small"
            onClick={() => {
              close();
            }}
          >
            close
          </Button>
        </Space>
      </div>
    ),
    filterIcon: (filtered) => (
      <SearchOutlined
        style={{
          color: filtered ? '#1890ff' : undefined,
        }}
      />
    ),
    onFilter: (value, record) =>
      record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()),
    onFilterDropdownOpenChange: (visible) => {
      if (visible) {
        setTimeout(() => searchInput.current?.select(), 100);
      }
    },
    render: (text) =>
      searchedColumn === dataIndex ? (
        <Highlighter
          highlightStyle={{
            backgroundColor: '#ffc069',
            padding: 0,
          }}
          searchWords={[searchText]}
          autoEscape
          textToHighlight={text ? text.toString() : ''}
        />
      ) : (
        text
      ),
  });

  const columns = [
    {
      title: 'Userame',
      dataIndex: 'username',
      key: 'username',
      ...getColumnSearchProps('username'),
        sorter: (a, b) => {
          const nameA = a.username.toUpperCase(); // ignore upper and lowercase
          const nameB = b.username.toUpperCase(); // ignore upper and lowercase
          if (nameA < nameB) {
            return -1;
          }
          if (nameA > nameB) {
            return 1;
          }
        
          // names must be equal
          return 0;
        },
        sortDirections: ['descend', 'ascend'],
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
      ...getColumnSearchProps('email'),
        sorter: (a, b) => {
          const nameA = a.email.toUpperCase(); // ignore upper and lowercase
          const nameB = b.email.toUpperCase(); // ignore upper and lowercase
          if (nameA < nameB) {
            return -1;
          }
          if (nameA > nameB) {
            return 1;
          }
        
          // names must be equal
          return 0;
        },
        sortDirections: ['descend', 'ascend'],
    },
    {
      title: 'Phone Number',
      dataIndex: 'phoneNumber',
      key: 'phoneNumber',
      ...getColumnSearchProps('phone number'),
        sorter: (a, b) => {
          const nameA = a.phoneNumber.toUpperCase(); // ignore upper and lowercase
          const nameB = b.phoneNumber.toUpperCase(); // ignore upper and lowercase
          if (nameA < nameB) {
            return -1;
          }
          if (nameA > nameB) {
            return 1;
          }
        
          // names must be equal
          return 0;
        },
        sortDirections: ['descend', 'ascend'],
    },
    {
      title: 'Roles',
      key: 'roles',
      dataIndex: 'roles',
      render: (tags) => (
        tags ? 
        <span>
          {tags.map((tag) => {
            let color = getExtract(colors)[0];
            colorDict[tag] = color;
            // console.log(colorDict);
            return (
              <Tag color={colorDict[tag]} key={tag}>
                {tag}
              </Tag>
            );
          })}
        </span> : 
        <span />
      ),
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <Link to={'edit/'+record.key} state={record}>Edit</Link>
          <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(record.key)}>
            <a>Delete</a>
          </Popconfirm>
        </Space>
      ),
    },
  ];
  
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/admin`);
         console.log(localStorage);
        const adminList = result.data.data.map(f => {
          return {...f, key: f.id};
        });
        setAdmins(adminList);
        
      } catch (error) {
        console.log(error);
        message.error(error.response.data.message);
      }

    }
    initialize();
  }, []);

  return (
    <div>
      <Table
        columns={columns}
        pagination={{
          position: ['bottomRight'],
        }}
        dataSource={admins}
      />
      <Link to={'/admin/admin/add'}>
        <Button
          type="primary"
        >
          Add new admin
        </Button>
      </Link>
    </div>
  );
};

export default AdminManagement;