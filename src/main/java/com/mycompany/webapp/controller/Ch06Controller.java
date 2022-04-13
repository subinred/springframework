package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	//ajax 요청을 할 때는 redirect 불가능 합니다잇
	@GetMapping("/getFragmentHtml")//응답이 html 조각을 생성해서 반드시 보내줘야하기 때문에 redirect 불가
	public String GetFragmentHtml() {//요청하면 html 조각을 받음
		return "ch06/fragemnt.html";
	}
	
	
	//컨트롤러가 직접 요청 처리하면서 응답을 만드는 경우
	@GetMapping("/getJson1")//응답이 html 조각을 생성해서 반드시 보내줘야하기 때문에 redirect 불가
	public void getJson1(HttpServletResponse response) throws Exception{//요청하면 json을 받음
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json=jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8");//응답 내용의 타입이 뭔지 확인
		PrintWriter pw=response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8")
	@ResponseBody //리턴되는 내용이 응답 본문에 들언간다
	public String getJson2() throws Exception{//요청하면 json을 받음
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json=jsonObject.toString();
		return json;
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		return "redirect:/";//잘못된 방식
	}
}
