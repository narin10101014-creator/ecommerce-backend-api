package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void register(User user) {

    }

    @Override
    public String login(String username, String password) {
        return null;
    }
}
