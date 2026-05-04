package lop5.nhom6.repositories.auth;

import lop5.nhom6.models.auth.Permission;
import lop5.nhom6.models.auth.Role;
import lop5.nhom6.models.auth.RolePermission;
import lop5.nhom6.models.auth.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
    Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);
}
