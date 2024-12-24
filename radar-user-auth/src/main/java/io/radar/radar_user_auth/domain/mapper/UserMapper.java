package io.radar.radar_user_auth.domain.mapper;

import io.radar.radar_user_auth.domain.dto.response.UserResponse;
import io.radar.radar_user_auth.domain.dto.response.UserListResponse;
import io.radar.radar_user_auth.domain.dto.request.RegisterRequest;
import io.radar.radar_user_auth.domain.entity.User;
import io.radar.radar_user_auth.domain.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse toResponse(User user) {
        return Optional.ofNullable(user)
                .map(u -> new UserResponse(
                        u.getUsername(),
                        u.getEmail(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getRoles(),
                        u.isEmailVerified(),
                        u.getLastLoginAt(),
                        u.getCreatedAt()))
                .orElse(null);
    }

    public UserListResponse toListResponse(User user) {
        return Optional.ofNullable(user)
                .map(u -> new UserListResponse(
                        u.getUsername(),
                        u.getEmail(),
                        u.getRoles(),
                        u.isActive()))
                .orElse(null);
    }

    public User toEntity(RegisterRequest dto) {
        return Optional.ofNullable(dto)
                .map(d -> User.builder()
                        .username(d.username())
                        .email(d.email())
                        .password(passwordEncoder.encode(d.password()))
                        .firstName(d.firstName())
                        .lastName(d.lastName())
                        .roles(Set.of(Role.USER))
                        .isActive(true)
                        .emailVerified(false)
                        .build())
                .orElse(null);
    }
}