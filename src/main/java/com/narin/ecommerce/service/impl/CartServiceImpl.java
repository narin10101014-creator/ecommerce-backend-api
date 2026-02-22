package com.narin.ecommerce.service.impl;

import com.narin.ecommerce.dto.response.CartResponse;
import com.narin.ecommerce.entity.Cart;
import com.narin.ecommerce.entity.CartItem;
import com.narin.ecommerce.entity.Product;
import com.narin.ecommerce.entity.User;
import com.narin.ecommerce.mapper.CartMapper;
import com.narin.ecommerce.repository.CartItemRepository;
import com.narin.ecommerce.repository.CartRepository;
import com.narin.ecommerce.repository.ProductRepository;
import com.narin.ecommerce.repository.UserRepository;
import com.narin.ecommerce.service.CartService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void addProduct(Long productId, int quantity) {

        // 1.Validate quantity (prevent zero or negative values from client)
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        // 2.Retrieve currently authenticated user from SecurityContext
        User user = getCurrentUser();

        // 3.Retrieve user's cart, or create a new one if it doesn't exist
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> createNewCart(user));

        // 4.Fetch product from database
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 5.Prevent adding soft-deleted products
        if (product.getDeleted()) {
            throw new RuntimeException("Product is not available");
        }

        // 6.Check if requested quantity exceeds available stock
        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        // 7.Check if the product already exists in the cart
        Optional<CartItem> existingItem =
                cart.getCartItems().stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();

        if (existingItem.isPresent()) {
            // 8.If product already exists → increase quantity
            int newQty = existingItem.get().getQuantity() + quantity;

            // 9.Re-check stock for updated quantity
            if (product.getStock() < newQty) {
                throw new RuntimeException("Not enough stock");
            }

            existingItem.get().setQuantity(newQty);

        } else {
            // 10.If product does not exist in cart → create new CartItem
            CartItem item = new CartItem();
            item.setCart(cart);          // establish cart relationship
            item.setProduct(product);    // assign product
            item.setQuantity(quantity);  // set quantity
            cart.getCartItems().add(item);
        }
        // 11.Persist cart (Cascade will save CartItems automatically)
        cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponse getCart() {
        // 1.Retrieve currently authenticated user from SecurityContext
        User user = getCurrentUser();

        // 2.Find the cart that belongs to this user
        // If the user does not have a cart yet, throw an exception
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 3.Calculate total price of the cart
        // For each item: product price × quantity
        // Then sum all item subtotals into one total value
        double total = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        // 4.Convert Cart entity into CartResponse DTO
        // Pass the calculated total to the mapper
        return CartMapper.toResponse(cart, total);
    }

    @Override
    @Transactional
    public void updateQuantity(Long itemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User user = getCurrentUser();
        if (!item.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        Product product = item.getProduct();

        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        item.setQuantity(quantity);
    }

    private User getCurrentUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Safety check (should not happen if security is configured correctly)
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        // Get username from JWT / SecurityContext
        String username = auth.getName();

        // Load full User entity from database
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Cart createNewCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
