package com.mycompany.webapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class Ch10SoldOutException extends RuntimeException {//일반 예외로 만들것인지, 실행 예외로 만들것인지 결정
	public Ch10SoldOutException() {
		super("품절");
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
	
}
