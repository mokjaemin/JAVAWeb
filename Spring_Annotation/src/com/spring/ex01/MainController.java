package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


// mainController 라는 이름의 Controller 빈으로 변환
@Controller("mainController")
// 요청이 origin/test 시 여기로 이동
@RequestMapping("/test")
public class MainController {
	
	
	// 요청이 origin/test/main1.do 일때 여기로 이동
   @RequestMapping(value="/main1.do" ,method=RequestMethod.GET)
   public ModelAndView main1(HttpServletRequest request, HttpServletResponse response)  throws Exception{
      ModelAndView mav=new ModelAndView();
      mav.addObject("msg","main1");
      mav.setViewName("main");
      return mav;
   }
   
   

   // 요청이 origin/test/main2.do 일때 여기로 이동
   @RequestMapping(value="/main2.do" ,method = RequestMethod.GET)
   public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception{
      ModelAndView mav=new ModelAndView();
      mav.addObject("msg","main2");
      mav.setViewName("main");
      return mav;
   }
}
