package com.fellaverse.backend.test.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class RepoTest {
    String adminToken = "";
    String userToken = "";


    /////////////////////////////////6001//////////////////////////////////////////////////
    @Test
    void tokenLoginSuccessSelf() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:6001/api/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"superadmin@admin.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        adminToken = responseBody.substring(10, i);
        System.out.println("token: "+ adminToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void tokenUserLoginSuccessSelf() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:6001/api/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"user01@user.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        userToken = responseBody.substring(10, i);
        System.out.println("user token: "+ userToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    ////////////////////////////////8001////////////////////////////////////////////////

    @Test
    void getAllUser() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/user")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getAllAdmin() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    //////////////////////////7501////////////////////////////////
    @Test
    void getCourse() throws Exception {
        HttpResponse<String> response = Unirest.get("http://localhost:7501/api/coach/course")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    /////////////////////////////////6001//////////////////////////////////////////////////
    @Test
    void tokenLoginSuccessGateway() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/api/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"superadmin@admin.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        adminToken = responseBody.substring(10, i);
        System.out.println("token: "+ adminToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void tokenUserLoginSuccessGateway() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/api/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"user01@user.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        userToken = responseBody.substring(10, i);
        System.out.println("user token: "+ userToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    ////////////////////////////////8001////////////////////////////////////////////////

    @Test
    void getAllUserGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/user")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getAllAdminGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    //////////////////////////7501////////////////////////////////
    @Test
    void getCourseGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/coach/course")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    /////////////////////////////////6001//////////////////////////////////////////////////
    @Test
    void tokenLoginSuccessConsumer() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:6002/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"superadmin@admin.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        adminToken = responseBody.substring(10, i);
        System.out.println("token: "+ adminToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void tokenUserLoginSuccessConsumer() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:6002/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"user01@user.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        userToken = responseBody.substring(10, i);
        System.out.println("user token: "+ userToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    ////////////////////////////////8001////////////////////////////////////////////////

    @Test
    void getAllUserConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/user")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getAllAdminConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    //////////////////////////7501////////////////////////////////
    @Test
    void getCourseConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:7701/coach/course")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////

    /////////////////////////////////6001//////////////////////////////////////////////////
    @Test
    void tokenLoginSuccessConsumerGateway() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"superadmin@admin.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        adminToken = responseBody.substring(10, i);
        System.out.println("token: "+ adminToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void tokenUserLoginSuccessConsumerGateway() throws Exception {
        HttpResponse<String> response = Unirest.post("http://localhost:9001/auth/login")
                .header("content-type", "application/json")
                .body("{\r\n    \"email\": \"user01@user.com\",\r\n    \"password\": \"hello\"\r\n}")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        String responseBody = response.getBody();
        int i=10;
        while(responseBody.charAt(i)!='\"') {
            i++;
        }
        userToken = responseBody.substring(10, i);
        System.out.println("user token: "+ userToken);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    ////////////////////////////////8001////////////////////////////////////////////////

    @Test
    void getAllUserConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/user")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getAllAdminConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }

    //////////////////////////7501////////////////////////////////
    @Test
    void getCourseConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/coach/course")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////
    @Test
    void getScheduleSelf() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:7001/api/schedule/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getScheduleGateway() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/schedule/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getScheduleConsumer() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:7999/schedule/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getScheduleConsumerGateway() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/schedule/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getProgramSelf() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:7001/api/program/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getProgramGateway() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/program/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getProgramConsumer() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:7999/program/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void getProgramConsumerGateway() throws Exception {
        tokenUserLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/program/1")
                .header("fellaverse-token", userToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
   @Test
   void finaAllAdminSelf() throws Exception {
       tokenLoginSuccessSelf();
       HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/admin")
               .header("fellaverse-token", adminToken)
               .header("content-type", "application/json")
               .asString();
       System.out.println(response.getStatus());
       System.out.println(response.getBody());
       Assertions.assertEquals(200, response.getStatus());
       Assertions.assertNotNull(response.getBody());
   }
    @Test
    void finaAllAdminGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllAdminConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllAdminConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/admin")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllExerciseSelf() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/exercise")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllExerciseGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/exercise")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllExerciseConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/exercise")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllExerciseConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/exercise")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllRoleSelf() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/role")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllRoleGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/role")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllRoleConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/role")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllRoleConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/role")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllOrderSelf() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/order")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllOrderGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/api/management/order")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllOrderConsumer() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8002/management/order")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAllOrderConsumerGateway() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:9001/management/order")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
    @Test
    void finaAlllimitedProductSelf() throws Exception {
        tokenLoginSuccessSelf();
        HttpResponse<String> response = Unirest.get("http://localhost:8001/api/management/limitedProduct")
                .header("fellaverse-token", adminToken)
                .header("content-type", "application/json")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getBody());
    }
}
