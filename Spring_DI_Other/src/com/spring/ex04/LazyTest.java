package com.spring.ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LazyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("lazy.xml");
		System.out.println("Second Bean 얻기");
		context.getBean("secondBean");
		// first, third는 그냥 호출 없어도 생성되고 second는 호출헤야 생성됨
	}

}
