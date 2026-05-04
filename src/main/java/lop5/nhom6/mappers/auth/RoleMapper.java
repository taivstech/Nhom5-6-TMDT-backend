package lop5.nhom6.mappers.auth;

import lop5.nhom6.dto.request.auth.RoleRequest;
import lop5.nhom6.dto.response.auth.RoleResponse;
import lop5.nhom6.models.auth.Role;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { PermissionMapper.class}
)
public interface RoleMapper {

    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
