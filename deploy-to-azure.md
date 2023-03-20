## Deployment

1. Install azure cli
2. Install mvn from [here](https://stackoverflow.com/questions/10649707/maven-mvn-command-not-found)
3. Build and package maven projects locally in the `backend` directory
    - `mvn clean package -DskipTests -amd` 
4. Run an az command 
    - `az spring app deploy -n demo -g <resourceGroup> -s <Azure Spring Apps instance> --jar-path target\hellospring-0.0.1-SNAPSHOT.jar`
    - `az spring app deploy -n tokenserver-6001 -g fellaverse -s fellaverse --jar-path target\provider-token-6001-0.0.1-SNAPSHOT.jar`