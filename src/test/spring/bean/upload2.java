package test.spring.bean;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class upload2 {

	@RequestMapping("uploadForm2.do")
	public String uploadForm() {
		System.out.println("test");
		return "spring03/uploadForm2";
		
	}
	
	
	@RequestMapping("uploadPro2.do")
	public String uploadPro(String writer, MultipartHttpServletRequest request, Model md) { 
		// MultipartHttpServletRequest ����ؾ���, ���� HttpServletRequest �δ� ������ ���� �� ����.		
		System.out.println("writer = " + writer);
		MultipartFile mf = null;
		String newName = null;
		
		try {
			
			
//			// #2. ������ ����
//			mf=request.getFile("img");
//			String path = request.getRealPath("save"); // �������� save ���� ��� ��������
//			System.out.println(path);
//			String imgPath = path + "||" + mf.getOriginalFilename(); 
//			File copyFile = new File(imgPath);
//			mf.transferTo(copyFile);
			
			// #3. ���� �̸� �ߺ�ó�� : ���ο� ���ϸ�+Ȯ���ڸ����
			// �������� ���ϸ�+��¥
			mf = request.getFile("img");
			
			String orgName = mf.getOriginalFilename(); // �������� ���ϸ�
			String imgName = orgName.substring(0, orgName.lastIndexOf('.')); // �����̸���
			String ext = orgName.substring(orgName.lastIndexOf('.')); // Ȯ���ڸ�
			long date = System.currentTimeMillis();
			newName = imgName+date+ext;
			System.out.println(newName);
			
			String path = request.getRealPath("save");
			System.out.println(path);
			String imgPath = path + "\\" + newName;
			System.out.println(newName);
			File copyFile = new File(imgPath);
			mf.transferTo(copyFile);
			
			md.addAttribute("newName", newName);
			
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "spring03/uploadPro2";
		
	}
	
}