# Hello Kafka API
a tiny publish/subscribe example using Kafka + Spring + Maven + Java

<br/><br/>
## prerequisites

- docker/docker-compose
- local dns mapping: 127.0.0.1 kafka
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
docker-compose -f docker-compose.yml up -d

#stop kafka:  docker-compose down
```

local dns setup => etc/hosts: 

    127.0.0.1  kafka

### 3. run sample application

```shell
cd  hello-kafka-api/java
mvn spring-boot:run
```

### 4. see console output

```bash
SEND    MESSAGE : hello kafka 2021-05-24T13:32:49.086
RECEIVE MESSAGE : hello kafka 2021-05-24T13:32:49.086
```

<br/><br/>
