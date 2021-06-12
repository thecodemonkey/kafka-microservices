# Hello Kafka 
setting up a local kafka playground.

![alt text](../docs/kafka-basic.png)

An executable Kafka environment consists in a minimal variant of at least 2 components:

- Kafka Broker (a single Node of a Kafka cluster :9092)
- Zookeeper (shared Configuration/ACL/health Service)

<br/><br/>

## Kafka as Docker

The easiest way to run Kafka locally is of course the Docker alternative. Since we need a minimum of 2 services(Broker and Zookeeper), Docker-Compose is used here. 

To give further insight into the Kafka ecosystem, in addition to the core components, I have added another 2 optional services. A Kafka Web UI, which makes it very easy to administer Kafka and a REST Proxy, which makes it possible to access Kafka clusters using REST API. 

There are many different images available in the docker hub. I decided to use the following setup:

```yaml
#docker-compose.yml

version: "3.4"

services:

# kafka core components

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

# optional components

  kafka-rest:
    image: confluentinc/cp-kafka-rest:4.1.1
    hostname: kafka-rest
    ports:
      - "38082:38082"
    depends_on:
      - kafka
    environment:
      KAFKA_REST_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_REST_HOST_NAME: kafka-rest
      KAFKA_REST_LISTENERS: http://kafka-rest:38082

  kafka-web-gui:
    image: tchiotludo/akhq:0.17.0
    ports:
      - "8081:8080"
    environment:
      AKHQ_CONFIGURATION: |
        akhq:
          connections:
            docker-kafka-server:
              properties:
                bootstrap.servers: "kafka:9092"

```

## Broker

xxx

<br/><br/>

## Zookeeper

xxx

<br/><br/>

## REST Proxy (optional)

xxx

more information about REST Proxy you can find at [docs.confluent.io](https://docs.confluent.io/platform/current/kafka-rest/index.html) ans [GitHub repo](https://github.com/confluentinc/kafka-rest)

<br/><br/>

## Admin UI (optional)

xxx

more information about AKHQ-Admin UI you can find at [akhq.io](https://akhq.io/) and [GitHub repo](https://github.com/tchiotludo/akhq)


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

With docker-compose the Kafka environment can be started and stopped. 

```bash

docker-compose -f docker-compose.yml up -d   # start kafka environment
docker-compose down                          # stop kafka environment
  
```

## play with Kafka

in windows bitte keine Git BASH verwendet, nur cmd.

<br/><br/>

### send a message

```bash

docker exec -ti kafka-microservices_kafka_1 /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

   
```


<br/><br/>

### receive the message

```bash

docker exec -ti kafka-microservices_kafka_1 /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning 

    
```

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

<br/><br/>
## Kafka ecosystem

<br/><br/>

## control Kafka outside of Docker :

https://gist.github.com/DevoKun/01b6c9963d5508579f4cbd75d52640a9
