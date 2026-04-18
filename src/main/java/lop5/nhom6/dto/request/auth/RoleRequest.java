package lop5.nhom6.dto.request.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {
    private String id;
    private String description;
}
