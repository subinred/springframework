package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect3After {
	@After("execution(public * com.mycompany.webapp.controller.Ch15Controller.after*(..))")//after 메소드가 실행 된 이후에 아래 메소드 실행!
	public void method() {
		log.info("실행");
	}
}
