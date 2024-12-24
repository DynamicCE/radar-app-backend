package io.radar.radar_user_auth.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {

    @NotBlank(message = "Refresh token gereklidir")
    private String refreshToken;
}