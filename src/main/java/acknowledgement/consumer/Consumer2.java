package acknowledgement.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer2 {
    private static final String CLASSIC_QUEUE = "classic_mirrored_queue";
    private static final String QUORUM_QUEUE = "quorum_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Consume from Classic Mirrored Queue
            channel.basicConsume(CLASSIC_QUEUE, false, (consumerTag, message) -> {
                System.out.println("Received from Classic Mirrored Queue: " + new String(message.getBody()));
                channel.basicNack(message.getEnvelope().getDeliveryTag(), false, true); // Requeue the message
            }, consumerTag -> {});

            // Consume from Quorum Queue
            channel.basicConsume(QUORUM_QUEUE, true, (consumerTag, message) -> {
                System.out.println("Received from Quorum Queue: " + new String(message.getBody()));
            }, consumerTag -> {});

            // Keep the application running
            System.out.println("Waiting for messages...");
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}