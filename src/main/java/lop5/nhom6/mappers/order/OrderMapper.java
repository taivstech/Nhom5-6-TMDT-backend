package lop5.nhom6.mappers.order;

import lop5.nhom6.dto.response.order.OrderResponse;
import lop5.nhom6.models.order.Order;
import lop5.nhom6.models.order.OrderItem;
import lop5.nhom6.mappers.product.ProductMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, builder = @Builder(disableBuilder = true))
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponse toOrderResponse(Order order);

    OrderResponse.OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderResponse.OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItems);
}
