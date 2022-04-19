package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
//Log4j2 어노태이션을 사용하지 않을 경우에는 Log4j2slf4j.Logger 를 import 해준 뒤 사용함
@Controller
@RequestMapping("/ch12")
@Log4j2
public class Ch12Controller {
	//private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	@RequestMapping("/content")
	public String content(){
		//logger.info("실행");
		log.info("실행");
		return "ch12/content";	
	}
	@RequestMapping("/fileList")
	public String fileList() {
		log.info("실행");
		return "ch12FileListView";
	}
	@RequestMapping("/fileDownload")
	public String fileDownload(@ModelAttribute("fileName") String fileName, 
			@ModelAttribute("userAgent") @RequestHeader("User-Agent") String userAgent) {
		log.info("실행");
		return "ch12FileDownloadtView";
	}
	/*
	 * @RequestMapping("/fileDownload") public String
	 * fileDownload(@ModelAttribute("fileName") String fileName) { log.info("실행");
	 * return "ch12FileDownloadtView"; }
	 */
}
