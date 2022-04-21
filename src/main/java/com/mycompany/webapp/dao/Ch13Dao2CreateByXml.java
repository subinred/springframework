package com.mycompany.webapp.dao;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch13Dao2CreateByXml {
	
	public Ch13Dao2CreateByXml() {
		log.info("실행");
	}
	
	public static Ch13Dao2CreateByXml getInstance1() {
		Ch13Dao2CreateByXml obj = new Ch13Dao2CreateByXml();
		return obj;
	}
	
	public static Ch13Dao2CreateByXml getInstance2() {
		Ch13Dao2CreateByXml obj = new Ch13Dao2CreateByXml();
		return obj;
	}

}
