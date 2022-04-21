package com.mycompany.webapp.aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Pager;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect7Around {// 6번과 표현식의 차이
	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck)") // 핵심 코드 앞과 뒤에 붙음, 그렇기 때문에 핵심 기점을 만드는데
																				// 사용됨
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		// 전처리(핵심 코드 이전에 실행할 코드)
		log.info("전처리 실행");
		long start = System.nanoTime();
		Object result = joinPoint.proceed(); // -> proceed(=핵심코드 실행)는 around를 실행하게됨

		// 후처리(핵심 코드 이후에 실행할 코드)
		log.info("후처리 실행");
		long end = System.nanoTime();
		long howLong = end - start;
		// 핵심 코드(메소드)이름 얻기
		String methodName = joinPoint.getSignature().toShortString();

		log.info(methodName + " 실행 시간: " + howLong + "ns");

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		return result;
	}

	
}
