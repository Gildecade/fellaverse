import { Button, Result, message, Steps, theme, Statistic, Row, Col, Image, Progress } from 'antd';
import { useState } from 'react';
import { useParams, Link, useLocation } from 'react-router-dom';
import { domain } from '../../config';
import axios from 'axios';
import GGko from '../../images/GGko.jpg';

const { Countdown } = Statistic;

const OrderSuccess = () => {
  const { title, subTitle } = useParams();
  const { token } = theme.useToken();
  const [current, setCurrent] = useState(0);
  const [percent, setPercent] = useState(0);
  const [color, setColor] = useState('#b3b3b3');
  const [success, setSuccess] = useState(false);
  const [finished, setFinished] = useState(false);
  const [disable, setDisabled] = useState(false);
  const [msg, setMsg] = useState("Please check and modify the following information before resubmitting.");
  const parameters = useLocation();
  // console.log(parameters);
  const product = parameters.state;
  // console.log(product);
  const deadline = product.time + 1000 * 60 * 3; // Dayjs is also OK
  // console.log(deadline);
  const delay = ms => new Promise(res => setTimeout(res, ms));

  const progressBar = async(value) => {
    setPercent((prevPercent) => {
      return value;
    });
    let cnt = 0;
    while (!finished && cnt < 20) {
      // console.log(percent);
      await delay(500);
      increase();
      cnt += 1;
    }
    if (success) {
      setPercent((prevPercent) => {
        return 100;
      });
    }
    next();
  };
  const increase = () => {
    setPercent((prevPercent) => {
      const newPercent = prevPercent + 5;
      if (newPercent > 100) {
        return 100;
      }
      return newPercent;
    });
  };
  const onFinish = () => {
    message.error("Time up!");
    setDisabled(true);
  };
  const OnChange = (value) => {
    // console.log(value);
    if (value < 60 * 1000) {
      setColor('red');
    }
  };
  const OnPay = async () => {
    try {
      next();
      progressBar(0);
      const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
      const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
      // const userId = null;
      axios.defaults.headers.common['Fellaverse-token'] = token;
      const result = await axios.post(`${domain}limitedProduct/pay`, {orderId: product.orderId, userId: userId});
      const msg = result.data.data;
      console.log(result.data);
      setSuccess((prevPercent) => {
        return true;
      });
      setMsg(msg);
      setFinished((prevPercent) => {
        return true;
      });
    } catch (error) {
      console.log(error);
      setSuccess(false);
      let msg = "Internal server error.";
      if (error.response.data.message) {
        msg = error.response.data.message;
      } else if (error.response.data) {
        msg = error.response.data;
      }
      message.error(msg);
      setMsg(msg);
      setFinished(true);
      next();
    }
  }
  const successPage = (<Result
    status="success"
    title={msg}
    subTitle={"Order number: " + product.orderId + " is completed. You can view orders in the order center."}
  />);
  const failurePage = (<Result
      status="error"
      title="Payment Failed"
      subTitle={msg}
    >
    </Result>);

  const steps = [
    {
      title: 'Place order',
      content: (<Result
        status="success"
        title={title}
        subTitle={subTitle}
      />),
    },
    {
      title: 'Order conformation',
      content: (
        <>
        <h1 style={{textAlign:'center', fontSize:'30px', marginTop:'-5px', marginBottom:'-20px'}}>Order conformation</h1>
        <Row>
        <Col lg={2} xxl={4}>
        </Col>
        <Col lg={22} xxl={20} >
          <Row>
            <Col lg={11} xxl={10}>
              <Image
                width={300}
                height={300}
                src={product.imageUrl}
                fallback={GGko}
              />
            </Col>
            <Col lg={13} xxl={14}>
              <Row style={{marginTop:'-70px', marginBottom:'-130px'}}>
                <p style={{fontSize:'40px',}}>Order Number: {product.orderId}</p>
              </Row>
              <Row style={{marginBottom:'-130px'}}>
                <p style={{fontSize:'40px'}}>Product name: {product.productName}</p>
              </Row>
              <Row style={{marginBottom:'-130px'}}>
                <p style={{fontSize:'40px'}}>Quantity: {product.quantity}</p>
              </Row>
              <Row style={{marginBottom:'-130px'}}>
                <p style={{fontSize:'40px', color:'#DC143C',}}>Sale price: {'$'+product.price}</p>
              </Row>
              <Row style={{marginBottom:'-130px'}}>
                <p style={{fontSize:'40px',}}>Total amount: {'$'+product.price * product.quantity}</p>
              </Row>
            </Col>
          </Row>
        </Col>
      </Row></>
      ),
    },
    {
      title: 'Payment',
      content: (<Progress
        type="circle"
        percent={percent}
        strokeColor={{
          '0%': '#108ee9',
          '100%': '#87d068',
        }}
      />),
    },
    {
      title: 'Result',
      content: success ? successPage: failurePage,
    },
  ];
  
  const next = () => {
    setCurrent((prevCurrent) => {
      return prevCurrent + 1;
    });
  };
  const prev = () => {
    setCurrent((prevCurrent) => {
      return prevCurrent - 1;
    });
  };
  const prevTwice = () => {
    setCurrent((prevCurrent) => {
      return prevCurrent - 2;
    });
  };
  const items = steps.map((item) => ({
    key: item.title,
    title: item.title,
  }));
  const contentStyle = {
    lineHeight: '110px',
    textAlign: 'center',
    color: token.colorTextTertiary,
    backgroundColor: token.colorFillAlter,
    borderRadius: token.borderRadiusLG,
    border: `1px dashed ${token.colorBorder}`,
    marginTop: 16,
  };
  return (
    <>
      <Row>
        <Col lg={10} xxl={11}></Col>
        <Col lg={6} xxl={2}>
          <Countdown title="Please pay for orders within: " value={deadline} onFinish={onFinish} onChange={OnChange} format="HH:mm:ss:SSS" valueStyle={{color:color, fontSize:'40px'}}/>
        </Col>
        <Col lg={8} xxl={11}></Col>
      </Row>
      <Steps current={current} items={items} />
      <div style={contentStyle}>{steps[current].content}</div>
      <div
        style={{
          marginTop: 24,
          textAlign: 'center',
        }}
      >
        {current === 0 && (
          <Button type="primary" onClick={() => next()}>
            Next
          </Button>
        )}
        {current === 1 && (
          <Button type="primary" onClick={OnPay} disabled={disable}>
            Pay
          </Button>
        )}
        {current === steps.length - 1 && (
          <Link to='/'>
            <Button type="primary">
              Go back to home
            </Button>
          </Link>
        )}
        {current === 1 && (
          <Button
            style={{
              margin: '0 8px',
            }}
            onClick={() => prev()}
          >
            Previous
          </Button>
        )}
        {current === 3 && (success?
          <></>:
          <Button
            style={{
              margin: '0 8px',
            }}
            onClick={() => prevTwice()}
          >
            Previous
          </Button>
        )}
      </div>
    
    
    </>
  );
}
export default OrderSuccess;