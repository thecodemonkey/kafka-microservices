# Hello Kafka 
setting up a local kafka playground.

![alt text](../docs/kafka-publish-subscribe-simple.png)

An executable Kafka environment consists in a minimal variant of at least 2 components:

- Kafka Broker (a single Node of a Kafka cluster :9092)
- Zookeeper (shared Configuration/ACL/health Service)

<br/><br/>

## Kafka in Docker

```yaml
#docker-compose.yml

version: "3.4"

services:
  zookeeper:
    image: zookeeper:3.7.0
    ports:
      - "2181:2181"

  kafka:
    image: wurstmeister/kafka:2.12-2.5.0
    ports:
      - "9092:9092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_ADVERTISED_HOST_NAME: kafka
      KAFKA_ADVERTISED_PORT: "9092"
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_MESSAGE_MAX_BYTES: 104858800
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

## Broker

xxx

<br/><br/>

## Zookeeper

xxx

<br/><br/>

## REST Proxy (optional)

xxx

<br/><br/>

## Admin UI (optional)

xxx

<br/><br/>

## setting up a local Kafka environment

### prerequisites

- docker/docker-compose
- local dns mapping: 127.0.0.1 kafka

<br/><br/>

### run Kafka

This project provides a docker-compose.yml to run a complete Kafka environment locally.
In addition, the docker-compose.yml contains a web UI for managing the Kafka system. The web UI is
after a successful start of the docker-compose at http://localhost:8081
There you can also create topics, write and read messages.

> The Kafka environment absolutely needs a host name (KAFKA_ADVERTISED_HOST_NAME) this is already in docker-compose.yml
> configured as **"kafka"**.
>
> So you need a DNS entry in the local etc / hosts file:
>
> 127.0.0.1  kafka

With docker-compose the Kafka environment can be started and stopped. In the individual projects, depending on the environment
the individual tasks are provided that wrap the actual docker-compose command.

```bash

docker-compose -f docker-compose.yml up -d   # start kafka environment
docker-compose down                          # stop kafka environment
  
```

control Kafka outside of Docker :
https://gist.github.com/DevoKun/01b6c9963d5508579f4cbd75d52640a9


## play with Kafka


```bash

    docker exec -it kafka-microservices_kafka_1 /opt/kafka_2.12-2.5.0/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic testwarhier

    #List Brokers
    docker exec -ti kafka /usr/bin/broker-list.sh
    
    ## List Topics
    docker exec -ti kafka /opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
    
    ## Create a Topic
    docker exec -ti kafka /opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic test2
    docker exec -it kafka-microservices_kafka_1 /opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic testwarhier12323
    
    ## List Topics
    docker exec -ti kafka /opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181


    ##consume 
    docker exec kafka kafka-console-consumer --bootstrap-server localhost:29092 --topic foo -new-consumer --from-beginning --max-messages 42
    
```

<br/><br/>

### send a message

<br/><br/>

### receive the message

<br/><br/>

-----

## About Kafka

In a distributed system, different sub-systems can communicate via publish/subscribe patterns with each other. 
The producer can write messages and the consumer can subscribe to the message channel/queue and receive the messages.
Appropriate messaging systems are used to enable such publish/subscribe mechanisms. There are of course differences in the details, but at this point there are only details.
Kafka is one of those systems. Regardless of what makes Kafka so unique and powerful, Kafka is first and foremost a messaging system like 
E.g. RabbitMQ or Redis.

However, Kafka can do much more than just publish/subscribe. Kafka's real power lies in **stream processing**.
Kafka is able to manage very large data streams with extremely high performance. These data streams are persisted in the Kafka system.
This means that the data can not only be processed in real time, but can also be used for analysis.
With libraries such as Kafka Streams API, states can be determined from data streams and also persisted.

All of this makes Kafka a Swiss Army Knife.

Theoretically, Kafka can be used as a:

- Messaging System
- Data Streaming Plattform 
- Data Lake
- Data Warehouse 
- Event Store  
- Database 


All of this makes Kafka so powerful and unique.

## Kafka ecosystem
