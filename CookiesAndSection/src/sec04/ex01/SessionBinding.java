package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionBinding
 */
@WebServlet("/login10")
public class SessionBinding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // 세션생성
		String user_id = request.getParameter("user_id"); // html로부터 아이디 불러옴 
		String user_pw = request.getParameter("user_pw"); // html로부터 비밀번호 불러옴
		
		if(session.isNew()) { // 세션 생성이 처음이면(기존 생성된 세션이 없으면)
			if(user_id != null) { // user_id가 비어있지 않으면
				session.setAttribute("user_id", user_id); // 세션에 특성 셋
				
				// encodeURL 사용 - 보안 상의 이유로 쿠키사용 금지시 사
//				String url = response.encodeURL("login10");
//				out.println("<a href=" + url + ">로그인 상태 확인</a>");
				
				out.println("<a href='login10'>로그인 상태 확인</a>");
			}
			else { // user_id의 입력이 없었으면
				out.println("<a href='login2.html'>다시 로그인하세요 </a>");
				session.invalidate();
			}
		}
		else { // 기존에 생성된 세션이 있으면
			user_id = (String) session.getAttribute("user_id"); // 세션의 특성 불러오기
			if(user_id != null && user_id.length() != 0) { // 아이디가 잘 저장되어있으면
				out.print("안녕하세요 " + user_id);
			}
			else{ // 아이디가 잘 저장되어있지 않으면 
				out.println("<a href='login2.html'>다시 로그인하세요 </a>");
				session.invalidate();
			}
		}
	}

}
