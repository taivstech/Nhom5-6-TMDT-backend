package lop5.nhom6.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lop5.nhom6.utils.DobConstraint;
import lop5.nhom6.utils.PasswordConstraint;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @JsonProperty("old_password")
    private String oldPassword;

    @PasswordConstraint(message = "Invalid password")
    private String password;

    @JsonProperty("repeat_password")
    private String repeatPassword;

    @JsonProperty("full_name")
    private String fullName;

    private String email;
    private String phone;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    private LocalDate dob;

    private List<String> roles;
}
