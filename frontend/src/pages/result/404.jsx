import { Button, Result } from 'antd';
import { Link } from 'react-router-dom';

const NotFound = () => {
  var homePage = null;
  if (localStorage.getItem("roles") || sessionStorage.getItem("roles")) {
    homePage = "/admin";
  } else {
    homePage = "/";
  }
  return (
    <Result
      status="404"
      title="404"
      subTitle="Sorry, the page you visited does not exist."
      extra={[
        <Link to={homePage} key="home-unique">
          <Button type="primary" key="home-unique">
            Back Home
          </Button>
        </Link>
      ]}
    />
  );
}
export default NotFound;