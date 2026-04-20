package com.Dayroom.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dayroom.project.dto.LoginRequestDTO;
import com.Dayroom.project.dto.RegisterRequestDTO;
import com.Dayroom.project.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(authService.login(dto));
	}
	@PostMapping("/register")
	public ResponseEntity<String> Register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}
}