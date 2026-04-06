package com.Dayroom.project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Dayroom.project.dto.RegisterRequestDTO;
import com.Dayroom.project.entity.User;
import com.Dayroom.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "이미 사용중인 아이디입니다.";
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return "이미 사용중인 이메일입니다.";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        userRepository.save(user);
        return "회원가입 성공!";
    }
}