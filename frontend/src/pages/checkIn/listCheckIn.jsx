import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, Popconfirm, message } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import {
  CheckCircleOutlined,
  ClockCircleOutlined,
  CloseCircleOutlined,
  ExclamationCircleOutlined,
} from '@ant-design/icons';
import moment from 'moment/moment';
dayjs.extend(customParseFormat);

const UserCheckIn = () => {
  const [checkIns, setCheckIns] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const navigate = useNavigate();
  const [userRecord, setUserRecord] = useState('');
  const delay = ms => new Promise(res => setTimeout(res, ms));
  const handleDelete = async (record) => {
    try {
      console.log('record',record);
      setUserRecord(record);
      const data = {
        "id": {
          "id": record.id.id,
          "userId": record.id.userId
        },
        "user": {
          "id": record.user.id
        },
        "startDateTime": record.startDateTime,
        "endDateTime": record.endDateTime,
        "weight": record.weight
      };
      console.log('data',data);
      const result = await axios.delete(`${domain}checkin`, {data});
      message.success("Delete successfully.");
      const userData = result.data.data;
      await delay(500);
      // console.log(data);
      const title = userData;
      const subTitle = "Delete check in success!";
      //navigate(`/admin/success/${title}/${subTitle}`);
      window.location = `checkIn`;
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
      title: 'Start time',
      dataIndex: 'startDateTime',
      key: 'startDateTime',
      render: (dateTime) => {
        return moment(dateTime).format('YYYY-MM-DD HH:mm:ss');
      },
        sorter: (a, b) => {
          const nameA = a.startDateTime; // ignore upper and lowercase
          const nameB = b.startDateTime; // ignore upper and lowercase
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
      title: 'End time',
      dataIndex: 'endDateTime',
      key: 'endDateTime',
      render: (dateTime) => {
        return moment(dateTime).format('YYYY-MM-DD HH:mm:ss');
      },
        sorter: (a, b) => {
          const nameA = a.endDateTime; // ignore upper and lowercase
          const nameB = b.endDateTime; // ignore upper and lowercase
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
      title: 'Body Weight',
      key: 'weight',
      dataIndex: 'weight',
      render: (tag) => {
        return (
          <Tag color="success" icon={<CheckCircleOutlined />} key={tag}>
            {tag}
          </Tag>
        );
      }
    },

    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <Link to={'edit'} state={record}>Edit</Link>
          <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(record)}>
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
        const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
        const result = await axios.get(`${domain}checkin/` + userId);
        const checkInList = result.data.data.map(f => {
          return {...f, key: f.id};
        });
        setCheckIns(checkInList);
        //console.log(checkIns);
        const sortedCheckIns = [...checkInList].sort((a, b) => dayjs(a.startDateTime).diff(dayjs(b.startDateTime))<0 ? 1 : -1);
        setCheckIns(sortedCheckIns);
        //console.log(sortedCheckIns);

      } catch (error) {
        console.log(error);
        message.error(error.response.data.message);
      }

    }
    initialize();
  }, []);

  return (
    <div>
      <Link to={'/checkIn/add'}>
        <Button
          type="primary"
        >
          Add new check in
        </Button>
      </Link>
      <Table
        columns={columns}
        pagination={{
          position: ['bottomRight'],
        }}
        dataSource={checkIns}
      />
    </div>
  );
};

export default UserCheckIn;