1. run mvn clean package
2. -Dspring.profiles.active=dev
3. go to http://localhost:8080/swagger-ui/#/lottery-controller
4. enjoy
5. if you want to use www.random.org as random generator use *randomorg* profile
   ``java -jar ./target/lottery-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=randomorg``

p.s. it uses h2 as database. Do not expect to have db to be filled after 
restart of app. 