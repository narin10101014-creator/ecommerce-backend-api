package com.narin.ecommerce.mapper;

import com.narin.ecommerce.dto.response.CartItemResponse;
import com.narin.ecommerce.dto.response.CartResponse;
import com.narin.ecommerce.entity.Cart;
import com.narin.ecommerce.entity.CartItem;

import java.util.List;

public class CartMapper {

    // Private constructor to prevent instantiation
    // This class is a utility class (only static methods)
    private CartMapper() {}

    // Convert Cart entity to CartResponse DTO
    public static CartResponse toResponse(Cart cart, double total) {

        // 1.Convert each CartItem in the cart into CartItemResponse
        List<CartItemResponse> itemResponses = cart.getCartItems()
                .stream()
                .map(CartMapper::mapToItemResponse)
                .toList();

        // 2.Convert each CartItem in the cart into CartItemResponse
        return new CartResponse(itemResponses, total);
    }

    // Convert a single CartItem entity to CartItemResponse DTO
    private static CartItemResponse mapToItemResponse(CartItem item) {
        // 1.Calculate subtotal for this item
        // 2.subtotal = product price × quantity
        double subtotal = item.getProduct().getPrice() * item.getQuantity();

        // 3.subtotal = product price × quantity
        return new CartItemResponse(
                item.getId(),                      // cart item id
                item.getProduct().getId(),         // cart item id
                item.getProduct().getName(),       // product name
                item.getProduct().getPrice(),      // product price
                item.getQuantity(),                // product price
                subtotal                           // quantity in cart
        );
    }
}
