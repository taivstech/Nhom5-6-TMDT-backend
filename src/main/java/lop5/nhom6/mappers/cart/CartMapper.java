package lop5.nhom6.mappers.cart;

import lop5.nhom6.dto.response.cart.CartResponse;
import lop5.nhom6.models.cart.Cart;
import lop5.nhom6.models.cart.CartItem;
import lop5.nhom6.mappers.product.ProductMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, builder = @Builder(disableBuilder = true))
public interface CartMapper {

    @Mapping(target = "totalItems", expression = "java(cart.getItems() != null ? cart.getItems().stream().mapToInt(lop5.nhom6.models.cart.CartItem::getQuantity).sum() : 0)")
    CartResponse toCartResponse(Cart cart);

    CartResponse.CartItemResponse toCartItemResponse(CartItem cartItem);

    List<CartResponse.CartItemResponse> toCartItemResponses(List<CartItem> cartItems);
}
