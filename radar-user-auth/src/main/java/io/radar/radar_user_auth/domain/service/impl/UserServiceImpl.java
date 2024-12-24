package io.radar.radar_user_auth.domain.service.impl;

import io.radar.radar_user_auth.domain.dto.request.RegisterRequest;
import io.radar.radar_user_auth.domain.dto.response.UserResponse;
import io.radar.radar_user_auth.domain.entity.User;
import io.radar.radar_user_auth.domain.mapper.UserMapper;
import io.radar.radar_user_auth.domain.repository.UserRepository;
import io.radar.radar_user_auth.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse registerUser(RegisterRequest request) {
        User user = userMapper.toEntity(request);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID id, UserResponse userResponse) {
        return userRepository.findById(id)
                .map(user -> userMapper.toResponse(userRepository.save(user)))
                .orElse(null);
    }
}