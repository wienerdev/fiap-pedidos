FROM maven:3.8.3-openjdk-17 as build

ARG TOKEN_SONAR
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY src ${HOME}/src
COPY pom.xml ${HOME}/pom.xml
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="alissoncastroskt@gmail.com"
ARG APP_VERSION
ENV JAR_FILE=${APP_VERSION}.jar
ENV APP_HOME="/opt/app"
WORKDIR ${APP_HOME}
COPY --from=build /usr/app/target/${JAR_FILE} ${JAR_FILE}
EXPOSE 8080
ENTRYPOINT java -jar ${JAR_FILE} 