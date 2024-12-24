package io.radar.radar_user_auth.domain.dto.response;

public record AuthenticationResponse(
        String token,
        UserResponse user) {
}