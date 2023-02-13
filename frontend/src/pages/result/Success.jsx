import { Button, Result } from 'antd';
import { useParams, Link } from 'react-router-dom';

const Success = () => {
  const { title, subTitle } = useParams();
  return (
    <Result
      status="success"
      title={title}
      subTitle={subTitle}
      extra={[
        <Button type="primary" href='/'>
          Back Home
        </Button>
      ]}
    />
  );
}
export default Success;