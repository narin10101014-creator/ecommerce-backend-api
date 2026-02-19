package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.repository.UserRepository;
import com.narin.ecommerce.service.AuthService;
import com.narin.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public void register(User user) {

    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return JwtUtil.generateToken(username);
    }
}
