package com.mycompany.webapp.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch07")
@Log4j2
public class Ch07Controller {
   
   private int count;

@RequestMapping("/content")
   public String content(HttpServletRequest request) {
      
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat();
      String strDate = sdf.format(date);
      
      request.setAttribute("strDate", strDate);
      
      
      
      return "ch07/content";
      
   }
   
   @RequestMapping("/requestScopeSave")
   public String requestScopeSave(HttpServletRequest request) {
      //request 범위에 객체 저장
      request.setAttribute("requestScopeValue", "홍길동");
      
      //멤버 객체 생성
      Ch07Member member = new Ch07Member();
      member.setName("홍길동");
      member.setAge(25);
      member.setJob("프로그래머");
      Ch07City city = new Ch07City();
      city.setName("seoul");
      member.setCity(city);
      request.setAttribute("member", member);
      return "ch07/content";
   }
   
   @RequestMapping("/sessionScopeSave")
   public String sessionScopeSave(HttpSession session) {
      //request 범위에 객체 저장
      session.setAttribute("sessionScopeValue", "aaa");
      
      //멤버 객체 생성
      Ch07Member member = new Ch07Member();
      member.setName("aaa");
      member.setAge(30);
      member.setJob("grammar");
      Ch07City city = new Ch07City();
      city.setName("jeju");
      member.setCity(city);
      session.setAttribute("member", member);
      return "ch07/content";
      
   }
   
   @RequestMapping("/applicationScopeSave")
   public String applicationScopeSave(HttpServletRequest request) {
      //application 범위에 객체 저장
      ServletContext application = request.getServletContext();
      application.setAttribute("applicationScopeValue", "bbb");
      
      Integer counter=++count;
      application.setAttribute("counter", counter);
      return "ch07/content";
   }
   
   @GetMapping("/useJstl1")
   public String useJstl1(HttpServletRequest request) {
	   String[] languages= {"Java", "JavaScript", "Springframwork", "Vue"};
	   request.setAttribute("langs",languages);
	   return "ch07/usejstl1";
   }
   
   @GetMapping("/useJstl2")
   public String useJstl2(HttpServletRequest request) {
	   List<Ch07Board> list=new ArrayList<>();
	   for(int i=1;i<=5;i++) {
		   Ch07Board board=new Ch07Board(i,"제목"+i, "내용"+i, "글쓴이"+i, new Date());
		   list.add(board);
	   }
	   request.setAttribute("boardList", list);
	   return "ch07/usejstl2";
   }
   @GetMapping("/useModelAndViewReturn")
   public ModelAndView modelAndViewReturn() {
	   Ch07Board board=new Ch07Board(1, "제목1", "내용1", "글쓴이", new Date());
	   ModelAndView modelAndView = new ModelAndView();
	   modelAndView.addObject("board", board);
	   modelAndView.setViewName("/ch07/boardDetail");
	   return modelAndView;
   }
   
   @GetMapping("/modelArgument")//위 방법보다 최근에는 model 매개변수를 이용하는 이 방법을 많이 사용함
   public String modelArgument(Model model) {
	   Ch07Board board=new Ch07Board(1, "제목1", "내용1", "글쓴이", new Date());
	   model.addAttribute("board", board);
	   return "/ch07/boardDetail";
   }
   
  @GetMapping("/modelAttribute")
  public String modelAttribute(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex) {
	   return "/ch07/clothInfo";
  }
	/*
	 * @GetMapping("/modelAttribute") public String modelAttribute(String kind,
	 * String sex Model model) { model.Attribute("kind", kind);
	 * model.Attribute("sex", sex); return "/ch07/clothInfo"; }
	 */
  @GetMapping("/commandObject")
  public String commandObject(@ModelAttribute("cloth") Ch07Cloth cloth) {
	   return "/ch07/clothInfo";
  }
	/*
	 * @GetMapping("/commandObject") public String
	 * commandObject(Ch07Cloth cloth) { 
	 * return
	 * "/ch07/clothInfo"; }
	 */

}
