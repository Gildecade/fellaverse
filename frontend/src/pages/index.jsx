import React, { useState, useEffect } from 'react';
import { Carousel, Row, Col, Image } from 'antd';
import GGko from '../images/GGko.jpg';

const contentStyle = {
  height: '160px',
  color: '#fff',
  lineHeight: '160px',
  textAlign: 'center',
  background: '#364d79',
};
// function(component) name
const Index = () => {
    // declare varibles and corresponding setter here
    // useState means varibles' initialized value, like "", [], false
    const [ username, setUsername] = useState(null);
  
    // useEffect: run this block when first rendering this page
    useEffect(() => {
      const username = localStorage.getItem('username') ? localStorage.getItem('username') : sessionStorage.getItem('username');
      setUsername(username);
    }, []);
  
    
    // return real html code here
    return (
      <div>
        { username ? 
          (
            <><Row>
              <Col lg={8} xxl={9}>
              </Col>
              <Col lg={8} xxl={5}>
                <Carousel autoplay>
                  <div>
                    <Image
                      width={420}
                      height={420}
                      src={'www.aaaa.com'}
                      fallback={GGko} />
                  </div>
                  <div>
                    <Image
                      width={420}
                      height={420}
                      src={'www.aaaa.com'}
                      fallback={GGko} />
                  </div>
                  <div>
                    <Image
                      width={420}
                      height={420}
                      src={'www.aaaa.com'}
                      fallback={GGko} />
                  </div>
                </Carousel>
              </Col>
              <Col lg={8} xxl={10}>
              </Col>
            </Row>
              <h1 style={{ marginLeft: '40px', }}>
                Welcome to Fellaverse, {username}.
              </h1></>
          ) :
          (
            <h1>
                Welcome to Fellaverse, please Login.
            </h1>
          )
        }
      </div>
    );
  }

  // remember to export your component
  export default Index;