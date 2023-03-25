import React, { useState, useEffect, useRef } from 'react';
import { Button, Input, Space, Table, Tag, Popconfirm, message, Badge, Calendar, Modal } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import dayjs from 'dayjs';
import customParseFormat from 'dayjs/plugin/customParseFormat';
import './Badge.css';
import './hr.css';
dayjs.extend(customParseFormat);

const UserCheckIn = () => {
  const [checkIns, setCheckIns] = useState([]);
  const navigate = useNavigate();
  const delay = ms => new Promise(res => setTimeout(res, ms));
  var calendarItem = [];
  var nowMonth;
  var nowYear;
  const [calendarList, setCalendarList] = useState([]);
  const [calendarListMonth, setCalendarListMonth] = useState([]);
  const [monthValue, setMonthValue] = useState(0);
/////////////////// modal //////////////////////////
  const maskStyle = {
    backgroundColor: 'rgba(0, 0, 0, 0.2)',
  };
  const handleButton = (data) => {
    navigate('edit',
    {
        state: data,
    });
    window.location.reload(false);
  }
  const handleContent = (calendarListItem) => {
    //console.log("line46", calendarListItem);
     return (
       <Space size="1" direction="vertical">
         {calendarListItem.map((item) => (
            <>
            <Space size="middle">
              <p><b>start time : </b>{dayjs(item.startDateTime).format("HH:mm")}</p>
              <Popconfirm title="Sure to edit?" onConfirm={() => handleButton(item)}>
                <a>Edit</a>
              </Popconfirm>
              <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(item)}>
                <a>Delete</a>
              </Popconfirm>
            </Space>
              <small>body weight : {item.weight}</small>
              <hr class="rounded"></hr>
            </>
         ))}
       </Space>
     )
  };
////////////////////// calendar ///////////////////////////////
  const getListData = (value) => {
    let listData = [];
    let listData2;

    //console.log(calendarList);

    switch (value.date()) {
      case value.date():
        let day2 = calendarList[value.date()];
        try {
            //console.log("ssss",day2);
            day2.forEach(day3 => {
              if (day3) {
                listData2 = [
                  {
                    type: 'success',
                    content: 'start:\t ' + dayjs(day3.startDateTime).format("HH:mm"),
                  },
                  {
                    type: 'warning',
                    content: '   end:\t ' + dayjs(day3.endDateTime).format("HH:mm"),
                  },
                ];
                listData.push(listData2[0]);
                listData.push(listData2[1]);
                //console.log(listData);
              }
            });
        }catch (error) {
          //console.log("not this one");
        }
        break;
      default:
    }
    return listData || [];
  };
  const dateCellRender = (value) => {
    const listData = getListData(value, calendarList);
    return (
      <ul className="events">
        {listData.map((item) => (
          <div key={item.content}>
            <Badge status={item.type} text={item.content} onClick={event => onClick(event, calendarList[value.date()])}/>
          </div>
        ))}
      </ul>
    );
  };

  const getMonthData = (value) => {
    let listDataMonth = 0;

    switch(value.month()) {
      case value.month():
        let month2 = calendarListMonth[value.month()];
        try {
          month2.forEach(month3 => {
            if (month3) {
              listDataMonth++;
            }
          }) 
        }catch (error) {

        }
        break;
      default:
    }
    //console.log(listDataMonth);
    return listDataMonth || 0;
  };

  const monthCellRender = (value) => {
    // work around
    var num = getMonthData(dayjs(value).add(1,'month'));
    if (num != 0) {
      return (
        <div className="notes-month">
            <div key={value.month()}>
              <Badge status="success" text={num}/>
            </div>
        </div>
      );
    }
  };

  const onClick = (event, calendarList) => {
    //console.log("cl",dayjs(calendarList[0].startDateTime).format("DD"));
    calendarItem = calendarList;
    const modal = Modal.info();
    modal.update({
      title: dayjs(calendarItem[0].startDateTime).format("MM-DD"),
      content: handleContent(calendarItem),
      centered: true,
      maskClosable: true,
      maskStyle,
    });
    //showModal();
    //console.log(event.target.innerText);
  }

  const onPanelChange = (e) => {
    nowMonth = parseInt(e.format("YYYYM"));
    nowYear = parseInt(e.format("YYYY"));

    let calendarListTemp = [];
    for (let i =0; i<31; i++) {
      calendarListTemp.push([]);
    }
    let calendarListMonthTemp = [];
    for (let i =0; i<12; i++) {
      calendarListMonthTemp.push([]);
    }
    checkIns.forEach(element => {
      if (element.length!=0) {
        //console.log("e=", parseInt(dayjs(element.startDateTime).format("MM")), "n2=", nowMonth, "TF=", parseInt(dayjs(element.startDateTime).format("MM")) == nowMonth);
        if (parseInt(dayjs(element.startDateTime).format("YYYYM")) == nowMonth) {
          let day = dayjs(element.startDateTime).format("DD");
          //console.log(day);
          calendarListTemp[day].push(element);
        }
        //console.log(calendarListTemp);
        if (parseInt(dayjs(element.startDateTime).format("YYYY")) == nowYear) {
          let month = dayjs(element.startDateTime).format("M");
          //console.log(day);
          calendarListMonthTemp[month].push(element);
        }
      }
    });
    setCalendarList(calendarListTemp);
    setCalendarListMonth(calendarListMonthTemp);
  }
