# Hello Kafka Streams Event Streaming with Streams API

a tiny **Event Sourcing** example using Kafka + Spring + Kotlin + Gradle

<br/><br/>

## prerequisites

- docker/docker-compose
- gradle
- java sdk 1.8
- kotlin
- local dns mapping: 127.0.0.1 kafka

<br/><br/>

## quickstart

### 1. get the source code

```shell
    git clone https://github.com/thecodemonkey/kafka-microservices.git
```

### 2. start local Kafka Environment

```shell
docker-compose -f docker-compose.yml up -d
#stop kafka:  docker-compose down
```

local dns setup => etc/hosts:

    127.0.0.1  kafka

### 3. run sample application

```shell
cd  hello-kafka-es-streamsapi/kotlin
gradle bootRun 
```

### 4. see console output

```bash
SEND    MESSAGE : hello kafka 2021-05-24T13:32:49.086
RECEIVE MESSAGE : hello kafka 2021-05-24T13:32:49.086
```

<br/><br/>
