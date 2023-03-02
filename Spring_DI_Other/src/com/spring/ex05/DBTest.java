package com.spring.ex05;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import com.spring.ex03.MemberService;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("member.xml"));
		MemberService service1 = (MemberService) factory.getBean("MemberService");
		MemberService service2 = (MemberService) factory.getBean("BoardService");
		service1.listMembers();
		
	}

}
