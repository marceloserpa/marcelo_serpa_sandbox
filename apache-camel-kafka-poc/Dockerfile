FROM frolvlad/alpine-oraclejdk8:slim

WORKDIR /opt

VOLUME /tmp

RUN mkdir /opt/app

EXPOSE 8888

ADD build/libs/com.marceloserpa.camelkafka.poc-1.0-SNAPSHOT.jar   /opt/app/com.marceloserpa.camelkafka.poc-1.0-SNAPSHOT.jar

ENV JAVA_OPTS="-Xmx1g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSConcurrentMTEnabled"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app/com.marceloserpa.camelkafka.poc-1.0-SNAPSHOT.jar " ]