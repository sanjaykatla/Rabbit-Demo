# RabbitMQ Demo

## How to run
1. Navigate to proj directory
2. run docker-compose up -d
3. Create queues
   1. Create a queue with the following settings:
      1. Name: classic_mirrored_queue 
      2. Type: Classic
   2. Create another queue:
      1. Name: quorum_queue 
      2. Type: Quorum

4. mvn compile exec:java -Dexec.mainClass=producer.Producer
5. mvn compile exec:java -Dexec.mainClass=consumer.Consumer


