package lop5.nhom6.mappers.user;

import lop5.nhom6.dto.request.user.UserCreationRequest;
import lop5.nhom6.dto.request.user.UserUpdateRequest;
import lop5.nhom6.dto.response.user.UserResponse;
import lop5.nhom6.models.user.User;
import org.mapstruct.*;
import lop5.nhom6.mappers.auth.RoleMapper;

@Mapper(
        componentModel = "spring",
        uses = { RoleMapper.class }
)
public interface UserMapper {

    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
