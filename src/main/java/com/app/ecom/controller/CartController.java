package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody CartItemRequest request) {
        if (!cartService.addToCart(userId, request))
             return ResponseEntity.badRequest().body("Product Out of Stock or User not Found or Product Not found");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User-ID") String userdId,
            @PathVariable Long productId){
        boolean deleted = cartService.deleteItemFromCart(userdId, productId);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();

    }

}
