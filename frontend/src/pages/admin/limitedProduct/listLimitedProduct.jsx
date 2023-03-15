import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, Popconfirm, message } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import {
  CheckCircleOutlined,
  ClockCircleOutlined,
  CloseCircleOutlined,
  ExclamationCircleOutlined,
} from '@ant-design/icons';
import moment from 'moment/moment';

const LimitedProductManagement = () => {
  const [products, setProducts] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const navigate = useNavigate();

  const handleDelete = async (key) => {
    try {
      const result = await axios.delete(`${domain}management/limitedProduct/` + key);
      message.success("Delete successfully.");
      const data = result.data.data;
      // console.log(data);
      const title = data;
      const subTitle = "Delete limited product success!";
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
      title: 'Product name',
      dataIndex: 'productName',
      key: 'productName',
      ...getColumnSearchProps('productName'),
        sorter: (a, b) => {
          const nameA = a.productName.toUpperCase(); // ignore upper and lowercase
          const nameB = b.productName.toUpperCase(); // ignore upper and lowercase
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
      title: 'Quantity',
      dataIndex: 'quantity',
      key: 'quantity',
      ...getColumnSearchProps('quantity'),
        sorter: (a, b) => {
          const nameA = a.quantity; // ignore upper and lowercase
          const nameB = b.quantity; // ignore upper and lowercase
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
      title: 'Price',
      dataIndex: 'price',
      key: 'price',
      ...getColumnSearchProps('price'),
        sorter: (a, b) => {
          const nameA = a.price; // ignore upper and lowercase
          const nameB = b.price; // ignore upper and lowercase
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
      title: 'Status',
      key: 'productStatus',
      dataIndex: 'productStatus',
      render: (tag) => {
        switch (tag) {
          case "ACTIVE":
            return (
              <Tag color="success" icon={<CheckCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "UNAVAILABLE":
            return (
              <Tag color="error" icon={<CloseCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "HIDE":
            return (
              <Tag color={"warning"} icon={<ExclamationCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "OTHER":
            return (
              <Tag color={"default"} icon={<ClockCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
          default:
            return (<span />);
        }
      },
    },
    {
      title: 'Create time',
      dataIndex: 'createdDateTime',
      key: 'createdDateTime',
      render: (dateTime) => {
        return moment(dateTime).format('YYYY-MM-DD HH:mm:ss');
      },
        sorter: (a, b) => {
          const nameA = a.createdDateTime; // ignore upper and lowercase
          const nameB = b.createdDateTime; // ignore upper and lowercase
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
      title: 'Sale time',
      dataIndex: 'saleDateTime',
      key: 'saleDateTime',
      render: (dateTime) => {
        return moment(dateTime).format('YYYY-MM-DD HH:mm:ss');
      },
        sorter: (a, b) => {
          const nameA = a.saleDateTime; // ignore upper and lowercase
          const nameB = b.saleDateTime; // ignore upper and lowercase
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
        const result = await axios.get(`${domain}management/limitedProduct`);
        console.log(result);
        const productList = result.data.data.map(f => {
          return {...f, key: f.id};
        });
        setProducts(productList);
        
      } catch (error) {
        console.log(error);
        let msg = null;
        if (error.response.data.data) {
          msg = error.response.data.data.message;
        } else if (error.response.data.message) {
          msg = error.response.data.message;
        } else if (error.response) {
          msg = error.response.data;
        } else {
          msg = "Add failed. Internal server error.";
        }
        message.error(msg);
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
        dataSource={products}
      />
      <Link to={'/admin/limitedProduct/add'}>
        <Button
          type="primary"
        >
          Add new limited product
        </Button>
      </Link>
    </div>
  );
};

export default LimitedProductManagement;