import { Button, Result } from 'antd';
import { useParams} from 'react-router-dom';

const Success = () => {
  const { title, subTitle } = useParams();
  var homePage = null;
  if (localStorage.getItem("roles") || sessionStorage.getItem("roles")) {
    homePage = "/admin";
  } else {
    homePage = "/";
  }
  return (
    <Result
      status="success"
      title={title}
      subTitle={subTitle}
      extra={[
        <Button type="primary" href={homePage}>
          Back Home
        </Button>
      ]}
    />
  );
}
export default Success;