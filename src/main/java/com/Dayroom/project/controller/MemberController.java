package com.Dayroom.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dayroom.project.dto.LoginRequestDTO;
import com.Dayroom.project.dto.RegisterRequestDTO;
import com.Dayroom.project.service.MemberService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
		String result = memberService.register(request);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok("로그인 완료!");
	}
}
