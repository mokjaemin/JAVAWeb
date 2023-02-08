package sec03.brd02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController2 extends HttpServlet {
	
	// 파일경로 변수
	private static String ARTICLE_IMAGE_REPO = "/Users/mokjaemin/Desktop/test";
	// 서비스 클래스 선언
	BoardService boardService;
	// VO 클래스 선언
	ArticleVO articleVO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// 서비스 객체 생성
		boardService = new BoardService();
		// VO 객체 생성
		articleVO = new ArticleVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이동할 페이지 변수 초기화
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 요청받은 경로 받기
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			// 게시판 글 결과를 담을 변수 초기화
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			// Null 일때, 게시판 목록으로 이동
			if (action == null) {
				// 글 목록 서비스 요청 후 결과 담기
				articlesList = boardService.listArticles();
				// 결과를 바인딩
				request.setAttribute("articlesList", articlesList);
				// 바인딩한 결과를 전달한 페이지 설정
				nextPage = "/board02/listArticles.jsp";
			} 
			// ListArtivles.do 경로로 요청이 온 경우
			else if (action.equals("/listArticles.do")) {
				// 위의 과정과 동일
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board02/listArticles.jsp";
			} 
			// articleForm.do 경로로 요청이 온 경우 바로 해당 페이지로 이동
			else if (action.equals("/articleForm.do")) {
				nextPage = "/board02/articleForm.jsp";
			} 
			// addArticle.do 경로로 요청이 온 경우
			else if (action.equals("/addArticle.do")) {
				// 밑에 private method인 upload 호출 및 request, response 전달
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.addArticle(articleVO);
				nextPage = "/board/listArticles.do";
			}
			// 그 이외의 경우는 모두 글 목록 페이지로 이동
			else {
				nextPage = "/board02/listArticles.jsp";
			}
			// 포워딩
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 반환 : Map<String, String>, 함수명 : upload
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 해쉬맵은 key-value, 현재 String, String으로 전달
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		// 파일 클래스에 파일 저정할 경로 지정
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		// DiskFileItemFactory 클래스 객체 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 팩토리 객체에 주소 및 파일 최대크기 담기
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		// ServletFileUpload 클래스에 담아서 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try { // 받아온 파일 처리
			List items = upload.parseRequest(request); // 요청 파싱해서 저장
			for (int i = 0; i < items.size(); i++) { // 아이템의 크기만큼 반복, 각 아이템은 각각의 키값들
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) { // formFiled인지 확인 (file이 아닌것들)
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					// ex) param1=1 등 식으로 파일 아닌 것들을 출력
				} 
				else { // 파일 인것들(사진등)
					System.out.println("파라미터 명:" + fileItem.getFieldName());
					System.out.println("파일 크기:" + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\"); //
						
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일 명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}