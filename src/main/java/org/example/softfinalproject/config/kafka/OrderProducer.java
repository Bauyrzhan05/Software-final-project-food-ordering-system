package org.example.softfinalproject.config.kafka;

import lombok.RequiredArgsConstructor;
import org.example.softfinalproject.entity.Order.Order;
import org.example.softfinalproject.entity.Order.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderCreated(Order order) {
        OrderEvent event = new OrderEvent(
                order.getId(),
                order.getUser().getId(),
                order.getTotalPrice(),
                "NEW"
        );
        kafkaTemplate.send("order-created", order.getId().toString(), event);
    }
}
