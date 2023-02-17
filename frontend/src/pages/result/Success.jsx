import { Button, Result } from 'antd';
import { useParams, Link } from 'react-router-dom';

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
        <Link to={homePage}>
          <Button type="primary">
            Back Home
          </Button>
        </Link>
      ]}
    />
  );
}
export default Success;