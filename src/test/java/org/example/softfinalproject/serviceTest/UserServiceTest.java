package org.example.softfinalproject.serviceTest;

import org.example.softfinalproject.entity.Permission;
import org.example.softfinalproject.entity.User;
import org.example.softfinalproject.repository.PermissionRepository;
import org.example.softfinalproject.repository.UserRepository;
import org.example.softfinalproject.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User mockUser;

    @BeforeEach
    void setupSecurityContext() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@mail.com");
        mockUser.setUsername("test");
        mockUser.setPassword(passwordEncoder.encode("old"));

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        mockUser,
                        null,
                        List.of()
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void loadUserByUsername_success() {
        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(mockUser);

        UserDetails user =
                userService.loadUserByUsername("test@mail.com");

        assertNotNull(user);
        assertEquals("test@mail.com", ((User) user).getEmail());
    }

    @Test
    void register_success() {
        User newUser = new User();
        newUser.setEmail("new@mail.com");
        newUser.setPassword("1234");

        Permission roleUser = new Permission();
        roleUser.setName("ROLE_USER");

        when(userRepository.findByEmail("new@mail.com"))
                .thenReturn(null);
        when(permissionRepository.findByName("ROLE_USER"))
                .thenReturn(roleUser);

        userService.register(newUser);

        verify(userRepository).save(newUser);
        assertTrue(passwordEncoder.matches(
                "1234", newUser.getPassword()));
    }

    @Test
    void updatePassword_success() {
        when(userRepository.save(any(User.class)))
                .thenReturn(mockUser);

        boolean result = userService.updatePassword(
                "old", "new", "new");

        assertTrue(result);
        assertTrue(passwordEncoder.matches(
                "new", mockUser.getPassword()));
    }

    @Test
    void updatePassword_wrongOldPassword() {
        boolean result = userService.updatePassword(
                "wrong", "new", "new");

        assertFalse(result);
    }
    @Test
    void editProfile_success() {
        when(userRepository.findByEmail(mockUser.getEmail()))
                .thenReturn(mockUser);

        boolean result =
                userService.editProfile("newName", "new@mail.com");

        assertTrue(result);
        assertEquals("newName", mockUser.getUsername());
        assertEquals("new@mail.com", mockUser.getEmail());
    }

    @Test
    void editProfile_emailBelongsToAnotherUser() {
        User another = new User();
        another.setId(2L);
        another.setEmail("test@mail.com");

        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(another);

        boolean result =
                userService.editProfile("name", "test@mail.com");

        assertFalse(result);
    }

    @Test
    void deleteUser_success() {
        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository).deleteById(1L);
    }
}

