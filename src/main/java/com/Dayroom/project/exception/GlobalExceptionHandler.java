package com.Dayroom.project.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//CustomException 처리 (ErrorCode 기반)
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Map<String, Object>> handleCustomException(CustomException e) {
		ErrorCode errorCode = e.getErrorCode();
		return buildResponse(errorCode.getStatus().value(), errorCode.getMessage());
	}
	
	// @Valid 유효성검사 실패 처리
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
		String message = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.findFirst()
				.orElse(ErrorCode.INVALID_INPUT.getMessage());
		
		return buildResponse(ErrorCode.INVALID_INPUT.getStatus().value(), message);
	}
	
	// 예상치 못한 예외 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception e) {
		return buildResponse(500,  "서버 내부 오류가 발생했습니다.");
	}
	
	// 공통 응답 포맷
	private ResponseEntity<Map<String, Object>> buildResponse(int status, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", status);
		body.put("message", message);
		body.put("timestamp", LocalDateTime.now());
		
		return ResponseEntity.status(status).body(body);
	}
}
