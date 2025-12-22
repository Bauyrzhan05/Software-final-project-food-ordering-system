package org.example.softfinalproject.repository.Order;

import org.example.softfinalproject.entity.Order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
