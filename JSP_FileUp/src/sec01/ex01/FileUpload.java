package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
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
		String encoding = "utf-8";
		File currentDirPath = new File("/Users/mokjaemin/Desktop/test"); // 파일이 저장될 경로 지정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024); // 최대 업로드할 파일 용량
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileName = null;
		try {
			List items = upload.parseRequest(request);
			for (int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				
				if (fileItem.isFormField()) { // form 필드안에 type을 text로 전달된 내용들인가?
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				}
				else { // 파일인가? 
					System.out.println("파라미터 명 : " + fileItem.getFieldName());
					System.out.println("파일명 : " + fileItem.getName());
					System.out.println("파일 크기 : " + fileItem.getSize() + "bytes");
					
					if (fileItem.getSize() > 0) { // 첨부할 파일이 존재하면
						// 경로를 기준으로 \\의 위치를 찾음
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						// idx의 위치 다음부터의 내용을 불러움 즉, 파일의 이름을 받아옴
						fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "/" + fileName);
						fileItem.write(uploadFile); // 파일 저장
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("param1", fileName);
		RequestDispatcher dis = request.getRequestDispatcher("test02/result.jsp");
		dis.forward(request, response);
		
 	}
}

