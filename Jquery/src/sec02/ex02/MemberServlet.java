package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/mem2")
public class MemberServlet extends HttpServlet {
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
		PrintWriter writer = response.getWriter();
		
		String id = (String) request.getParameter("id");
		System.out.println(id);
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		List membersList = memberDAO.listMembers(memberVO);
		
		String result = "";
		result = "<main>";
		for(int i = 0; i < membersList.size(); i++) {
			MemberVO m = (MemberVO)membersList.get(i);
			String _id = m.getId();
			String name = m.getName();
			System.out.println(_id);
			System.out.println(name);
			result += "<member><id>" + _id + "</id>";
			result += "<name>" + name + "</name></member>";
		}
		result += "</main>";
		
		writer.print(result);
//		boolean overlappedID = memberDAO.overlappedID(id);
//		
//		if(overlappedID == true) {
//			writer.print("not_usable");
//		}
//		else {
//			writer.print("usable");
//		}
	}

}
