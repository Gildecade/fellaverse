import React, { useState, useEffect, useRef } from 'react';
import { Button, InputNumber, Space, Table, Tag, Popconfirm, message, Card, Image, Row, Col } from 'antd';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate, useLocation, useParams } from 'react-router-dom';
import GGko from '../../images/GGko.jpg';
import moment from 'moment/moment';

const { Meta } = Card;

const DetailFlashSale = () => {
  const [loading, setLoading] = useState(true);
  const [disabled, setDisabled] = useState(false);
  const [quantity, setQuantity] = useState(1);
  const productId = useParams().id;
  const parameters = useLocation();
  const product = parameters.state;
  // console.log(product);
  const delay = ms => new Promise(res => setTimeout(res, ms));

  const buy = async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token');
      const userId = localStorage.getItem('userId') ? localStorage.getItem('userId') : sessionStorage.getItem('userId');
      axios.defaults.headers.common['Fellaverse-token'] = token;
      const values = {id: productId, userId: userId, quantity: quantity, purchaseDateTime: moment()};
      const result = await axios.post(`${domain}limitedProduct/purchase`, values);
      await delay(1000);
    } catch (error) {
      setLoading(false);
      console.log(error);
      if (error.response) {
        let msg = error.response.data.message;
        message.error(msg);
      } else {
        message.error("Purchase failed. Internal server error.");}
    }
  };

  useEffect(() => {
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
                  <Button type="primary" size={'large'} disabled={disabled} onClick={buy}>
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