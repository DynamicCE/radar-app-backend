package io.radar.radar_user_auth.domain.service;

import io.radar.radar_user_auth.domain.dto.request.RegisterRequest;
import io.radar.radar_user_auth.domain.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);

    UserResponse getUserById(UUID id);

    UserResponse getUserByUsername(String username);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(UUID id, UserResponse user);
}