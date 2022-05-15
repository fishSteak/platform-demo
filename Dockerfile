FROM openjdk:11
MAINTAINER fishsteak <cutevillainrole@gmail.com>
#LABEL name="medication-webapp" version="1.3" author="fishsteak"
#ENV LOGPATH=/var/log/spring-boot-log4j2
ENV TZ=Asia/Shanghai
#WORKDIR /app
COPY /target/*.jar /app.jar
EXPOSE 8080
#VOLUME /var/log
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]