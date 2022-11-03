# rss_feeds
Little project of backend for rss feeds handle

# Install Cassandra in docker

Use these commands :

Get last cassandra image : ```docker pull cassandra:latest``` <br>
Create network : ```docker network create cassandra``` <br>
Create docker (and running it) : ```docker run -p 9042:9042 -d --name cassandra --hostname cassandra --network cassandra cassandra``` <br>

# Access cassandra with another docker

Create temporary docker (and running it) : ```docker run --rm -it --network cassandra nuvo/docker-cqlsh cqlsh cassandra 9042 --cqlversion='3.4.5'``` <br>

# Commands to clean docker and network

```docker kill cassandra``` <br>
```docker network rm cassandra```
