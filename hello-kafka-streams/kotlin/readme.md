# Hello Kafka Streams

a tiny **Stream Processing** example using Kafka + Spring + Kotlin + Gradle

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
cd  hello-kafka-streams/kotlin
gradle bootRun 
```

### 4. see console output

```bash
publish  MESSAGE : hello kafka streams
process  MESSAGE in stream: ##=> hello kafka streams <=##
receive  MESSAGE : ##=> hello kafka streams <=##
```

Each of the 3 components (producer, processor and consumer) is generated
the corresponding console output.

> ATTENTION: The application must be started => stopped => and restarted again at the first time.
> This ensures that the topic "input-topic" is created before StreamBuilder subscribes to it.
> Topics can be created before by the administrative process or automatically by producer.
> To keep the examples simple, this has been omitted here.

<br/><br/>
