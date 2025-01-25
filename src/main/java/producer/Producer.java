package producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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

            String message = "Hello, RabbitMQ!";

            // Publish to Classic Mirrored Queue
            channel.basicPublish("", CLASSIC_QUEUE, null, message.getBytes());
            System.out.println("Sent to Classic Mirrored Queue: " + message);

            // Publish to Quorum Queue
            channel.basicPublish("", QUORUM_QUEUE, null, message.getBytes());
            System.out.println("Sent to Quorum Queue: " + message);
        }
    }
}
