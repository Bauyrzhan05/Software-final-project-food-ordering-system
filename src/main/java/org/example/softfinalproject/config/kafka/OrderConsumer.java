package org.example.softfinalproject.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.softfinalproject.entity.Order.Order;
import org.example.softfinalproject.entity.Order.OrderEvent;
import org.example.softfinalproject.entity.Order.OrderStatus;
import org.example.softfinalproject.repository.Order.OrderRepository;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;

    @Transactional
    @KafkaListener(topics = "payment-success", groupId = "order-service-group")
    public void handlePaymentSuccess(OrderEvent orderEvent) {
        Order order = orderRepository.findById(orderEvent.getOrderId()).orElseThrow();
        order.setStatus(OrderStatus.IN_PROGRESS);

        orderRepository.save(order);
        log.info("is now IN_PROGRESS order: {}", orderEvent.getOrderId());
    }


}
