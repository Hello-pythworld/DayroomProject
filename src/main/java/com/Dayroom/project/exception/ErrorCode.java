package com.Dayroom.project.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	// 회원 관련
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "이미 사용 중인 아이디입니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
	
	// 일정 관련
	SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."),
	SCHEDULE_FORBIDDEN(HttpStatus.FORBIDDEN, "본인의 일정만 수정/삭제할 수 있습니다."),
	
	// JWT 관련
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
	
	// 공통
	INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다.");
	
	private final HttpStatus status;
	private final String message;
	
	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
