# stage to build .jar from application
FROM openjdk:11-jdk as builder
RUN mkdir /opt/sandbox \
        && mkdir /opt/sandbox/bookstore/
WORKDIR /opt/sandbox/bookstore/
COPY . .
RUN ./gradlew build

# stage to just run application
FROM openjdk:11-jdk
RUN mkdir /opt/apps \
    && mkdir /opt/apps/bookstore/
WORKDIR /opt/apps/bookstore/
COPY --from=builder /opt/sandbox/bookstore/build/libs/book-service-0.1.0.jar .
EXPOSE 8080:8080
RUN java -jar book-service-0.1.0.jar
