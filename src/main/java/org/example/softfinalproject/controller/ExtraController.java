package org.example.softfinalproject.controller;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.ExtraDto;
import org.example.softfinalproject.service.ExtraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("api/extras")
public class ExtraController {

    private final ExtraService extraService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(extraService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getExtra(@PathVariable Long id){
        return new ResponseEntity<>(extraService.getExtra(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addExtra(@RequestBody ExtraDto extraDto){
        return new ResponseEntity<>(extraService.addExtra(extraDto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateExtra(@PathVariable Long id, @RequestBody ExtraDto extraDto){
        return new ResponseEntity<>(extraService.updateExtra(id, extraDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteExtra(@PathVariable Long id){
        return new ResponseEntity<>(extraService.deleteExtra(id), HttpStatus.OK);
    }

}




