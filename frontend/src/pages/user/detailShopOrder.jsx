import { useLocation } from 'react-router-dom';
import { Button, Descriptions, Card, Image } from 'antd';
import moment from 'moment/moment';
import GGko from '../../images/GGko.jpg';

const DetailShopOrder = () => {
  const parameters = useLocation();
  const record = parameters.state;

  const OnClick = () => {
    window.history.back();
  };
  return (
    <>
      <Descriptions title="Shop Order Information" bordered>
        <Descriptions.Item label="Order ID">{record.id}</Descriptions.Item>
        <Descriptions.Item label="Quantity">{record.quantity }</Descriptions.Item>
        <Descriptions.Item label="Amount">{record.amount}</Descriptions.Item>
        <Descriptions.Item label="Purchase date time">{moment(record.purchaseDateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
      </Descriptions>
      <br/>
      <Descriptions title="Course Information" bordered>
        <Descriptions.Item label="course name">{record.product.productName}</Descriptions.Item>
        <Descriptions.Item label="Description">{record.product.description }</Descriptions.Item>
        <Descriptions.Item label="Price">{record.product.price}</Descriptions.Item>
        <Descriptions.Item label="link">{record.product.course.videoUrl}</Descriptions.Item>

      </Descriptions>
      <br/>
      <Card 
        hoverable={true}
        style={{
          width: 250,
        }}
        // cover={<img alt={product.productName} src={GGko}></img>}
        cover={<Image alt={record.limitedProduct.productName} src={record.limitedProduct.imageUrl} height='250px' width='250px' fallback={GGko}></Image>}
      >
      </Card>
      <br />
      <Button type='primary' onClick={OnClick}>Back</Button>
    </>
  );
};
export default DetailShopOrder;

