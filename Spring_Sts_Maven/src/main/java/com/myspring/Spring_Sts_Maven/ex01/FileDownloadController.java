package com.myspring.Spring_Sts_Maven.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@Controller
public class FileDownloadController {
	// 파일 저장 경로
	private static String CURR_IMAGE_REPO_PATH = "/Users/mokjaemin/Desktop/image/server";

	// /download 요청시
	@RequestMapping("/download")
	// 요청에 대해 imageFileName 이라는 내용을 변수 imageFileName 에 저장
	public void download(@RequestParam("imageFileName") String imageFileName,
			                 HttpServletResponse response)throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMAGE_REPO_PATH + "/" + imageFileName;
		File file = new File(downFile);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // 
			if (count == -1) // 
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

}
