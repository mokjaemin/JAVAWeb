//package sec03.ex01;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Servlet Filter implementation class EncodeFliter
// */
//@WebFilter("/*") 
//// 모든 요청에 대해 필터처리를 한다. 즉, login.html을 요청하든 servlet을 요청하든 계속 실행
//public class EncodeFliter implements Filter {
//
//    /**
//     * Default constructor. 
//     */
//    public EncodeFliter() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
//		// 요청 필터 기능 
//		System.out.println("doFilter 호출");
//		request.setCharacterEncoding("utf-8"); // 모든 요청에 대한 한글처리
//		String context = ((HttpServletRequest) request).getContextPath();
//		String pathinfo = ((HttpServletRequest) request).getRequestURI();
//		String realPath = request.getRealPath(pathinfo);
//		String msg = "Context : " + context + " URI : " + pathinfo + " 물리적 경로 : " + realPath;
//		System.out.println(msg);
//		long begin = System.currentTimeMillis();
//		
//		
//		chain.doFilter(request, response);
//		
//		
//		// 응답 필터 기능 
//		long end = System.currentTimeMillis();
//		System.out.println("필터작업시간 : " + (end-begin) + "ms");
//		
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
