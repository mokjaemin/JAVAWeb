package sec03.brd04;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BoardController4 extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "/Users/mokjaemin/Desktop/article";
	BoardService boardService;
	ArticleVO articleVO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board03/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/board03/articleForm.jsp";
			} else if (action.equals("/addArticle.do")) {
				int articleNO=0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO= boardService.addArticle(articleVO);
				if(imageFileName!=null && imageFileName.length()!=0) {
				    File srcFile = new 	File(ARTICLE_IMAGE_REPO +"/"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO +"/"+articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"  alert('글 업로드 완료.');" 
						 +" location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");

				return;
			}else if(action.equals("/viewArticle.do")){
				// listArticles.jsp로 부터 ArticleNo 불러옴
				String articleNO = request.getParameter("articleNO");
				// 서비스에 viewArticle 요청, 요청시 파싱한 articleNO 전송
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				// request에 바인딩
				request.setAttribute("article", articleVO);
				// 다음 이동할 페이지 설정
				nextPage = "/board03/viewArticle.jsp";
			
			}else {
				nextPage = "/board03/listArticles.jsp";
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