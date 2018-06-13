FROM openjdk:8
ENV PROFILE kube
VOLUME /tmp
COPY target/positions.jar /app.jar  
ENTRYPOINT java -Dspring.profiles.active=kube -Djava.security.egd=file:/dev/./urandom -jar /app.jar 