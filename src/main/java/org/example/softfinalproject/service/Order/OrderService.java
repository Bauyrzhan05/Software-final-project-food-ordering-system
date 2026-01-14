package org.example.softfinalproject.service.Order;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.config.kafka.OrderProducer;
import org.example.softfinalproject.dto.Order.OrderItemDto;
import org.example.softfinalproject.dto.Order.CreateOrderDto;
import org.example.softfinalproject.dto.Order.OrderResponseDto;
import org.example.softfinalproject.entity.Extra;
import org.example.softfinalproject.entity.Food;
import org.example.softfinalproject.entity.Order.Order;
import org.example.softfinalproject.entity.Order.OrderItem;
import org.example.softfinalproject.entity.Order.OrderStatus;
import org.example.softfinalproject.entity.User;
import org.example.softfinalproject.mapper.Order.OrderMapper;
import org.example.softfinalproject.repository.ExtraRepository;
import org.example.softfinalproject.repository.FoodRepository;
import org.example.softfinalproject.repository.Order.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final ExtraRepository extraRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;


    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return (User) auth.getPrincipal();
    }

    @Transactional
    public OrderResponseDto createOrder(CreateOrderDto dto) {
        User user = getCurrentUser();

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());

        int total = 0;
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto itemDto : dto.getItems()) {

            Food food = foodRepository.findById(itemDto.getFoodId()).orElseThrow();

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setFood(food);
            item.setQuantity(itemDto.getQuantity());

            int price = food.getPrice() * itemDto.getQuantity();

            if (itemDto.getExtraIds() != null) {
                List<Extra> extras = extraRepository.findAllById(itemDto.getExtraIds());
                item.setExtras(extras);

                for (Extra extra : extras) {
                    price += extra.getPrice();
                }
            }

            total += price;
            items.add(item);
        }

        order.setTotalPrice(total);
        order.setItems(items);

        Order savedOrder = orderRepository.save(order);

        try {
            orderProducer.sendOrderCreated(savedOrder);
        } catch (Exception e) {
            System.err.println("Error send to kafka: " + e.getMessage());
        }

        return orderMapper.toDto(savedOrder);
    }

    public boolean deleteOrder(Long id){
        orderRepository.deleteById(id);
        return true;
    }

}
