#!/bin/sh

echo "********************************************************"
echo "Waiting for the $DATABASESERVER_NAME server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z $DATABASESERVER_NAME $DATABASESERVER_PORT`; do sleep 3; done
echo "******** Database Server has started "

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Waiting for the kafka server to start on port $KAFKASERVER_PORT"
echo "********************************************************"
while ! `nc -z kafkaserver $KAFKASERVER_PORT`; do sleep 10; done
echo "******* Kafka Server has started"

echo "********************************************************"
echo "Waiting for the ZIPKIN server to start  on port $ZIPKIN_PORT"
echo "********************************************************"
while ! `nc -z zipkin $ZIPKIN_PORT`; do sleep 10; done
echo "******* ZIPKIN has started"

echo "********************************************************"
echo "Starting Daemon Server with Configuration Service"
#echo "Using Kafka Server: $KAFKASERVER_URI"
echo "Using ZK    Server: $ZKSERVER_URI"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.cloud.stream.kafka.binder.zkNodes=$KAFKASERVER_URI          \
     -Dspring.cloud.stream.kafka.binder.brokers=$ZKSERVER_URI             \
     -Dspring.zipkin.baseUrl=$ZIPKIN_URI                                  \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/onlinedogshowdaemon/@project.build.finalName@.jar
