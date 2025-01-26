package acknowledgement.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer {
    private static final String CLASSIC_QUEUE = "classic_mirrored_queue";
    private static final String QUORUM_QUEUE = "quorum_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            sendMessage(channel, "1 - Hello, RabbitMQ!");
            sendMessage(channel, "2 - Hello, RabbitMQ!");
            sendMessage(channel, "3 - Hello, RabbitMQ!");
            sendMessage(channel, "4 - Hello, RabbitMQ!");
            sendMessage(channel, "5 - Hello, RabbitMQ!");
            sendMessage(channel, "6 - Hello, RabbitMQ!");
            sendMessage(channel, "7 - Hello, RabbitMQ!");
            sendMessage(channel, "8 - Hello, RabbitMQ!");
            sendMessage(channel, "9 - Hello, RabbitMQ!");
            sendMessage(channel, "10 - Hello, RabbitMQ!");
            sendMessage(channel, "11 - Hello, RabbitMQ!");
            sendMessage(channel, "12 - Hello, RabbitMQ!");
            sendMessage(channel, "13 - Hello, RabbitMQ!");
            sendMessage(channel, "14 - Hello, RabbitMQ!");
            sendMessage(channel, "15 - Hello, RabbitMQ!");

            // Publish to Quorum Queue
            String message = "Q1 - Hello, RabbitMQ!";
            channel.basicPublish("", QUORUM_QUEUE, null, message.getBytes());
            System.out.println("Sent to Quorum Queue: " + message);
        }
    }

    private static void sendMessage(Channel channel, String message) throws IOException {
        // Publish to Classic Mirrored Queue
        channel.basicPublish("", CLASSIC_QUEUE, null, message.getBytes());
        System.out.println("Sent to Classic Mirrored Queue: " + message);
    }
}
