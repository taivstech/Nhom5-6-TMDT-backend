package lop5.nhom6.services.user;

import lop5.nhom6.dto.request.user.ActiveUserRequest;
import lop5.nhom6.dto.request.user.DeactivateUser;
import lop5.nhom6.dto.request.user.UpdateOwnProfile;
import lop5.nhom6.dto.request.user.UserCreationRequest;
import lop5.nhom6.dto.request.user.UserUpdateRequest;
import lop5.nhom6.dto.response.user.UserResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserManagementService {

    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();

    UserResponse updateMyInfo(UpdateOwnProfile request, MultipartFile avatarFile);

    org.springframework.data.domain.Page<UserResponse> getUsers(org.springframework.data.domain.Pageable pageable);

    UserResponse getUserById(String id);

    void activeUser(ActiveUserRequest activeUserRequest);

    void deactivateUser(DeactivateUser deactivateUser);

    UserResponse updateUser(UserUpdateRequest request);

    UserResponse getUser(String id);

    UserResponse getActivatedUser(String id);

    UserResponse getActivatedUserByUsername(String username);

    boolean existById(String id);

    String getUserIdByUsername(String username);
}
