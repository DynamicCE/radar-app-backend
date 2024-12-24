package io.radar.radar_user_auth.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordResetRequest(
        @NotBlank(message = "Email boş olamaz") @Email(message = "Geçerli bir email adresi giriniz") String email,

        @NotBlank(message = "Token boş olamaz") String token,

        @NotBlank(message = "Yeni şifre boş olamaz") @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır") String newPassword) {
}