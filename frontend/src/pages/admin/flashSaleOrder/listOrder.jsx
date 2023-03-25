import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, message } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../../config';
import axios from 'axios';
import dayjs from 'dayjs';
dayjs.extend(utc);
dayjs.extend(timezone);
import { Link, useNavigate } from 'react-router-dom';
import {
  CheckCircleOutlined,
  ClockCircleOutlined,
  CloseCircleOutlined,
  ExclamationCircleOutlined,
} from '@ant-design/icons';
var utc = require('dayjs/plugin/utc');
var timezone = require('dayjs/plugin/timezone');
dayjs.extend(utc);
dayjs.extend(timezone);

const FlashSaleOrderManagement = () => {
  const [products, setProducts] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const timezone = dayjs.tz.guess();
  const navigate = useNavigate();
  const timezone = dayjs.tz.guess();

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
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      ...getColumnSearchProps('id'),
        sorter: (a, b) => {
          const nameA = a.id; // ignore upper and lowercase
          const nameB = b.id; // ignore upper and lowercase
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
      title: 'Amount',
      dataIndex: 'amount',
      key: 'amount',
      ...getColumnSearchProps('amount'),
        sorter: (a, b) => {
          const nameA = a.amount; // ignore upper and lowercase
          const nameB = b.amount; // ignore upper and lowercase
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
      key: 'orderStatus',
      dataIndex: 'orderStatus',
      render: (tag) => {
        switch (tag) {
          case "COMPLETED":
            return (
              <Tag color="success" icon={<CheckCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "CANCELLED":
            return (
              <Tag color="error" icon={<CloseCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "ACTIVE":
            return (
              <Tag color={"warning"} icon={<ClockCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
            case "OTHER":
            return (
              <Tag color={"default"} icon={<ExclamationCircleOutlined />} key={tag}>
                {tag}
              </Tag>
            );
          default:
            return (<span />);
        }
      },
    },
    {
      title: 'Purchase time',
      dataIndex: 'purchaseDateTime',
      key: 'purchaseDateTime',
      render: (dateTime) => {
        return dayjs.utc(dateTime).tz(timezone).format('YYYY-MM-DD HH:mm:ss');
      },
        sorter: (a, b) => {
          const nameA = a.purchaseDateTime; // ignore upper and lowercase
          const nameB = b.purchaseDateTime; // ignore upper and lowercase
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
          <Link to={'detail/'+record.key} state={record}>Detail</Link>
        </Space>
      ),
    },
  ];
  
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/flashSaleOrder`);
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
          msg = "Internal server error.";
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
    </div>
  );
};

export default FlashSaleOrderManagement;