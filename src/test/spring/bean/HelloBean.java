package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// ��Ʈ�ѷ� ������̼� : ��Ʈ�ѷ����� �� Ŭ������ ȣ���� �� �ִ� ���̶�� �ݵ�� ǥ��
@Controller
//@RequestMapping("/test/*")    // url, �ּҿ� context( spring ) �� /test/ �� �߰� �Ȱ�. ������ �ϳ� �� �߰� �� �Ͱ� ����.
public class HelloBean {

	@RequestMapping(value="hello.do", method=RequestMethod.GET) // �޼��� ������ �������� �� // get, post ��� ����������, �������ϸ� �ΰ��� ��� ����
	//@GetMapping("hello.do") // get ��� ��û. ���� ó�� method=RequestMethod.GET ���� �ص� ��. 2���� ����� �ִ�. spring 4.3 ���� ����.
	//@PostMapping("hello.do") // post ����� ���� �Ŀ� ����. ��û��  post �ֱ� ������ ������ ������ �ʿ�
	public String hello() {
			
		return "/WEB-INF/views/spring01/hello.jsp";
	}

	
}
