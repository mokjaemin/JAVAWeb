package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec02.ex01.MemberVO;

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
	public List listMembers(MemberVO memberVO) {
		// 초기 리스트 생성
		List membersList = new ArrayList();
		// member.jsp에서 memberVO에 담아둔 이름 불러옴
		String _name = memberVO.getName();
		System.out.println(_name);
		try {
			// 디비 연결 객체 불러오기
			con = dataFactory.getConnection();
			// 쿼리문 작성
			String query = "select * from t_member ";
			
			// 불러온 이름이 널이 아니고 길이가 0이 아닐때
			if (_name != null && _name.length() != 0) {
				// 쿼리에 해당 이름을 찾는 문법 추가
				query += "where name = ?";
				pstmt = con.prepareStatement(query);
				// 쿼리 ?에 들어갈 변수 설정
				pstmt.setString(1, _name);
			}
			else {
				// 위에 설정 아닐시 그냥 진행
				pstmt = con.prepareStatement(query);
			}
			// 쿼리문 한번 출력
			System.out.println(query);
			// 쿼리 결과를 rs에 담음
			ResultSet rs = pstmt.executeQuery();
			// rs가 빌때가지 진행
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				// vo에 생성후 정보를 담고 membersList에 순서대로 담음
				MemberVO vo = new MemberVO();
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
	public void addMember(MemberVO memberVO) {
		try {
			con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
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






