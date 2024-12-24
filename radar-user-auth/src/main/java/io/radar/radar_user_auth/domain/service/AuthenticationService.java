package io.radar.radar_user_auth.domain.service;

import io.radar.radar_user_auth.domain.dto.request.LoginRequest;
import io.radar.radar_user_auth.domain.dto.response.AuthenticationResponse;
import io.radar.radar_user_auth.domain.entity.User;
import io.radar.radar_user_auth.domain.mapper.UserMapper;
import io.radar.radar_user_auth.domain.repository.UserRepository;
import io.radar.radar_user_auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse initialLogin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AuthenticationException("User not found") {
                });

        String token = jwtService.generateLongLivedToken(user);

        user.setLastLoginAt(java.time.LocalDateTime.now());
        userRepository.save(user);

        return new AuthenticationResponse(
                token,
                userMapper.toResponse(user));
    }

    public void logout(String token) {
        jwtService.invalidateToken(token);
    }
}