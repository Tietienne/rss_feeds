# rss_feeds
Little project of backend for rss feeds handle

# Install Cassandra in docker

Use these commands :

Get last cassandra image : ```docker pull cassandra:latest``` <br>
Create network : ```docker network create cassandra``` <br>
Create docker (and running it) : ```docker run -p 9042:9042 -d --name cassandra --hostname cassandra --network cassandra cassandra``` <br>

# Access cassandra with another docker

Create temporary docker (and running it) : ```docker run --rm -it --network cassandra nuvo/docker-cqlsh cqlsh cassandra 9042 --cqlversion='3.4.5'``` <br>
And create keyspace : ```CREATE KEYSPACE IF NOT EXISTS rss WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };```

# Install Kafka

Download kafka last version : ```https://dlcdn.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz``` <br>
Extract the repository and open terminal in it. <br>

## Windows :
Start Zookeeper : ```.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties``` <br>
Start Kafka Server : ```.\bin\windows\kafka-server-start.bat .\config\server.properties``` <br>
Create Topic : ```./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions  1 --topic topic-articles``` <br>
Open a consumer : ```./bin/windows/kafka-console-consumer.bat --topic topic-articles --from-beginning --bootstrap-server localhost:9092``` <br>

## Linux :
Start Zookeeper : ```.\bin\zookeeper-server-start.sh .\config\zookeeper.properties``` <br>
Start Kafka Server : ```.\bin\kafka-server-start.sh .\config\server.properties``` <br>
Create Topic (Not verified) : ```./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions  1 --topic topic-articles``` <br>
Open a consumer : ```./bin/kafka-console-consumer.sh --topic topic-articles --from-beginning --bootstrap-server localhost:9092``` <br>

# Commands to clean docker and network

```docker kill cassandra``` <br>
```docker network rm cassandra```
