package io.radar.radar_user_auth.domain.dto.response;

import io.radar.radar_user_auth.domain.enums.Role;
import java.util.Set;

public record UserListResponse(
        String username,
        String email,
        Set<Role> roles,
        boolean isActive) {
}