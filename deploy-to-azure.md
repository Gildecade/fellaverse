## Deployment

1. Install azure cli
2. Install mvn from [here](https://stackoverflow.com/questions/10649707/maven-mvn-command-not-found)
3. Build and package maven projects locally in the `backend` directory
    - `mvn clean package -DskipTests -amd spring-boot:repackage -pl '!openfeign-config, !redis-config, !consumer-utils'` 
4. Run an az command 
    - `az spring app deploy -n demo -g <resourceGroup> -s <Azure Spring Apps instance> --jar-path target\hellospring-0.0.1-SNAPSHOT.jar`
    - `az spring app deploy -n eurekaserver-8761 -g fellaverse -s fellaverse --jar-path 'eureka-8761/target/eureka-8761-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n gateway-9001 -g fellaverse -s fellaverse --jar-path 'provider-gateway-9001/target/provider-gateway-9001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n tokenserver-6001 -g fellaverse -s fellaverse --jar-path 'provider-token-6001/target/provider-token-6001-0.0.1-SNAPSHOT.jar' --disable-validation`
    - `az spring app deploy -n tokenconsumer-6002 -g fellaverse -s fellaverse --jar-path 'consumer-token-6001/target/consumer-token-6001-0.0.1-SNAPSHOT.jar' --disable-validation`
