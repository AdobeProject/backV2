package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends RuntimeException {
	public InvalidArgumentException() {
		super("Invalid Argument was Passed");
	}

	public InvalidArgumentException(String message) {
		super(message);
	}
}
