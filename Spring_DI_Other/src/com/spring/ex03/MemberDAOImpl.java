package com.spring.ex03;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public void listMembers() {
		// TODO Auto-generated method stub
		list();
	}
	
	public void list() {
		System.out.println("MemberDAOImpl의 listMembers 메서드 호출");
		System.out.println("회원정보를 호출합니다. ");
	}
	
}
