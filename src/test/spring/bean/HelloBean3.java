package test.spring.bean;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloBean3 {

	@RequestMapping("abcd.do")
	public String abc(String name, Model md) {
		System.out.println(name);
		md.addAttribute("name", name);
		
		return "spring03/abcd" ; 
	}
	
	@RequestMapping("download.do")
	public ModelAndView down() {
		//�����ڰ� ���ϴ� �ٿ�ް� �� ������ ����.
		File f = new File("D:\\save\\2.png");
		
		
		
		ModelAndView mv = new ModelAndView("fileDown", "downloadFile", f);
		// fileDown �� �߰����� bean ��ü�� ID ��.
		// ������ �̵��� �ƴ϶� DownLoadView�� �����ϰڴ��ؼ� ������ ����.
		// downloadFile : �Ķ���͸� ����
		// f : �ٿ�޾ư��� �� ������
		
		return mv;
	}
	
}
