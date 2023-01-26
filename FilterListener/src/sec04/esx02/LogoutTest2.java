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
 * Servlet implementation class LogoutTest2
 */
@WebServlet("/logout2")
public class LogoutTest2 extends HttpServlet {
	ServletContext context;

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
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		context = getServletContext();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		
		session.invalidate(); // LoginImpl의 sessionDestroyed 호출됨.
		
		// 컨텍스트에 담겨있는 user_list 불러옴
		List user_list = (ArrayList) context.getAttribute("user_list");
		// user_list에서 아이디 제거
		user_list.remove(user_id);
		// 실제 컨텍스트에서도 아이디 제거
		context.removeAttribute("user_list");
		// 위에서 해당 아이디를 제거한 user_list를 컨텍스트에 새롭게 바인딩
		context.setAttribute("user_list", user_list);
		out.println("<br>로그아웃하였습니다. ");
	}
}
