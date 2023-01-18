package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
//		out.print("<script type='text/javascript'>");
//		out.print("location.href='second';");
//		out.print("</script>"); // 1. location 방식
		
//		response.addHeader("Refresh", "1; url=second"); // 2. Refresh 방식
		
//		response.sendRedirect("second"); // 3. Redirect 방식
//		response.sendRedirect("second?name=mok"); // 3. Redirect 방식, get 방식으로 데이터 전달
//		RequestDispatcher dispatch = request.getRequestDispatcher("second");
		
//		dispatch.forward(request, response); // 4. Dispatch 방식
//		RequestDispatcher dispatch = request.getRequestDispatcher("second?name=mok");
//		dispatch.forward(request, response); // 4. Dispatch 방식, get 방식으로 데이터 전송
		
		request.setAttribute("address", "천안시");
		RequestDispatcher dispatch = request.getRequestDispatcher("second");
		dispatch.forward(request, response); // 5. Dispatch 방식, binding 방식으로 데이터 전송
	}

}
