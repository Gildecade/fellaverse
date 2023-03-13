import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, Popconfirm, message, Popover, Modal, Radio } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [searchText, setSearchText] = useState('');
  const [searchedColumn, setSearchedColumn] = useState('');
  const searchInput = useRef(null);
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [value, setValue] = useState(1);
  const [balance, setBalance] = useState(0);
  const [userRecord, setUserRecord] = useState('');
  const delay = ms => new Promise(res => setTimeout(res, ms));
  const [buttonDisable, setButtonDisable] = useState(true);
  const [currentId, setCurrentId] = useState(9999);
  const data = {
    "id": userRecord.id,
    "wallet": userRecord.wallet,
    "status": userRecord.status,
  };
  const handleData = (isStatus, record) => {
    console.log(userRecord);
    data.id = record.id;
    if (isStatus == 1) {
      data.wallet = record.wallet;
      data.status = value;
    } else {
      data.wallet = balance;
      data.status = record.status;
    }
  }
  const handleEdit = async (isStatus, record) => {
    try {
      handleData(isStatus, record);
      console.log(data);
      const request = {...data};
      const result = await axios.put(`${domain}management/user`, request);
      console.log(result);
      message.success("Edit successfully.");
      const title = data;
      await delay(1000);
      const subTitle = "Edit user info success!";
      //navigate(`/admin/user`);
      window.location = `/admin/user`;
      //navigate(`/admin/success/${title}/${subTitle}`);
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
      title: 'Balance',
      dataIndex: 'wallet',
      key: 'wallet',
      ...getColumnSearchProps('wallet'),
       // sorter: (a, b) => {
       //   const nameA = a.phoneNumber.toUpperCase(); // ignore upper and lowercase
       //   const nameB = b.phoneNumber.toUpperCase(); // ignore upper and lowercase
       //   if (nameA < nameB) {
       //     return -1;
       //   }
       //   if (nameA > nameB) {
       //     return 1;
       //   }
       // 
       //   // names must be equal
       //   return 0;
       // },
        render: (wallet, record) => {
          const onClick = (e) => {
            setUserRecord(record);
            // TODO if edit one number but press submit another user will still update.
            console.log("submit ",balance);
            console.log("button", e.target)
            if (balance != wallet) {
              handleEdit(0, record);
            }
          }
          const handleChange = (e) => {
            setCurrentId(record.id);
            setBalance(e.target.value);
          //  console.log("currentId:", currentId);
          //  console.log("balance:", e.target.value);
          //  console.log("wallet", Number(wallet));
          //  console.log("b:",e.target.value.toString(), "w:", wallet.toString());
          //  console.log(e.target.value.toString() == wallet.toString());
          }
          return (
            <Input.Group compact value={balance}>
              <Input type="number" onChange={handleChange} style={{width: 'calc(80% - 50px)',}}
                defaultValue={wallet}
              />
              <Button key={record.id} value={balance} type="primary" onClick={onClick} disabled={record.id != currentId || wallet.toString() == balance.toString()}>Submit</Button>
            </Input.Group>
          )
        },
        sortDirections: ['descend', 'ascend'],
    },
    {
      title: 'Status',
      key: 'status',
      dataIndex: 'status',
      ...getColumnSearchProps('status'),
        render: (status, record) => {
          const showModal = (event, record) => {
            setUserRecord(record);
            setIsModalOpen(true);
          };
          const handleOk = (event, record) => {
            console.log('radio send', value);
            if (value != 1) {
              handleEdit(1, userRecord);
              setIsModalOpen(false);
            }
            else
              message.error('please select a status!');
          };
          const handleCancel = () => {
            setIsModalOpen(false);
          };
          let buttonStyle = {
            backgroundColor: 'green',
            color: 'white',
          };
          if (status == "LOCKED") {
            buttonStyle.backgroundColor = 'red';
          } else if (status == "UNKNOWN") {
            buttonStyle.backgroundColor = 'magenta';
          }
          const onChange = (e) => {
            console.log('radio checked', e.target.value);
            setValue(e.target.value);
          };
            return (
              <>
                  <Button type="primary" onClick={(event) => showModal(event, record)} style={buttonStyle} key={status} >
                    {status}
                  </Button>
                  <Modal title="Status Change to:" open={isModalOpen} onOk={(event) => handleOk(event, record)} onCancel={handleCancel}>
                    <Radio.Group onChange={onChange} value={value} defaultValue="a">
                      <Space direction="vertical">
                        <Radio value={"NORMAL"} >NORMAL</Radio>
                        <Radio value={"LOCKED"} >LOCKED</Radio>
                        <Radio value={"UNKNOWN"} >UNKNOWN</Radio>
                      </Space>
                    </Radio.Group>
                  </Modal>
              </>
            )
        },
        //sorter: (a, b) => {
        //  const nameA = a.phoneNumber.toUpperCase(); // ignore upper and lowercase
        //  const nameB = b.phoneNumber.toUpperCase(); // ignore upper and lowercase
        //  if (nameA < nameB) {
        //    return -1;
        //  }
        //  if (nameA > nameB) {
        //    return 1;
        //  }
        
        //  // names must be equal
        //  return 0;
        //},
    },
    {
      title: 'User Function',
      key: 'userFunction',
      render: (_, record) => (
        <Space size="small">
          <Link to={'edit/'+record.key} state={record}>Edit</Link>
        </Space>
      ),
    },
  ];
  
  useEffect(() => {
    const initialize = async (prop) => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}management/user`);
        // console.log(result);
        const userList = result.data.data.map(f => {
          return {...f, key: f.id};
        });
        setUsers(userList);
        const sortedUsers = [...userList].sort((a, b) => a.username < b.username ? -1 : 1);
        setUsers(sortedUsers);
        
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
        dataSource={users}
      />
    </div>
  );
};
export default UserManagement;