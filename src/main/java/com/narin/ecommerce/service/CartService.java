package com.narin.ecommerce.service;

import com.narin.ecommerce.dto.response.CartResponse;

public interface CartService {
    void addProduct(Long productId, int quantity);

    CartResponse getCart();

    void updateQuantity(Long itemId, int quantity);
}
