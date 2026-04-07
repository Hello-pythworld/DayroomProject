package com.Dayroom.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Dayroom.project.service.AuthService;
import com.Dayroom.project.dto.LoginRequestDTO;
import com.Dayroom.project.dto.RegisterRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO request){
		String token = authService.login(request);
		return ResponseEntity.ok(token);
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
		authService.register(request);
		return ResponseEntity.ok("회원가입 성공!");
	}
}
