package lop5.nhom6.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lop5.nhom6.utils.PasswordConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordRequest {

    @NotBlank(message = "TOKEN_REQUIRED")
    private String token;

    @JsonProperty("new_password")
    @PasswordConstraint
    private String newPassword;

    @JsonProperty("confirm_password")
    @PasswordConstraint
    private String confirmPassword;
}
