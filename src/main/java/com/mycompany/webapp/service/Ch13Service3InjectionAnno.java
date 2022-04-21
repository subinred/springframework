package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao1CreateByAnno;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service3InjectionAnno {//주입과 관련된 예제
	public Ch13Service3InjectionAnno() {
		log.info("실행");	
	}
	//타입으로 주입
	/*@Resource
	private Ch13Dao1CreateByAnno ch13Dao1;
	//private TestDao testDao;//테스트 용으로 비어잇는 TestDao를 생성후 import하면 관리객체가 아니기 때문에 실행하면 에러가 남
	
	@Resource
	public void setCh13Dao1(Ch13Dao1CreateByAnno ch13Dao1) {
		log.info("실행: 타입으로 주입");	
		this.ch13Dao1 = ch13Dao1;
	}*/
	
	//이름으로 주입
	@Resource(name="ch13Dao1")
	private Ch13Dao1CreateByAnno ch13Dao1;
	
	@Resource(name="ch13Dao1")
	public void Ch13Dao1(Ch13Dao1CreateByAnno ch13Dao1) {
		log.info("실행: 이름으로 주입");	
		this.ch13Dao1=ch13Dao1;
	}
	//이름으로 주입하나 타입으로 주입하나 동일함, 타입은 중복되는 경우 어던 객체를 주입해야할지 모르기 때문에
	//에러가 날 수 있을 경우, 타입의 객체가 하나만 관리될 경우에만 사용가능
	
}
