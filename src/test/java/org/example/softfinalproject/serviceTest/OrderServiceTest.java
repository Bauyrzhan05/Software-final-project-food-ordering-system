package org.example.softfinalproject.serviceTest;

import org.example.softfinalproject.dto.Order.CreateOrderDto;
import org.example.softfinalproject.dto.Order.OrderItemDto;
import org.example.softfinalproject.dto.Order.OrderResponseDto;
import org.example.softfinalproject.entity.Extra;
import org.example.softfinalproject.entity.Food;
import org.example.softfinalproject.entity.Order.Order;
import org.example.softfinalproject.entity.Order.OrderStatus;
import org.example.softfinalproject.entity.User;
import org.example.softfinalproject.mapper.Order.OrderMapper;
import org.example.softfinalproject.repository.ExtraRepository;
import org.example.softfinalproject.repository.FoodRepository;
import org.example.softfinalproject.repository.Order.OrderRepository;
import org.example.softfinalproject.service.Order.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private ExtraRepository extraRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private User mockUser;

    @BeforeEach
    void setupSecurityContext() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testUser");

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(mockUser);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);

        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createOrder_success() {
        Food food = new Food();
        food.setId(1L);
        food.setPrice(1000);

        Extra extra = new Extra();
        extra.setId(1L);
        extra.setPrice(200);

        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setFoodId(1L);
        itemDto.setQuantity(2);
        itemDto.setExtraIds(List.of(1L));

        CreateOrderDto dto = new CreateOrderDto();
        dto.setItems(List.of(itemDto));

        when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
        when(extraRepository.findAllById(List.of(1L)))
                .thenReturn(List.of(extra));

        Order savedOrder = new Order();
        savedOrder.setTotalPrice(2200);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setTotalPrice(2200);

        when(orderMapper.toDto(savedOrder)).thenReturn(responseDto);

        OrderResponseDto result = orderService.createOrder(dto);

        assertNotNull(result);
        assertEquals(2200, result.getTotalPrice());

        verify(orderRepository).save(any(Order.class));
        verify(foodRepository).findById(1L);
        verify(extraRepository).findAllById(List.of(1L));
    }

    @Test
    void updateOrderStatus_success() {
        // GIVEN
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.NEW);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        OrderResponseDto dto = new OrderResponseDto();
        dto.setStatus(OrderStatus.DELIVERED);

        when(orderMapper.toDto(order)).thenReturn(dto);

        OrderResponseDto result =
                orderService.updateOrderStatus(1L, OrderStatus.DELIVERED);

        assertEquals(OrderStatus.DELIVERED, result.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void deleteOrder_success() {
        boolean result = orderService.deleteOrder(1L);

        assertTrue(result);
        verify(orderRepository).deleteById(1L);
    }
}


