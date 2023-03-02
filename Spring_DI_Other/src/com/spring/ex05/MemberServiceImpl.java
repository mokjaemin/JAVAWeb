package com.spring.ex05;

public class MemberServiceImpl implements MemberService{
	private MySQl mysql;
	
	public void setMemberDAO(MySQl mysql) {
		this.mysql = mysql;
	}

	@Override
	public void listMembers() {
		// TODO Auto-generated method stub
		mysql.listMembers();
		
	}

}
