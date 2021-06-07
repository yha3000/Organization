package com.hyeon.organization.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExceptionEntity {
	
	private String code;
	private String message;

	@Builder
	public ExceptionEntity(HttpStatus status, String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}