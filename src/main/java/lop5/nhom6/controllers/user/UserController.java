package lop5.nhom6.controllers.user;

import lop5.nhom6.dto.request.user.UpdateOwnProfile;
import lop5.nhom6.dto.request.user.UserCreationRequest;
import lop5.nhom6.dto.response.user.UserResponse;
import lop5.nhom6.services.auth.AuthenticationService;
import lop5.nhom6.services.user.UserManagementService;
import lop5.nhom6.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserManagementService userManagementService;
    private final AuthenticationService authenticationService;

    @GetMapping("/me")
    public ApiResponse<UserResponse> me() {
        return ApiResponse.<UserResponse>builder()
                .result(userManagementService.getMyInfo())
                .build();
    }

    @PutMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserResponse> updateMe(
            @RequestPart("profile") @Valid UpdateOwnProfile request,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return ApiResponse.<UserResponse>builder()
                .result(userManagementService.updateMyInfo(request, file))
                .build();
    }

    @PutMapping(value = "/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserResponse> updateMeJson(@RequestBody @Valid UpdateOwnProfile request) {
        return ApiResponse.<UserResponse>builder()
                .result(userManagementService.updateMyInfo(request, null))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:view_all') or hasAuthority('user:manage') or hasRole('ADMIN')")
    public ApiResponse<Page<UserResponse>> getUsers(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.<Page<UserResponse>>builder()
                .result(userManagementService.getUsers(pageable))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:view_all') or hasAuthority('user:manage') or hasRole('ADMIN')")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .result(userManagementService.getUserById(id))
                .build();
    }

    @PostMapping("/registration")
    public ApiResponse<UserResponse> register(@RequestBody @Valid UserCreationRequest request){
        return ApiResponse.<UserResponse>builder().code(200).result(authenticationService.createUser(request)).build();
    }
}
