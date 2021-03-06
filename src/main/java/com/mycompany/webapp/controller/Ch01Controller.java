package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;
//Log4j2 어노태이션을 사용하지 않을 경우에는 Log4j2slf4j.Logger 를 import 해준 뒤 사용함
@Controller
@RequestMapping("/ch01")
@Log4j2
public class Ch01Controller {
	//private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	@RequestMapping("/content")
	public String content(){
		//logger.info("실행");
		log.info("실행");
		return "ch01/content";
		
	}
}
