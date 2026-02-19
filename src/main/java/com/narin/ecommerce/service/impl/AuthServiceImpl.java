package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.config.SecurityConfig;
import com.narin.ecommerce.dto.RegisterRequest;
import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.enums.Role;
import com.narin.ecommerce.exception.DuplicateUserException;
import com.narin.ecommerce.exception.InvalidPasswordException;
import com.narin.ecommerce.exception.UserNotFoundException;
import com.narin.ecommerce.repository.UserRepository;
import com.narin.ecommerce.service.AuthService;
import com.narin.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateUserException("Username already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateUserException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.ROLE_USER) // default
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return JwtUtil.generateToken(username);
    }
}
