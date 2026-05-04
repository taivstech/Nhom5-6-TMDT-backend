package lop5.nhom6.repositories.auth;

import lop5.nhom6.models.auth.UserRole;
import lop5.nhom6.models.auth.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    Set<UserRole> findByUserId(String id);
}
