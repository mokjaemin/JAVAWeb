package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec01.ex01.MemberBean;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	// 초기화 DB연결
	public MemberDAO(){
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// context.xml에 resource name 과 같아야 함.
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원정보 반환
	public List listMembers() {
		// 초기 리스트 생성
		List membersList = new ArrayList();
		// member.jsp에서 memberVO에 담아둔 이름 불러옴
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				// vo에 생성후 정보를 담고 membersList에 순서대로 담음
				MemberBean vo = new MemberBean();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				membersList.add(vo);
			}
			rs.close();
			pstmt.close();
			//stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 반환
		return membersList;
	}
	
	// 회원 가입
	public void addMember(MemberBean memberBean) {
		try {
			con = dataFactory.getConnection();
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			String query = "insert into t_member";
					query += " (id, pwd, name, email)";
					query += " values(?, ?, ?, ?)";
			System.out.println("Prepared Statememnt : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		try {
			con = dataFactory.getConnection();
			String query = "delete from t_member "+" where id = ?";
			System.out.println("prepared statement : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}






