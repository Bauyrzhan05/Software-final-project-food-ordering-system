package org.example.softfinalproject.controller.Order;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.Order.CreateOrderDto;
import org.example.softfinalproject.service.Order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto createOrderDto){
        return new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }

}
