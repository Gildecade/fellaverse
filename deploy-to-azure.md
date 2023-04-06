## Deployment

1. Install azure cli
2. Install mvn from [here](https://stackoverflow.com/questions/10649707/maven-mvn-command-not-found)
3. Build and package maven projects locally in the `backend` directory
    - `mvn clean package -DskipTests -amd spring-boot:repackage` 
4. Try to execute the package locally
    - `java -jar <path-to-jar-file-in-target-folder`
    - `java -jar eureka-8761/target/eureka-8761-0.0.1-SNAPSHOT.jar`

5. Run an az command  (only run this step if the above all steps succeed)
    - `az spring app deploy -n demo -g <resourceGroup> -s <Azure Spring Apps instance> --jar-path target\hellospring-0.0.1-SNAPSHOT.jar`
    - `az spring app deploy -n provider-gateway -g fellaverse -s fellaverse --jar-path 'provider-gateway-9001/target/provider-gateway-9001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-token -g fellaverse -s fellaverse --jar-path 'provider-token-6001/target/provider-token-6001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-token -g fellaverse -s fellaverse --jar-path 'consumer-token-6002/target/consumer-token-6002-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-shop -g fellaverse -s fellaverse --jar-path 'provider-shop-7501/target/provider-shop-7501-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-shop -g fellaverse -s fellaverse --jar-path 'consumer-shop-7701/target/consumer-shop-7701-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-management -g fellaverse -s fellaverse --jar-path 'provider-management-8001/target/provider-management-8001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-management -g fellaverse -s fellaverse --jar-path 'consumer-management-8002/target/consumer-management-8002-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-usercenter -g fellaverse -s fellaverse --jar-path 'consumer-usercenter-5002/target/consumer-usercenter-5002-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-usercenter -g fellaverse -s fellaverse --jar-path 'provider-usercenter-5001/target/provider-usercenter-5001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-workout -g fellaverse -s fellaverse --jar-path 'consumer-workout-7999/target/consumer-workout-7999-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-workout -g fellaverse -s fellaverse --jar-path 'provider-workout-7001/target/provider-workout-7001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-checkin -g fellaverse -s fellaverse --jar-path 'consumer-checkin-7012/target/consumer-checkin-7012-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-checkin -g fellaverse -s fellaverse --jar-path 'provider-checkin-7002/target/provider-checkin-7002-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n provider-flashsale -g fellaverse -s fellaverse --jar-path 'provider-flashsale-7502/target/provider-flashsale-7502-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n consumer-flashsale -g fellaverse -s fellaverse --jar-path 'consumer-flashsale-7702/target/provider-flashsale-7702-0.0.1-SNAPSHOT.jar' --disable-validation`

6. Log from azure
    - `az spring app logs --name eureka-8761 --service "fellaverse" --resource-group "fellaverse" -f`