package org.example.softfinalproject.controller;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.FoodDto;
import org.example.softfinalproject.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("api/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(foodService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getFood(@PathVariable Long id) {
        return new ResponseEntity<>(foodService.getFood(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addFood(@RequestBody FoodDto foodDto){
        return new ResponseEntity<>(foodService.addFood(foodDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodDto foodDto){
        return new ResponseEntity<>(foodService.updateFood(id, foodDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteFood(@PathVariable Long id){
        return new ResponseEntity<>(foodService.deleteFood(id), HttpStatus.OK);
    }

}
