package com.mycompany.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

//모든 컨트롤러에서 발생한 예외를 처리하기 위해 만든 클래스
@Component
@ControllerAdvice
@Log4j2
public class Ch10ExceptionHandler {
	@ExceptionHandler(NullPointerException.class)//구체적인 에러가 발생한 경우
	public String handleNullPointerException(NullPointerException e) {
		log.info("실행:"+e.getMessage());
		return "ch10/500_null";
	}
	@ExceptionHandler(ClassCastException.class)
	public String handleClassCastException(ClassCastException e) {
		log.info("실행:"+e.getMessage());
		return "ch10/500_cast";
	}
	@ExceptionHandler(Ch10SoldOutException.class)
	public String handleCh10SoldOutException(Ch10SoldOutException e) {
		log.info("실행:"+e.getMessage());
		return "ch10/soldout";
	}
	@ExceptionHandler(Exception.class)//에러가 구체적이지 않고, 500 에러가 떳을 경우
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//응답의 staus 번호를 변경해줌, 상황에 따라 자바스크립트에서 다르게 처리해줄 필요가 있음
	public String handleExcepion(Exception e) {
		log.info("실행:"+e.getMessage());
		e.printStackTrace();
		return "ch10/500";
	}
	@ExceptionHandler(NoHandlerFoundException.class)//404에러를 500에러로 변환했을 경우 사용
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoHandlerFoundException(NoHandlerFoundException e) {
		log.info("실행:"+e.getMessage());
		return "ch10/404";
	}
}
