import { useLocation } from 'react-router-dom';
import { Button, Descriptions } from 'antd';
import moment from 'moment/moment';

const DetailSaleOrder = () => {
  const parameters = useLocation();
  const record = parameters.state;

  const OnClick = () => {
    window.history.back();
  };
  return (
    <>
      <Descriptions title="Flash Sale Order Information" bordered>
        <Descriptions.Item label="Order ID">{record.id}</Descriptions.Item>
        <Descriptions.Item label="Quantity">{record.quantity }</Descriptions.Item>
        <Descriptions.Item label="Amount">{record.amount}</Descriptions.Item>
        <Descriptions.Item label="Purchase date time">{moment(record.purchaseDateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
        <Descriptions.Item label="Status">{record.orderStatus}</Descriptions.Item>
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
      <Descriptions title="Limited Product Information" bordered>
        <Descriptions.Item label="Limited product ID">{record.limitedProduct.id}</Descriptions.Item>
        <Descriptions.Item label="Product name">{record.limitedProduct.productName}</Descriptions.Item>
        <Descriptions.Item label="Description">{record.limitedProduct.description }</Descriptions.Item>
        <Descriptions.Item label="Price">{record.limitedProduct.price}</Descriptions.Item>
        <Descriptions.Item label="Quantity">{record.limitedProduct.quantity }</Descriptions.Item>
        <Descriptions.Item label="Status">{record.limitedProduct.productStatus}</Descriptions.Item>
        <Descriptions.Item label="Create date time">{moment(record.limitedProduct.createdDateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
        <Descriptions.Item label="Sale date time">{moment(record.limitedProduct.saleDateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
      </Descriptions>
      <br/>
      <Button type='primary' onClick={OnClick}>Back</Button>
    </>
  );
};
export default DetailSaleOrder;

