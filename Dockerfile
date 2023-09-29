FROM openjdk:17
EXPOSE 8080
ADD target/springboot-rest.jar springboot-rest.jar
ENTRYPOINT ["java","-jar","/springboot-rest.jar"]