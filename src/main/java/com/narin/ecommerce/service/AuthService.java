package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.request.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(String username, String password);
}
