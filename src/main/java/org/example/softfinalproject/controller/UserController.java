package org.example.softfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.softfinalproject.entity.User;
import org.example.softfinalproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.status(HttpStatus.OK).body("Logged in");
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> register(@RequestBody User user){
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getUsername() + " registered");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePassword(@RequestParam String oldPassword,
                                            @RequestParam String newPassword,
                                            @RequestParam String repeatNewPassword
    ){
        boolean result = userService.updatePassword(oldPassword, newPassword, repeatNewPassword);

        if (result) {
            return ResponseEntity.ok("Password updated");
        } return ResponseEntity.badRequest().body("Password update failed");
    }

    @PutMapping("edit-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> editProfile(@RequestBody User req ){
        boolean result = userService.editProfile(req.getUsername(), req.getEmail());

        if (result) {
            return ResponseEntity.ok("Profile updated");
        }
        return ResponseEntity.badRequest().body("Profile update failed");
    }

}
