package io.radar.radar_user_auth.domain.dto.response;

import io.radar.radar_user_auth.domain.enums.Role;
import java.time.LocalDateTime;
import java.util.Set;

public record UserProfileResponse(
        String username,
        String email,
        String firstName,
        String lastName,
        Set<Role> roles,
        boolean emailVerified,
        LocalDateTime lastLoginAt,
        LocalDateTime createdAt) {
}