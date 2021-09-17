# Pangaea HTTP Notification System

This project contain two module which represent the Publisher Server and the Subscriber Server.

The Publisher Server publishes an http notification to a provide topic which would be consume by subscribers that subscribe to that topic.

Subscriber Server subscribes to a specified topic and listens to any message published  to the topic.


# Getting Started 

This project has a couple of dependencies before it can be executed correctly.

### Dependencies Required

1. RabitMQServer(Lastest)
2. Java SDK(At least JDK 11)
3. Maven(Latest)


* Download and install Java SDK: https://www.oracle.com/java/technologies/downloads/

* Download and install RabitMQServer: https://www.rabbitmq.com/

* Download and install Maven: https://maven.apache.org/download.cgi

* Clone the project from the repository: git clone https://github.com/perowiski/Pangaea.git

* Startup the RabitMQServer: rabbitmq-server



### Step to starting up Subscriber Server

Run the following commands from the Command Prompt or Terminal

1. $ cd into the SubscriberServer
2. $ mvn clean install
3. $ java -jar target/SubscriberServer-0.0.1-SNAPSHOT.jar



### Step to starting up Publisher Server

Run the following commands from the Command Prompt or Terminal
1. $ cd into the PublisherServer
2. $ run mvn clean install
3. $ java -jar target/PublisherServer-0.0.1-SNAPSHOT.jar


### SwaggerUI:

The SubscriberServer & PublisherServer module  contains SwaggerUI that bootstraps all available endpoints to test the functionalities of this project. 

Publisher Server SwaggerUI: http://localhost:8000/swagger-ui.html

Subscriber Server SwaggerUI:  http://localhost:9000/swagger-ui.html
