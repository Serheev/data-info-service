# Data Info - Client Service

Receives data
from [data handler service](https://github.com/Serheev/data-handler-service)
with Apache Kafka and Debezium. Data is sent by the [data generator service](https://github.com/Serheev/data-generator-service) and placed in the Kafka queue. Instructions for generating data in the application [description](https://github.com/Serheev/data-generator-service/blob/main/README.md).

### Usage

To start an application you need to pass variables to `.env` file.

You can find Docker compose file
in [data handler service](https://github.com/Serheev/data-handler-service) `docker/docker-compose.yaml`.

Application is running on port `8083`.

All insignificant features (checkstyle, build check, dto validation) are not
presented.

Just after startup application will try to connect to Apache Kafka and begin to
listen topics from `data` topic created by Debezium.

### The following endpoints can be used for testing

* GET `/api/v1/analytics/summary/1`
* GET `/api/v1/analytics/summary/1?mt=POWER`
* GET `/api/v1/analytics/summary/1?mt=VOLTAGE`
* GET `/api/v1/analytics/summary/1?mt=TEMPERATURE`
* GET `/api/v1/analytics/summary/1?mt=POWER&mt=VOLTAGE&mt=TEMPERATURE&st=AVG`

### Kafka console
View Kafka topics:
```
$ kafka-topics --list --bootstrap-server localhost:9092
```
Run a Kafka console consumer to read all messages from the beginning of the specified topic "data":
```
$ kafka-console-consumer --topic data --bootstrap-server localhost:9092 --from-beginning
```
### Redis CLI
```
$ redis-cli
$ KEYS *
```

