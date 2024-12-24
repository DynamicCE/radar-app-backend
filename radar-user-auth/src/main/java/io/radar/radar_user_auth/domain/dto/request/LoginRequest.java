package io.radar.radar_user_auth.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record LoginRequest(
        @NotBlank(message = "Email boş olamaz") @Email(message = "Geçerli bir email adresi giriniz") String email,

        @NotBlank(message = "Şifre boş olamaz") String password) {
}