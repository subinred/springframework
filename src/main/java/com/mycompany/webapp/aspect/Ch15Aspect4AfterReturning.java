package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect4AfterReturning {
	@AfterReturning( 
			pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))",
			returning="returnValue" 
     )
	public void method(String returnValue) {//()안에는 실제로 메소드가 리턴할 값이 들어감
		log.info("실행");
		log.info("리턴값:"+returnValue);
	}
}
