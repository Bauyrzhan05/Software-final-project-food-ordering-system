package org.example.softfinalproject.service;

import org.example.softfinalproject.entity.Permission;
import org.example.softfinalproject.entity.User;
import org.example.softfinalproject.repository.PermissionRepository;
import org.example.softfinalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userCheck = userRepository.findByEmail(username);
        if(Objects.nonNull(userCheck)){
            return userCheck;
        }

        throw new UsernameNotFoundException("User not found");
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            assert authentication != null;
            return (User) authentication.getPrincipal();
        }

        return null;
    }


    public void register(User user) {
        User check = userRepository.findByEmail(user.getEmail());
        if (check == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Permission> permissions = List.of(permissionRepository.findByName("ROLE_USER"));
            user.setRoles(permissions);
            userRepository.save(user);
        }
    }

    public boolean deleteUser(Long id){
        userRepository.deleteById(id);
        return true;
    }

    public boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword){
        User currentUser = getCurrentUser();

        if (currentUser != null){
            if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
                if (newPassword.equals(repeatNewPassword)) {
                    currentUser.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(currentUser);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean editProfile(String username, String email){
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return false;
        }

        if (email == null || username == null) {
            return false;
        }

        User emailOwner = userRepository.findByEmail(currentUser.getEmail());
        if (!emailOwner.getId().equals(currentUser.getId())) {
            return false;
        }

        currentUser.setUsername(username);
        currentUser.setEmail(email);
        userRepository.save(currentUser);

        return true;
    }


}
