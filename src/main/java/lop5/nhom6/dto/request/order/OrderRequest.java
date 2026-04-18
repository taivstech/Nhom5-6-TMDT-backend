package lop5.nhom6.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "SHIPPING_ADDRESS_REQUIRED")
    private String shippingAddress;

    @NotBlank(message = "PHONE_NUMBER_REQUIRED")
    private String phoneNumber;
}
