# Hello Kafka Streams

a tiny **Stream Processing** example using Kafka + Spring + Java + Maven

<br/><br/>

## prerequisites

- docker/docker-compose
- maven/mvn
- java sdk 1.8


<br/><br/>

## quickstart

### 1. get the source code

```shell
    git clone https://github.com/thecodemonkey/kafka-microservices.git
```

### 2. start local Kafka Environment

```shell
docker-compose up -d
#stop kafka:  docker-compose down
```

### 3. run sample application

```shell
cd  hello-kafka-streams/java
mvn spring-boot:run
```

### 4. see console output

```bash
publish  MESSAGE : hello kafka streams
process  MESSAGE in stream: ##=> hello kafka streams <=##
receive  MESSAGE : ##=> hello kafka streams <=##
```

Each of the 3 components (producer, processor and consumer) is generated
the corresponding console output.

<br/><br/>
