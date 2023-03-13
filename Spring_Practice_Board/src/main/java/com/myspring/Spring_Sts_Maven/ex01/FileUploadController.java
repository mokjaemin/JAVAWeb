package com.myspring.Spring_Sts_Maven.ex01;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FileUploadController  {
	private static final String CURR_IMAGE_REPO_PATH = "/Users/mokjaemin/Desktop/image/server";
	
	// /from 요청시
	@RequestMapping(value="/form")
	public String form() {
	    return "uploadForm";
	}
	
	// upload 요청시
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	// MultipartHttpServletRequest -> 다중 파일 업로드 시 사용하는 클래스
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest,HttpServletResponse response)
	  throws Exception{
		multipartRequest.setCharacterEncoding("utf-8");
		Map map = new HashMap();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			//System.out.println(name+", "+value);
			map.put(name,value);
		}
		
		List fileList= fileProcess(multipartRequest);
		map.put("fileList", fileList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("result");
		return mav;
	}
	
	
	// jsp에서 multiple = "multiple" 지정 후 여러개의 파일을 한번에 업로드하기 위한 메서드
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<String> fileList= new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName=mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(CURR_IMAGE_REPO_PATH + "/" + fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(! file.exists()){ // 경로상에 파일이 존재하지 않을 경우
					if(file.getParentFile().mkdirs()){ // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 이 후 파일 생성
					}
				}
				// 임시로 저장된 MultipartFile(위에서 생성한)을 실제로 전송
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"/"+ originalFileName));
			}
		}
		return fileList;
	}
}