/////////////////////// delete /////////////////////////////
  const handleDelete = async (record) => {
    try {
      console.log('record',record);
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
/////////////////////// column /////////////////////////////
  const columns = [
    {
      title: 'Start time',
      dataIndex: 'startDateTime',
      key: 'startDateTime',
      render: (dateTime) => {
        return dayjs(dateTime).format('YYYY-MM-DD HH:mm:ss');
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
        return dayjs(dateTime).format('YYYY-MM-DD HH:mm:ss');
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
          <Tag color="success" key={tag}>
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
        const sortedCheckIns = [...checkInList].sort((a, b) => dayjs(a.startDateTime).diff(dayjs(b.startDateTime))<0 ? -1 : 1);
        setCheckIns(sortedCheckIns);
        //console.log(sortedCheckIns);
        nowMonth = parseInt(dayjs().format("YYYYM"));
        nowYear = parseInt(dayjs().format("YYYY"));
        //console.log(nowMonth);
        let calendarListTemp = [];
        let calendarListMonthTemp = [];
        for (let i =0; i<31; i++) {
          calendarListTemp.push([]);
        }
        for (let i =0; i<12; i++) {
          calendarListMonthTemp.push([]);
        }
        sortedCheckIns.forEach(element => {
          if (element.length!=0) {
            //console.log("e=", parseInt(dayjs(element.startDateTime).format("MM")), "n2=", nowMonth, "TF=", parseInt(dayjs(element.startDateTime).format("MM")) == nowMonth);
            if (parseInt(dayjs(element.startDateTime).format("YYYYM")) == nowMonth) {
              let day = dayjs(element.startDateTime).format("DD");
              calendarListTemp[day].push(element);
            }

            if (parseInt(dayjs(element.startDateTime).format("YYYY")) == nowYear) {
              let month = dayjs(element.startDateTime).format("M");
              calendarListMonthTemp[month].push(element);
            }
          }
        });
        setCalendarList(calendarListTemp);
        setCalendarListMonth(calendarListMonthTemp);
      } catch (error) {
        console.log(error);
        message.error(error.response.data.message);
      }

    }

    initialize();
  }, []);

  return (
    <div>
      <Space direction="vertical" size="middle">
        <Link to={'/checkIn/add'}>
          <Button
            type="primary"
            block
          >
            Add new check in
          </Button>
        </Link>
        <Calendar dateCellRender={dateCellRender} monthCellRender={monthCellRender} onPanelChange={onPanelChange}/>
        {/* <Calendar dateCellRender={dateCellRender} monthCellRender={monthCellRender} onPanelChange={onPanelChange}/> */}
        <Link to={'/checkIn/add'}>
          <Button
            type="primary"
            block
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
      </Space>
    </div>
  );
};

export default UserCheckIn;