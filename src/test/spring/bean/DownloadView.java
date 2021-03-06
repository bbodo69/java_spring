﻿package test.spring.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	
	public DownloadView() { 
		// 내용물의 타입지정
		setContentType("application/download; charset=utf-8");  
	}
	@Override							
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		// Map<String, Object> model : ModelAndView를 통해 보내온 인자를 맵 형태로 받음. 
		
		// 파일 보낼때(download할때) 파일의 정보를 다 같이 보냄
		File file = (File)model.get("downloadFile");		
		response.setContentType(getContentType()); 			
		response.setContentLength((int)file.length());		
		
		String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8"); 
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");  
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);		
			FileCopyUtils.copy(fis, out);			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fis != null) { try {fis.close();}catch(Exception e2) { e2.printStackTrace(); } }
			out.flush();
		}
	
	}
	
}
