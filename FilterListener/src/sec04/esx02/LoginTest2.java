package sec04.esx02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginTest2
 */
@WebServlet("/login2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context;
	List user_list = new ArrayList();
	static int count = 0;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		context = getServletContext();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
//		Integer loginCheck = 0;
		
		List list = (ArrayList) context.getAttribute("user_list");
		if(list != null) {
			for (int i=0; i<list.size(); i++) {
				System.out.println("리스트 : " + list.get(i));
				System.out.println("입력 : " + user_id);
				if(list.get(i).equals(user_id)) {
					System.out.println("이미 로그인 되어있습니다.");
//					loginCheck = 1;
					++count;
					System.out.println(count);
				}
			}
		}
		
		if(count <= 5) {
			LoginImpl loginUser = new LoginImpl(user_id, user_pw);
			if(session.isNew()) {
				// LoginImpl의 SessionCreated 호출됨
				session.setAttribute("loginUser", loginUser);
				// user_id 리스트에 접속한 아이디 넣고 그것을 context에 바인딩
				user_list.add(user_id);
				context.setAttribute("user_list", user_list);
			}
			out.println("<html><body>");
			out.println("아이디는 " + loginUser.user_id + "<br>");
			out.println("총접속자 수는 " + LoginImpl.total_user + "<br>");
			out.println("접속 아이디는 : <br>");
			// 컨텍스트에 저장된 정보 가져와서 출력
			List list2 = (ArrayList) context.getAttribute("user_list");
			for (int i=0; i<list2.size(); i++) {
				out.println(list2.get(i) + "<br>");
			}
			// 로그아웃 서블릿에 get방식으로 로그아웃할 아이디 같이 전달
			out.println("<a href='logout2?user_id=" + "user_id" + "'>로그아웃</a>");
			out.println("</body></html>");
		}
		else {
			out.println("<html><body>");
			out.println("이미 로그인 되어있습니다.<br>");
			out.println("<a href='login2.html'>뒤로가기</a>");
			out.println("</body></html>");
		}
		
	}

}
