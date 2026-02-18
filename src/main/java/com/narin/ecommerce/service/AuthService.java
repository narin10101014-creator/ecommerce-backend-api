package com.narin.ecommerce.service;

import com.narin.ecommerce.entity.User;

public interface AuthService {
    void register(User user);
    String login(String username, String password);
}
