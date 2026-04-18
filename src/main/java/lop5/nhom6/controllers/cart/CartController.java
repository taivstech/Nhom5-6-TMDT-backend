package lop5.nhom6.controllers.cart;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lop5.nhom6.dto.ApiResponse;
import lop5.nhom6.dto.request.cart.CartItemRequest;
import lop5.nhom6.dto.response.cart.CartResponse;
import lop5.nhom6.services.cart.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('USER')")
public class CartController {

    CartService cartService;

    @GetMapping
    public ApiResponse<CartResponse> getMyCart() {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.getMyCart())
                .build();
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addToCart(@RequestBody @Valid CartItemRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.addToCart(request))
                .build();
    }

    @PutMapping("/items/{productId}")
    public ApiResponse<CartResponse> updateItemQuantity(
            @PathVariable String productId,
            @RequestParam Integer quantity) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.updateItemQuantity(productId, quantity))
                .build();
    }

    @DeleteMapping("/items/{productId}")
    public ApiResponse<CartResponse> removeFromCart(@PathVariable String productId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.removeFromCart(productId))
                .build();
    }

    @DeleteMapping
    public ApiResponse<Void> clearCart() {
        cartService.clearCart();
        return ApiResponse.<Void>builder().build();
    }
}
