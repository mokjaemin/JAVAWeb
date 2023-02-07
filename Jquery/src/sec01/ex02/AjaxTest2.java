package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest2
 */
@WebServlet("/AjaxTest2")
public class AjaxTest2 extends HttpServlet {
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
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String result = "";
		PrintWriter writer = response.getWriter();
		
		result = "<main><book>"+
					"<title><![CDATA[초보자를 위한 자바 프로그래밍1]]></title>" +
					"<writer><![CDATA[목재민]]></writer>"+
					"<image><![CDATA[http://localhost:8090/Jquery/image/image1.png]]></image>"+
				"</book>" +
				"<book>" + 
					"<title><![CDATA[초보자를 위한 자바 프로그래밍2]]></title>" +
					"<writer><![CDATA[목재민]]></writer>"+
					"<image><![CDATA[http://localhost:8090/Jquery/image/image1.png]]></image>"+
				"</book></main>";
		System.out.println(result);
		writer.print(result);
	}

}
