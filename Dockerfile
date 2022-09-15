FROM gradle:7.1.0-jdk11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    
# actual container
FROM adoptopenjdk/openjdk11:alpine-jre
ENV ARTIFACT_NAME=Api-sum-0.0.1.jar
ENV APP_HOME=/usr/app/
    
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/Api-sum-0.0.1.jar .

#ADD build/libs/Api-sum-0.0.1.jar /app/Api-sum-0.0.1.jar

EXPOSE 8086
#EXPOSE 6379
#ENTRYPOINT exec java -jar Api-sum-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app/Api-sum-0.0.1.jar"]
