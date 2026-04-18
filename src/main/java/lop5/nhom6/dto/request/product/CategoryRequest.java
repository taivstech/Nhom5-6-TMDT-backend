package lop5.nhom6.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    @NotBlank(message = "CATEGORY_NAME_REQUIRED")
    @Size(max = 100, message = "CATEGORY_NAME_TOO_LONG")
    private String name;

    @NotBlank(message = "CATEGORY_SLUG_REQUIRED")
    @Size(max = 120, message = "CATEGORY_SLUG_TOO_LONG")
    private String slug;

    private String description;
    private String parentId;
}
