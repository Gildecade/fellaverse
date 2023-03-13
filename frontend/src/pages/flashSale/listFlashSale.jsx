import React, { useState, useEffect } from 'react';
import { Space, message, Card } from 'antd';
import { domain } from '../../config';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import GGko from '../../images/GGko.jpg';

const { Meta } = Card;

const FlashSale = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  
  useEffect(() => {
    const initialize = async () => {
      try {
        const result = await axios.get(`${domain}limitedProduct`);
        // console.log(result);
        const productList = result.data.data;
        setProducts(productList);
        setLoading(false);
        // console.log(productList);
      } catch (error) {
        console.log(error);
        message.error(error.response.data.message);
      }

    }
    initialize();
  }, []);

  return (
    <div>
      <Space size={[8, 16]} wrap>
        {products.map((product) => (
          // eslint-disable-next-line react/no-array-index-key
          <Link to={'/flash-sale/'+product.id} state={product}>
            <Card 
              hoverable={true}
              style={{
                width: 240,
              }}
              loading={loading}
              // cover={<img alt={product.productName} src={GGko}></img>}
              cover={<img alt="example" src={product.imageUrl} height='250px' width='250px'></img>}
            >
              <Meta title={product.productName} />
              <Space size={'middle'} align='baseline' style={{marginTop:'-15px', marginBottom:'-50px'}}>
                <p style={{fontSize:'25px',}}>{'$'+product.price}</p>
                <p style={{fontSize:'20px', color:'#DC143C',}}>On sale</p>
              </Space>
              {/* <p style={{fontSize:'20px', color:'#FF7F50', marginTop:'-5px', marginBottom:'-20px', }}>{'$'+product.price+' On sale'}</p> */}
            </Card>
          </Link>
        ))}
      </Space>
    </div>
  );
};

export default FlashSale;