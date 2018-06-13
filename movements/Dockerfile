FROM openjdk:8-jre-alpine
ENV PROFILE kube
VOLUME /tmp
COPY target/movements.jar /app.jar 
EXPOSE 8080
ENTRYPOINT java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Xmx512m -Xms512m -Dspring.profiles.active=kube -Djava.security.egd=file:/dev/./urandom -jar /app.jar 