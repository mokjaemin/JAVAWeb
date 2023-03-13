package com.myspring.Spring_Sts_Maven.ex06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


// Rest API를 사용하기 위한 애너테이션 (반환을 화면명이 아닌 데이터로 설정하기 위한)
// 현재는 클래스 전체가 Rest 사용중 -> 전체 메소드의 반환값이 데이터 형식
// 만약 일부는 jsp이름 반환, 일부는 데이터 반환 형식으로 설정 원할시
// @RestController를 지우고 사용하고자 하는 메서드에 @ResponseBody를 설정
@RestController 
@RequestMapping("/test/*")
public class TestController {
	  static Logger logger = LoggerFactory.getLogger(TestController.class);
		
	  // 실습 1. 문자열 전송
	  @RequestMapping("/hello")
	  public String hello() {
		  return "Hello REST!!"; 
	  } 
	  
	  // 실습 2. Json형식(memberVo)로 전송
	  @RequestMapping("/member")
	  public MemberVO member() {
		    MemberVO vo = new MemberVO();
		    vo.setId("mok");
		    vo.setPwd("1234");
		    vo.setName("목재민");
		    vo.setEmail("bamer@naver.com");
		    return vo;
	  } 	
	  
	  
	  // 실습3. Collection형식(리스트)로 데이터 전송
	  @RequestMapping("/membersList")
	  public List<MemberVO> listMembers () {
		    List<MemberVO> list = new ArrayList<MemberVO>();
			for (int i = 0; i < 10; i++) {
			  MemberVO vo = new MemberVO();
			  vo.setId("hong"+i);
			  vo.setPwd("123"+i);
			  vo.setName("목재민"+i);
			  vo.setEmail("mok"+i+"@test.com");
			  list.add(vo);
			}
		    return list; 
	  }	
	  
	  
	  // 실습4. Collection형식()로 데이터 전송
	  @RequestMapping("/membersMap")
	  public Map<Integer, MemberVO> membersMap() {
		    Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		    for (int i = 0; i < 10; i++) {
		      MemberVO vo = new MemberVO();
		      vo.setId("hong" + i);
		      vo.setPwd("123"+i);
		      vo.setName("목재민" + i);
		      vo.setEmail("mokMap"+i+"@test.com");
		      map.put(i, vo); 
		    }
		    return map; 
	  } 	
	  
	  
	  // 실습5. @PathVariable 애너테이션 사용
	  // Get방식과 같이 주소로 전달 데이터 전달시 이를 활용하는 방법
	  @RequestMapping(value= "/notice/{num}" , method = RequestMethod.GET)
	  public int notice(@PathVariable("num") int num ) throws Exception {
		  return num;
	  }	
	  
	  
	  // 실습6. @RequestBody 활용하기
	  // Post방식으로 JSON 전달 받을시 그 데이터를 vo객체에 담아 콘솔에 출력
	  @RequestMapping(value= "/info", method = RequestMethod.POST)
	  public void modify(@RequestBody MemberVO vo ){
		  logger.info(vo.toString());
	  }
	
	  
	  
	  // 실습7. RespnseEntity 클래스 사용하기
	  @RequestMapping("/membersList2")
	  public  ResponseEntity <List<MemberVO>> listMembers2() {
			List<MemberVO> list = new ArrayList<MemberVO>();
			for (int i = 0; i < 10; i++) {
			  MemberVO vo = new MemberVO();
			  vo.setId("mok" + i);
			  vo.setPwd("123"+i);
			  vo.setName("목재민" + i);
		      vo.setEmail("mok"+i+"@test.com");
			  list.add(vo);
			}
		// 리스트와 상태전송(현재는 500) 자세한 내용은 검색 참조
	   return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
	  }	
	  
	  
	  // HttpHeaders 클래스 사용
	  @RequestMapping(value = "/res3")
	  public ResponseEntity res3() {
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    String message = "<script>";
			message += " alert('�� ȸ���� ����մϴ�.');";
			message += " location.href='/pro29/test/membersList2'; ";
			message += " </script>";
			return  new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	  }
	
}