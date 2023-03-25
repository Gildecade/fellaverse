import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import React, { useState, useEffect } from 'react';
import { ProList } from '@ant-design/pro-components';
import { Carousel, Image, Button, Tag, message } from 'antd';
import GGko from '../images/GGko.jpg';
import axios from 'axios';
import { domain } from '../config';
import dayjs from 'dayjs';

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
    const [ann, setAnn] = useState([]);
    const IconText = ({ icon, text }) => (
      <span>
        {React.createElement(icon, { style: { marginInlineEnd: 8 } })}
        {text}
      </span>
    )
    const mapDescription = (description) => {
      if (Array.isArray(description) && description.length > 0) {
        return (
          description.map((element) => (
            <Tag>{element}</Tag>
          ))
        );
      } else {
        return null; // or any other fallback value you want to return
      }
    }
    const dataSource = ann;
    // const dataSource = [
    //   {
    //     title: 'title1',
    //     description: ["tag1","tag2"],
    //     // actions: 156,
    //     extra: "https://fellaverse.blob.core.windows.net/product-images/Aerial.png",
    //     content: 'In the project proposal, students should explain the topic of their project, what types of results they hope to obtain, and what some of the technical issues are that you will need to address. For graduate students, the course project could be related to their own ongoing research as long as it also has clear connections with the course material. If necessary, the course instructor can help with finding topics. Each proposal should include all the following.',
    //   },
    //   {
    //     title: 'title2',
    //     extra: "https://fellaverse.blob.core.windows.net/product-images/Aerial.png",
    //     // extra: {
    //     //   render: () => (
    //     //     <img
    //     //       width={272}
    //     //       alt="logo"
    //     //       src="https://fellaverse.blob.core.windows.net/product-images/p5s_home.jpg"
    //     //     />
    //     //   ),
    //     // },
    //   },
    //   {
    //     title: 'title3',
    //     extra: "https://fellaverse.blob.core.windows.net/product-images/p5s_home.jpg",
    //   },
    //   {
    //     title: 'title4',
    //     extra: "https://fellaverse.blob.core.windows.net/product-images/forecast letter.png",
    //   },
    // ]; 
    // useEffect: run this block when first rendering this page
    useEffect(() => {
      const initialize = async (prop) => {
        try{
          const username = localStorage.getItem('username') ? localStorage.getItem('username') : sessionStorage.getItem('username');
          setUsername(username);
          const result = await axios.get(`${domain}management/prolist`);
          console.log(result);
          const proList = result.data.data.map(f => {
            return {...f, key: f.id};
          });
          setAnn(proList);

        } catch (error) {
          console.log(error);
          message.error(error.response.data.message);
        }
      }
      initialize();
    }, []);
  
    
    // return real html code here
    return (
      <div>
        <Carousel autoplay style={{textAlign:"center"}}>
          {
            ann.map((item) => (
              <div>
                <Image
                  //width={800}
                  height={450}
                  src={item.extra}
                  fallback={GGko} />
              </div>
            ))
          }
        </Carousel>
        { username ? 
          (
            <>
              <h1 style={{ marginLeft: '40px', }}>
                Welcome to Fellaverse, {username}.
              </h1>
            </>
          ) :
          (
            <h1>
                Welcome to Fellaverse, please Login.
            </h1>
          )
        }
        <ProList 
          // toolBarRender={() => {
          //   return [
          //     <Button key="3" type="primary">
          //       新建
          //     </Button>,
          //   ];
          // }}
          itemLayout="vertical"
          rowKey="id"
          headerTitle="what's new"
          dataSource={dataSource}
          metas={{
            title: {},
            description: {
            render: (description) => (
              <>
                 <tag color="cyan">{dayjs(description).format("YYYY-MM-DD").toString()}</tag>
              </>
            ),
            },
            actions: {
              render: (text) => [
                // <IconText icon={StarOutlined} text="156" key="list-vertical-star-o" />,
                // <IconText icon={LikeOutlined} text={1} key="list-vertical-like-o" />,
                // <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
              ],
            },
            extra: {
              render: (src) => (
                <img
                  width={272}
                  alt="logo"
                  src={src}
                  fallback = {GGko}
                />
              ),
            },
            content: {
              render: (content) => {
                return (
                  <div>
                    {content}
                  </div>
                );
              },
            },
          }}
        />
      </div>
    );
  }

  // remember to export your component
  export default Index;