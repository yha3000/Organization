package com.hyeon.organization.response;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.BAD_REQUEST.getStatus())
				.body(ExceptionEntity.builder()
					.code(ExceptionEnum.BAD_REQUEST.getCode())
					.message("요청값이 적절하지 않음")
					.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
		e.printStackTrace();
		return ResponseEntity
				.status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
				.body(ExceptionEntity.builder()
					.code(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
					.message("내부 서버 오류")
					.build());
	}
	
}