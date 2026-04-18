package lop5.nhom6.mappers.auth;

import lop5.nhom6.dto.response.auth.PermissionResponse;
import lop5.nhom6.models.auth.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponse toPermissionResponse(Permission permission);
}
