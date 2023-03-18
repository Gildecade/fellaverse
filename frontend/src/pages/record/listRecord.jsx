import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Popconfirm, message } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const RecordManagement = () => {
  const [records, setRecords] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const navigate = useNavigate();

  const handleDelete = async (key) => {
    try {
      const result = await axios.delete(`${domain}record/` + key); // TODO: should be /id/userid
      message.success("Delete successfully.");
      const data = result.data.data;
      console.log(data);
      const title = data;
      const subTitle = "Delete record success!";
      navigate(`/admin/success/${title}/${subTitle}`); //TODO
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

  const columns = [ // TODO: need to change cols
    {
      title: 'Record Time',
      dataIndex: 'recordTime',  //TODO
      key: 'recordTime', // TODO
      render: (text) => <a>{text}</a>, // TODO
      ...getColumnSearchProps('record time'),
        sorter: (a, b) => {
          const nameA = a.recordTime.toUpperCase(); // ignore upper and lowercase
          const nameB = b.recordTime.toUpperCase(); // ignore upper and lowercase
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
      title: 'Weights',
      dataIndex: 'weights',
      key: 'weights',
      ...getColumnSearchProps('weights'),
        sorter: (a, b) => a.weights - b.weights,
        sortDirections: ['descend', 'ascend'],
    },

    {
      title: 'Quantity',
      dataIndex: 'quantity',
      key: 'quantity',
      ...getColumnSearchProps('quantity'),
        sorter: (a, b) => a.quantity - b.quantity,
        sortDirections: ['descend', 'ascend'],
    },

    {
      title: 'Sets',
      dataIndex: 'sets',
      key: 'sets',
      ...getColumnSearchProps('sets'),
        sorter: (a, b) => a.sets - b.sets,
        sortDirections: ['descend', 'ascend'],
    },

    {
      title: 'Exercise',
      dataIndex: 'exercise',  //TODO
      key: 'exercise', // TODO
      render: (text) => <a>{text}</a>, // TODO
      ...getColumnSearchProps('exercise'),
        sorter: (a, b) => {
          const nameA = a.exercise.exerciseName.toUpperCase(); // TODO
          const nameB = b.exercise.exerciseName.toUpperCase(); 
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

        // TODO: get by user id
        const result = await axios.get(`${domain}record`); // TODO

        const recordList = result.data.data.map(f => {  // changed to record list
          return {...f, key: f.id};
        });
        setRecords(recordList);
        
      } catch (error) {
        console.log(error);
        let msg = "Internal server error."
        if (error.response.data.message) {
          msg = error.response.data.message;
        } else if (error.response.data) {
          msg = error.response.data;
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
        dataSource={records}
      />
      <Link to={'/record'}>
        <Button
          type="primary"
        >
          Add new record
        </Button>
      </Link>
    </div>
  );
};

export default RecordManagement;