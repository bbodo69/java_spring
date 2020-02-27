package test.spring.bean;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class upload {

	@RequestMapping("uploadForm.do")
	public String uploadForm() {
		System.out.println("test");
		return "spring03/uploadForm";
		
	}
	
	
	@RequestMapping("uploadPro.do")
	public String uploadPro(String writer, MultipartHttpServletRequest request) { 
		// MultipartHttpServletRequest ����ؾ���, ���� HttpServletRequest �δ� ������ ���� �� ����.		
		System.out.println("writer = " + writer);
		MultipartFile mf = null;
		try {
			mf = request.getFile("img"); // ������ jsp ������ ������ �Ӽ��� name �� �����ֱ�.
			// ���� ������ ��� + ���ϸ� -> File ��ü ����
			File copyFile = new File("d://save//" + mf.getOriginalFilename()); // ������ �̸��� ���ؼ� �ش� �ּҿ� ��������.
			// ������ ��ġ + ���ϸ����� ����
			mf.transferTo(copyFile);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "spring03/uploadPro";
		
	}
	
}
