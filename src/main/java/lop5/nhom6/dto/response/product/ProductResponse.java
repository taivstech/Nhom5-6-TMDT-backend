package lop5.nhom6.dto.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lop5.nhom6.dto.response.product.CategoryResponse;
import lop5.nhom6.enums.product.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Integer stockQuantity;
    private CategoryResponse category;
    private ProductStatus status;
    private String brand;
    private Double weight;
    private List<ProductImageResponse> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductImageResponse {
        private String id;
        private String url;
        private boolean isPrimary;
    }
}
