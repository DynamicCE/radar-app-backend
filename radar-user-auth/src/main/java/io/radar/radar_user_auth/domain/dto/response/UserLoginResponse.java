package io.radar.radar_user_auth.domain.dto.response;

import io.radar.radar_user_auth.domain.enums.Role;
import java.util.Set;

public record UserLoginResponse(
        String token,
        String username,
        Set<Role> roles) {
}