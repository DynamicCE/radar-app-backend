package io.radar.radar_user_auth.domain.dto.response;

public record PasswordResetResponse(
        boolean success,
        String message) {
}