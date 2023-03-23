import React, { useState, useEffect } from 'react';
import { Badge, Calendar, Typography, Button } from 'antd';
import axios from 'axios';
import { domain } from '../../config';
const { Title } = Typography;
const getListData = (value) => {
  
  let listData;
  switch (value.date()) {
    case 8:
      listData = [
        {
          type: 'warning',
          content: 'This is warning event.',
        },
        {
          type: 'success',
          content: 'This is usual event.',
        },
      ];
      break;
    case 10:
      listData = [
        {
          type: 'warning',
          content: 'This is warning event.',
        },
        {
          type: 'success',
          content: 'This is usual event.',
        },
        {
          type: 'error',
          content: 'This is error event.',
        },
      ];
      break;
    case 15:
      listData = [
        {
          type: 'warning',
          content: 'This is warning event',
        },
        {
          type: 'success',
          content: 'This is very long usual event。。....',
        },
        {
          type: 'error',
          content: 'This is error event 1.',
        },
        {
          type: 'error',
          content: 'This is error event 2.',
        },
        {
          type: 'error',
          content: 'This is error event 3.',
        },
        {
          type: 'error',
          content: 'This is error event 4.',
        },
      ];
      break;
    default:
  }
  return listData || [];
};
const getMonthData = (value) => {
  if (value.month() === 8) {
    return 1394;
  }
};
const ViewSchedule = () => {
  
  const [ schedules, setSchedules] = useState([]);


  const monthCellRender = (value) => {
    const num = getMonthData(value);
    return num ? (
      <div className="notes-month">
        <section>{num}</section>
        <span>Backlog number</span>
      </div>
    ) : null;
  };
  const dateCellRender = (value) => {
    const listData = getListData(value);
    return (
      <ul className="events">
        {listData.map((item) => (
          <li key={item.content}>
            <Badge status={item.type} text={item.content} />
          </li>
        ))}
      </ul>
    );
  };
  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');

        axios.defaults.headers.common['Fellaverse-token'] = token;
        const result = await axios.get(`${domain}schedule/${userId}`);
        const schedules = result.data.data;
        console.log(schedules);

        setSchedules(schedules);
      } catch (error) {
        console.log(error);
      }
    }
    initialize();
  }, []);

  return (
    <div>
      <Title level={3}>View Schedule</Title>
      <Calendar dateCellRender={dateCellRender} monthCellRender={monthCellRender} />
    </div>);
};
export default ViewSchedule;
