package lop5.nhom6.dto.request.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lop5.nhom6.enums.product.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "PRODUCT_NAME_REQUIRED")
    private String name;

    @NotBlank(message = "PRODUCT_SLUG_REQUIRED")
    private String slug;

    private String description;

    @NotNull(message = "PRODUCT_PRICE_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "PRICE_MUST_BE_POSITIVE")
    private BigDecimal price;

    private BigDecimal discountPrice;

    @NotNull(message = "STOCK_REQUIRED")
    @Min(value = 0, message = "STOCK_CANNOT_BE_NEGATIVE")
    private Integer stockQuantity;

    private String categoryId;

    @NotNull(message = "STATUS_REQUIRED")
    private ProductStatus status;

    private String brand;
    private Double weight;

    private List<ProductImageRequest> images;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductImageRequest {
        @NotBlank(message = "IMAGE_URL_REQUIRED")
        private String url;
        private boolean isPrimary;
    }
}
