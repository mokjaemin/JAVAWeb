package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




// 해당 이름으로 컨트롤러 빈 생성
@Controller("loginController")

public class LoginController {
	
	
	
	// 1. 해당 요청에 대해, Get방식일때 실행
	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" }, method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	
	// 2. 해당 요청에 대해, Get, Post 방식일때 실행
    @RequestMapping(value = "/test/login.do", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		// 이동할 View이름 설정
		mav.setViewName("result");
		// 입력받은 아이디와 이름 바인딩
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		// 전달 객체에 바인딩
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
    
    
    // 3. @RequestParam 사용해보기
	@RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	// 전송받은 request에 대해서 바로 userID, userName에 담음
	public ModelAndView login2(@RequestParam("userID") String userID, 
			                  @RequestParam("userName") String userName,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		// 이동할 화면
		mav.setViewName("result");
		System.out.println("userID: " + userID);
		System.out.println("userName: " + userName);
		// 바인딩
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
		
		
		// 해당 내용 대신 @RequestParam을 사용
		// String userID = request.getParameter("userID");
		// String userName = request.getParameter("userName");
	}
	
	
	
	
	// 4. RequestParam 사용
	// required = true or 지정 x시 해당 request를 전달 받지 못할시 오류
	// required = false 지정 시 해당 request를 전달 받지 못해도 오류 x
	@RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login2(@RequestParam("userID") String userID, 
                               @RequestParam(value="userName", required=true) String userName,
			                   @RequestParam(value="email", required=false) String email,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// usetID, userName은 전달 받지 못하면 오류 발생
		
		
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		System.out.println("email: "+ email);
		
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}
	
	
	
	
	// 5. RequestParam Map<String, String> info 사용해보기
	// 데이터가 많을 때 사용
	@RequestMapping(value = "/test/login3.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login3(@RequestParam Map<String, String> info,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		String userID = info.get("userID");
		String userName = info.get("userName");
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		
		mav.addObject("info", info);
		mav.setViewName("result");
		return mav;
	}
	
	
	
	
	// 6. ModelAttribute 사용
	// 1) 전달받은 request를 loginVO에 담음
	// 2) 이를 info라는 변수에 사용
	@RequestMapping(value = "/test/login4.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		System.out.println("userID: " + loginVO.getUserID());
		System.out.println("userName: " + loginVO.getUserName());
		
		// 아래 설정 없이 알아서 포워딩
		// 이후 jsp에서는 info. 으로 내용 호출
		// mav.addObject("info", info);
		mav.setViewName("result");
		return mav;
	}
	   
    
    
    // 7. Model 클래스 사용
	// ModelAndView 대신에 사용
	// 바인딩시 사용
	@RequestMapping(value = "/test/login5.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login5(Model model,
			                   HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "jaemin");
		// model 포워딩 없이 전달 가능
		// 전달받은 jsp에서는 usetID, usetName으로 바로 사용 가능
		return "result";
	}	
	
	
	
}
