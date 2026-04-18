package lop5.nhom6.services.user.impl;

import lop5.nhom6.dto.request.user.ActiveUserRequest;
import lop5.nhom6.dto.request.user.DeactivateUser;
import lop5.nhom6.dto.request.user.UpdateOwnProfile;
import lop5.nhom6.dto.request.user.UserCreationRequest;
import lop5.nhom6.dto.request.user.UserUpdateRequest;
import lop5.nhom6.dto.response.user.UserResponse;
import lop5.nhom6.models.auth.Role;
import lop5.nhom6.models.user.User;
import lop5.nhom6.models.auth.UserRole;
import lop5.nhom6.models.auth.UserRoleId;
import lop5.nhom6.mappers.user.UserMapper;
import lop5.nhom6.repositories.auth.RoleRepository;
import lop5.nhom6.repositories.user.UserRepository;
import lop5.nhom6.repositories.auth.UserRoleRepository;
import lop5.nhom6.services.user.UserManagementService;
import lop5.nhom6.constants.PredefinedRole;
import lop5.nhom6.exceptions.AppException;
import lop5.nhom6.exceptions.ErrorCode;
import lop5.nhom6.utils.RedisCacheHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final RedisCacheHelper cacheHelper;
    // private final FileStorageService fileStorageService; // Placeholder for future media module
    // private final ShopRepository shopRepository; // Placeholder for future shop module

    @Override
    @Transactional
    public UserResponse createUser(UserCreationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(false);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Role role = roleRepository.findByName(PredefinedRole.USER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTS));

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(user.getId(), role.getId()))
                .user(user)
                .role(role)
                .assignedAt(Instant.now())
                .build();
        userRoleRepository.save(userRole);

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getMyInfo() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateMyInfo(UpdateOwnProfile request, MultipartFile avatarFile) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            if (!request.getUsername().equals(user.getUsername()) &&
                    userRepository.existsByUsername(request.getUsername())) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
            user.setUsername(request.getUsername());
        }

        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setDob(request.getDob());

        // Avatar upload handled by media module in future
        /*
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String avatarUrl = fileStorageService.uploadAndGetUrl(avatarFile, "/avatars");
            user.setProfilePicture(avatarUrl);
        }
        */

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<UserResponse> getUsers(org.springframework.data.domain.Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUserResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    @Transactional
    public void activeUser(ActiveUserRequest activeUserRequest) {
        User user = userRepository.findById(activeUserRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deactivateUser(DeactivateUser deactivateUser) {
        User user = userRepository.findById(deactivateUser.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserUpdateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        if (request.getPassword() != null && !request.getPassword().equals(request.getRepeatPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        if (request.getPassword() != null) {
            if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            } else {
                throw new AppException(ErrorCode.WRONG_PASSWORD);
            }
        }

        if (request.getRoles() != null) {
            Set<String> newRoleNames = new HashSet<>(request.getRoles());
            user.getUserRoles().clear();
            for (String roleName : newRoleNames) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTS));
                UserRole userRole = UserRole.builder()
                        .id(new UserRoleId(user.getId(), role.getId()))
                        .user(user)
                        .role(role)
                        .assignedAt(Instant.now())
                        .build();
                user.getUserRoles().add(userRole);
            }
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getActivatedUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findByIdAndActive(id, true).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getActivatedUserByUsername(String username) {
        return userMapper.toUserResponse(
                userRepository.findByUsernameAndActive(username, true).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public boolean existById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public String getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED))
                .getId();
    }
}
