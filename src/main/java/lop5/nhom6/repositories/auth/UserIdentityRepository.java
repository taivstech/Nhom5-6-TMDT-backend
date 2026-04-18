package lop5.nhom6.repositories.auth;

import lop5.nhom6.models.auth.UserIdentity;
import lop5.nhom6.enums.auth.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserIdentityRepository extends JpaRepository<UserIdentity,String> {
    Optional<UserIdentity> findByProviderAndProviderUserId(AuthProviderType providerType, String providerUserId);
}
