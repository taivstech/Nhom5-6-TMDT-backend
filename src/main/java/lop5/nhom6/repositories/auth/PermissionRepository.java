package lop5.nhom6.repositories.auth;

import lop5.nhom6.models.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,String> {
    Optional<Permission> findByName(String name);
}
