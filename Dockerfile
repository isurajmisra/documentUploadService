FROM openjdk:11.0.3-jre-slim-stretch
VOLUME /config
ADD target/AccountingService-0.0.1-SNAPSHOT.jar /AccountingService-0.0.1-SNAPSHOT.jar
EXPOSE 8092
RUN bash -c 'touch /AccountingService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/AccountingService-0.0.2-SNAPSHOT.jar"]
