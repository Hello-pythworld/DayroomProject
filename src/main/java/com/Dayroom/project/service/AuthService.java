package com.Dayroom.project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Dayroom.project.dto.LoginRequestDTO;
import com.Dayroom.project.dto.RegisterRequestDTO;
import com.Dayroom.project.entity.User;
import com.Dayroom.project.repository.UserRepository;
import com.Dayroom.project.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;


	public String register(RegisterRequestDTO request) {
		
		//중복 확인
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("이미 사용 중인 아이디입니다.");
		}
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("이미 사용 중인 이메일입니다.");
		}
		
		// 비밀번호 암호화 후 저장
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		
		userRepository.save(user);
		return "회원가입 성공";
	}
	
	public String login(LoginRequestDTO request) {

		// 유저 조회
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

		// 비밀번호 확인
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}

		// 임시 토큰 반환 (추후 JWT로 교체)
		return jwtUtil.generateToken(user.getUsername());
	}
}
