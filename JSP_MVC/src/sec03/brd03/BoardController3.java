package sec03.brd03;

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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class BoardController
 */
//@WebServlet("/board/*")
public class BoardController3 extends HttpServlet {
	
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

				articleVO.setParentNO(0); // 부모기사 인덱스 설정
				articleVO.setId("hong"); // id 설정
				articleVO.setTitle(title); // 제목 설정
				articleVO.setContent(content); // 내용 설정
				articleVO.setImageFileName(imageFileName); // 파일명 설정
				
				// 잘 전달 받았는지 확인 
				System.out.println(title);
				System.out.println(content);
				System.out.println(imageFileName);
				
				// 기존에 생성한 서비스 클래스에 내용 전달
				Integer articleNO;
				articleNO = boardService.addArticle(articleVO);
				nextPage = "/board/listArticles.do";
				
				if(articleNO != -1) {
					File originalFile = new File(ARTICLE_IMAGE_REPO+"/"+imageFileName);
					File moveFile = new File(ARTICLE_IMAGE_REPO+"/"+articleNO);
					moveFile.mkdirs(); // 컴퓨터 내의 새로운 파일 생성
					FileUtils.moveFileToDirectory(originalFile, moveFile, true);
					// originalFile.delete();
				}
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
					// System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					// Map 의 키값으로 파라미터 명과 내용을 삽입
					// title, content 
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					// ex) param1=1 등 식으로 파일 아닌 것들을 출력, title content 등
				} 
				else { // 파일 인것들(사진등)
					// 파라미터 명 출력 imageFileName from articleForm.jsp
					// System.out.println("파라미터 명:" + fileItem.getFieldName());
					//파라미터 크기 출력
					// System.out.println("파일 크기:" + fileItem.getSize() + "bytes");
					// 파일 크기가 0이상 일 경우(파일이 존재할 경우)
					if (fileItem.getSize() > 0) {
						// 파일명 가져옴
						int idx = fileItem.getName().lastIndexOf("\\"); //
						
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						// 실제 파일명만 불러옴
						String fileName = fileItem.getName().substring(idx + 1);
						// System.out.println("파일 명:" + fileName);
						// Map 의 키로 파라미터명 값으로 파일 이름 삽입
						// imageFileName
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "/" + fileName);
						// 컴퓨터내의 해당 경로에 파일 다운로드
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 파라미터 명과 파일 이름 반환 
		return articleMap;
	}

}