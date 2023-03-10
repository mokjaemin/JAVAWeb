package com.myspring.Spring_Sts_Maven.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "/Users/mokjaemin/Desktop/image/server";
	
	
	// 파일 업로드시 화면에 출력하는 기능
//	@RequestMapping("/download")
//	protected void download(@RequestParam("imageFileName") String imageFileName,
//			                 HttpServletResponse response) throws Exception {
//		OutputStream out = response.getOutputStream();
//		String filePath = CURR_IMAGE_REPO_PATH + "/" + imageFileName;
//		File image = new File(filePath);
//		int lastIndex = imageFileName.lastIndexOf(".");
//		String fileName = imageFileName.substring(0,lastIndex);
//		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"/"+"thumbnail"+"/"+fileName+".png");
//		if (image.exists()) { 
//			thumbnail.getParentFile().mkdirs(); // 썸네일 폴더가 없으면 생성
//		    Thumbnails.of(image).size(50,50).outputFormat("png").toFile(thumbnail);
//		}
//
//		FileInputStream in = new FileInputStream(thumbnail);
//		byte[] buffer = new byte[1024 * 8];
//		while (true) {
//			int count = in.read(buffer); // 버퍼에 읽어들인 문자개수
//			if (count == -1) // 버퍼의 마지막에 도착했는지 체크
//				break;
//			out.write(buffer, 0, count);
//		}
//		in.close();
//		out.close();
//	}
	
	// 위와 다르게 파일을 저장하지 않고 빠르게 출력
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"/"+"thumbnail"+"/"+fileName+".png");
		if (image.exists()) { 
			// 썸네일 파일 조정을 통해 용량 조정
			Thumbnails.of(image).size(50,50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
	
}
