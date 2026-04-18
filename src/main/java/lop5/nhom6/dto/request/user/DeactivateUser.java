package lop5.nhom6.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateUser {

    @JsonProperty("user_id")
    private String userId;

}
