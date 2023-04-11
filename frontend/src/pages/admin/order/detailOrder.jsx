import { useLocation } from 'react-router-dom';
import { Button, Descriptions } from 'antd';
import moment from 'moment/moment';

const DetailOrderManagement = () => {
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
      <Descriptions title="User Information" bordered>
        <Descriptions.Item label="User ID">{record.user.id}</Descriptions.Item>
        <Descriptions.Item label="Username">{record.user.username}</Descriptions.Item>
        <Descriptions.Item label="Email">{record.user.email }</Descriptions.Item>
        <Descriptions.Item label="Phone number">{record.user.phoneNumber}</Descriptions.Item>
        <Descriptions.Item label="Wallet">{record.user.wallet}</Descriptions.Item>
        <Descriptions.Item label="Status">{record.user.status}</Descriptions.Item>
      </Descriptions>
      <br/>
      <Descriptions title="Course Information" bordered>
        <Descriptions.Item label="Course ID">{record.product.id}</Descriptions.Item>
        <Descriptions.Item label="Course name">{record.product.productName}</Descriptions.Item>
        <Descriptions.Item label="Description">{record.product.description }</Descriptions.Item>
        <Descriptions.Item label="Price">{record.product.price}</Descriptions.Item>
        <Descriptions.Item label="Status">{record.product.productStatus}</Descriptions.Item>
        <Descriptions.Item label="Create date time">{moment(record.product.createdDateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
        <Descriptions.Item label="link">{record.product.course.videoUrl}</Descriptions.Item>
      </Descriptions>
      <br/>
      <Descriptions title="Coach Information" bordered>
        <Descriptions.Item label="Coach ID">{record.product.course.user.id}</Descriptions.Item>
        <Descriptions.Item label="Coach name">{record.product.course.user.username}</Descriptions.Item>
        <Descriptions.Item label="Email">{record.product.course.user.email }</Descriptions.Item>
        <Descriptions.Item label="phoneNumber">{record.product.course.user.phoneNumber}</Descriptions.Item>
      </Descriptions>
      <br/>
      <Button type='primary' onClick={OnClick}>Back</Button>
    </>
  );
};
export default DetailOrderManagement;

