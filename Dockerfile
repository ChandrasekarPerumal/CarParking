FROM openjdk:17
EXPOSE 8082
COPY target/CarParking.jar /CarParking.jar
ENTRYPOINT ["java","-jar","/CarParking.jar"]