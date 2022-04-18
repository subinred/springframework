package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2
@SessionAttributes({"inputForm"}) //새로운 sessionFrom을 생성하여 Ch08InputForm inputForm 객체 저장
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	@GetMapping(value="/saveData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		session.setAttribute("name", name);
		
		//{"result": "success"} json 보내려면 jsonobject를 만들고 json array 객체를 만들어야 함
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", "success");
		String json=jsonObject.toString();
		return json;
	}
	
	@GetMapping(value="/readData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session) {
		String name=(String) session.getAttribute("name");
		//{"name":"홍길동"}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", name);
		String json=jsonObject.toString();
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		if(mid.equals("spring")&&mpassword.equals("12345")) {
			//로그인 성공 시 세션에 회원 아이디를 저장
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("sessionMid");//세션에서 주어진 키를 삭제
		//session.invalidate();//세션 객체 자체를 제거
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/userInfo")
	public String userInfo(@SessionAttribute String sessionMid, @SessionAttribute("sessionMid") String mid, HttpSession session) {
		String memberId=(String) session.getAttribute("sessionMid");
		log.info("memberId:" + memberId);
		log.info("sessionMid:" + sessionMid);
		log.info("mid:" + mid);
		return "redirect:/ch08/content";
	}
	//AJAX를 이용한 로그인
	@RequestMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		log.info("실행");
		String result=null;
		if(mid.equals("spring")) {
			if(mpassword.equals("12345")) {
				result="success";
				session.setAttribute("sessionMid", mid);
			}else {
				result="wrongMpassword";
			}
		}else {
			result="wrongMid";
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", result);
		String json=jsonObject.toString();
		return json;
	}
	
	@RequestMapping(value = "/logoutAjax", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		session.removeAttribute("sessionMid");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;   
	 }
	
	@GetMapping("/inputStep1")
	public String inputStep1() {
		return "ch08/inputStep1";
	}
	//새로운 세션 저장소에 객체를 저장하는 역할, 단 한번만 실행
	@ModelAttribute("inputForm")
	  public Ch08InputForm getCh08InputForm() {
		log.info("실행");
		Ch08InputForm inputForm=new Ch08InputForm();
		return inputForm;
	  }
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		log.info("data1:"+inputForm.getData1());
		log.info("data1:"+inputForm.getData2());
		return "ch08/inputStep2";
	}
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		log.info("data1:"+inputForm.getData1());
		log.info("data1:"+inputForm.getData2());
		log.info("data3:"+inputForm.getData3());
		log.info("data4:"+inputForm.getData4());
		sessionStatus.setComplete();//세션 저장소(setAttributes) 제거
		return "redirect:/ch08/content";
	}
	
}
