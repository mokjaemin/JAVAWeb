package com.spring.ex05;


public class Oracle implements DBConnect{

	@Override
	public void listMembers() {
		// TODO Auto-generated method stub
		System.out.println("Oracle의 listMembers 메서드 호출");
		System.out.println("회원정보를 호출합니다. ");
	}
}
