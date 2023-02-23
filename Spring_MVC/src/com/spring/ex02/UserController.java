package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = "";
		String passwd = "";
		String viewName = getViewName(request);
		
		// ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView();
		
		// 전달받은 데이터 가져옴
		request.setCharacterEncoding("utf-8");
		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");

		// 전달받은 데이터 정제후 설정 (바인딩)
		// ModelAndView에 전달
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		// 포워딩할 jsp명 설정
		// mav.setViewName("result");
		
		// 실무에선 이렇게 viewName을 얻어옴
		mav.setViewName(viewName);
		return mav;
	}
	
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    ModelAndView mav=new ModelAndView();
	    String id=request.getParameter("id");
	    String pwd=request.getParameter("pwd");
	    String name=request.getParameter("name");
	    String email=request.getParameter("email");

	    mav.addObject("id",id);
	    mav.addObject("pwd",pwd);
	    mav.addObject("name",name);
	    mav.addObject("email",email);
	    
	    mav.setViewName("memberInfo");
	    return mav;
	}
	
	private  String getViewName(HttpServletRequest request) throws Exception {
	      String contextPath = request.getContextPath();
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }
	      
	      
	      int begin = 0;  //
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();  
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");  
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");   
	      }else{
	         end=uri.length();
	      }

	      // 요청에 대해서 .do와 /사이에 있는 이름 가져와 반환
	      // ex) jsp 안에 있는 action="${contextPath}/test/login.do" 요청시
	      // login 반환
	      String fileName=uri.substring(begin,end);
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));  
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());   
	      }
	      return fileName;
	}
	
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		return new ModelAndView(viewName);
	}
	
	
}