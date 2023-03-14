import React, { useState, useEffect, useRef } from 'react';
import { Button, InputNumber, Space, Table, Tag, Popconfirm, message, Card, Image, Row, Col } from 'antd';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate, useLocation, useParams } from 'react-router-dom';
import GGko from '../../images/GGko.jpg';
import moment from 'moment/moment';

const { Meta } = Card;

const DetailFlashSale = () => {
  const [loading, setLoading] = useState(false);
  const [disabled, setDisabled] = useState(false);
  const [quantity, setQuantity] = useState(1);
  const productId = useParams().id;
  const parameters = useLocation();
  const navigate = useNavigate();
  const product = parameters.state;
  // console.log(product);
  const delay = ms => new Promise(res => setTimeout(res, ms));

  const buy = async () => {
    try {
      setLoading(true);
      const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
      const values = {id: productId, userId: userId, quantity: quantity, purchaseDateTime: moment()};
      const result = await axios.post(`${domain}limitedProduct/purchase`, values);
      // console.log(result);
      // console.log(values.purchaseDateTime.valueOf());
      const title = "Place order successfully!";
      const subTitle = "Your order number is: " + result.data.data;
      message.success(title);
      await delay(1000);
      navigate(`/flash-sale/${title}/${subTitle}`, {state:{quantity: quantity, productName:product.productName, orderId: result.data.data, price:product.price, imageUrl: product.imageUrl, time: values.purchaseDateTime.valueOf()}});
    } catch (error) {
      setLoading(false);
      console.log(error);
      let msg = "Purchase failed. Internal server error.";
      if (error.response.data.message) {
        msg = error.response.data.message;
      } else if (error.response.data) {
        msg = error.response.data;
      }
      message.error(msg);
    }
  };

  useEffect(() => {
    const initialize = async () => {
      try {
        const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
        axios.defaults.headers.common['Fellaverse-token'] = token;
        await axios.get(`${domain}limitedProduct/${product.id}`);
      } catch (error) {
        console.log(error);
        let msg = "Internal server error.";
        if (error.response.data.message) {
          msg = error.response.data.message;
        } else if (error.response.data) {
          msg = error.response.data;
        }
        message.error(msg);
      }
    }
    initialize();
    var now = moment();
    if (now.isBefore(product.saleDateTime)) {
      setDisabled(true);
    }
  }, []);

  return (
    <div>
      <Row>
        <Col lg={2} xxl={4}>
        </Col>
        <Col lg={20} xxl={16} >
          <Row>
            <Col lg={10} xxl={7}>
              <Image
                width={400}
                height={400}
                src={product.imageUrl}
                fallback={GGko}
              />
            </Col>
            <Col lg={14} xxl={17}>
              <Row style={{marginBottom:'-20px', }}>
                <p style={{fontSize:'40px'}}>{product.productName}</p>
              </Row>
              <Row>
                <p style={{fontSize:'30px', color:'#DC143C',}}>{'$'+product.price}</p>
              </Row>
              <Row style={{marginBottom:'20px', }}>
                <p style={{fontSize:'20px',}}>{product.description}</p>
              </Row>
              <Row>
                <Space>
                  <InputNumber min={1} max={10} defaultValue={quantity} onChange={setQuantity}/>
                  <Button type="primary" size={'large'} disabled={disabled} onClick={buy} loading={loading}>
                    Purchase
                  </Button>
                </Space>
              </Row>
            </Col>
          </Row>
        </Col>
        <Col lg={2} xxl={4}>
          <Row>
            
          </Row>
        </Col>
      </Row>
    </div>
  );
};

export default DetailFlashSale;