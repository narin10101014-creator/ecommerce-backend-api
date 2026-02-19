package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.RegisterRequest;
import com.narin.ecommerce.entity.User;

public interface AuthService {
    void register(RegisterRequest request);
    String login(String username, String password);
}
