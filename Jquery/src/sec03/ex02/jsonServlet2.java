package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class jsonServlet2
 */
@WebServlet("/json2")
public class jsonServlet2 extends HttpServlet {
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
		
		JSONObject totalObject = new JSONObject();
		JSONArray memberArray = new JSONArray();
		
		JSONObject memberInfo = new JSONObject();
		memberInfo.put("name", "목재민");
		memberInfo.put("age", "27");
		memberArray.add(memberInfo);
		
		
		memberInfo = new JSONObject();
		memberInfo.put("name", "목주형");
		memberInfo.put("age", "22");
		memberArray.add(memberInfo);
		
		totalObject.put("members", memberArray);
		
		String jsonInfo = totalObject.toJSONString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
		
	}

}
