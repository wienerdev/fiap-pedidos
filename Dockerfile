FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="alissoncastroskt@gmail.com"
ARG APP_VERSION
ENV JAR_FILE=${APP_VERSION}.jar
ENV APP_HOME="/opt/app"
WORKDIR ${APP_HOME}
COPY target/${JAR_FILE} ${JAR_FILE}
EXPOSE 8080
ENTRYPOINT java -jar ${JAR_FILE} 