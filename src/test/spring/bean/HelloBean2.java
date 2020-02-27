package test.spring.bean;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.spring.model.testDTO;
import test.spring.model.tvDTO;



@Controller
public class HelloBean2 {
	
	// ������ �ڵ� ����
	@Autowired
	private Date day = null;
	
	/*
	  	���� Ÿ���� �� �ΰ��� ������ �����̸��� �°� ���Ե�.
	  	�ڵ� ������ ���Եɶ�, �⺻������ "Ŭ���� Ÿ������ ����" �ؼ� �������µ� 
	  	(bean�� id �Ӽ����� �̰��� �����̸��� �޶� �������)
	  	���� ���� Ÿ���� bean ��ü�� �������� �ִٸ� bean�� id �Ӽ����� ��������
	  	������ ���� ��������.
	 
	 */
	
	@Autowired
	private testDTO dto = null;
	
	@Autowired
	private testDTO dto2 = null;
	
	@RequestMapping("hello2.do")	
	public String hello2() {
		
		System.out.println(day);
		System.out.println("dto = " + dto.getId());
		System.out.println("dto = " + dto.getReg());
		System.out.println("dto = " + dto.getPw());
		
		System.out.println("dto2 = " + dto2.getId());
		System.out.println("dto2 = " + dto2.getReg());
		System.out.println("dto2 = " + dto2.getPw());
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	
	

	@RequestMapping("form.do")
	
	public String form() {
		// form.do�� ��û�ϸ� ��������� jsp(view) ��� ����		
		return "/WEB-INF/views/spring02/form.jsp";
		
	}
	
	@RequestMapping("pro.do")
	
	public ModelAndView Pro(testDTO dto) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", dto.getId());
		mv.addObject("pw", dto.getPw());
		mv.setViewName("/WEB-INF/views/spring02/pro.jsp");
		
		return mv;
	}
	
//	public String pro(testDTO dto, Model model) {
//			// �Ű����� �����ؼ� �����Ѿ���� ������ �ޱ�.
//			//System.out.println(id);
//			//System.out.println(pw);
//			System.out.println(dto.getId());
//			System.out.println(dto.getPw());
////			System.out.println(request);
////			System.out.println(response);
////			System.out.println(session);
////			System.out.println(request.getRequestURI());
//			
////			request.setAttribute("id", dto.getId());
////			request.setAttribute("pw", dto.getPw());
//			
//			model.addAttribute("id", dto.getId());
//			model.addAttribute("pw", dto.getPw());
//			
//			
//		return "/WEB-INF/views/spring02/pro.jsp";
//	}
	
	@RequestMapping("hello3.do")
	//public String hello3(String test) { //�Ű������� ��û�Ķ���͸� �����ϰ�, get ������� test ������ �� �־��༭ url ���� 
	public String hello3(@RequestParam("test") String tost) { // �Ű����� �̸� ����ǵ� ������
		
		System.out.println(tost);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}

	
	// �Ϲ� �޼��� @ModelAttribute ������̼� �߰�
	@ModelAttribute // ��� �信 ��½�����
	//@modelAttribute("tvInfo");  //��Ī �߰�
	public tvDTO getTv(String col) {
		System.out.println("getTv ȣ��");
		tvDTO tv = new tvDTO();
		tv.setPower(true);
		tv.setCh(10);
		tv.setCol(col);
		
		return tv;
	}

	// ���� �޼���
	@RequestMapping("hello4.do")
	public String hello4() {
		
		System.out.println("hello4 ���� �ż��� ȣ��");
			
		return "/WEB-INF/views/spring02/helloTv.jsp"; 
	}
	
	// �޼��忡 @ModelAttribute ����
	@RequestMapping("form2.do")
	public String sendMsg() {
		return "/WEB-INF/views/spring02/form.jsp";
	}
	@RequestMapping("pro2.do")
	public String viewMsg(@ModelAttribute("dto")testDTO dto) {
		// �Ű����� testDTO dto ��� �����ϸ� set�޼���� �ڵ� ���ε���
		// ������̼� ȿ���� dto��� ��Ī���� Model�� ���� -> view ���� ����������.
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
				
		return "/WEB-INF/views/spring02/pro2.jsp";
	}
	
	@RequestMapping("hello5.do") // Ajax �� �����ؼ� ���. Ajax�� �����͸� �ҷ��鿩�� �����͸� �̿��Ѵ�.
	@ResponseBody
	public String hello5() {
		return "hello5";
	}
	
	// RequestMapping �ɼǵ�
	// value=�ּ� method=���۹��, params=�Ķ����
	@RequestMapping(value="hello6.do", params="id=java") // id ���� java ���� �Ѵ�.
	public String hello6(String id, String pw, Model md) {
		
		System.out.println(id);
		System.out.println(pw);
		md.addAttribute("id", id);
		md.addAttribute("pw", pw);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	
	@RequestMapping(value="hello7.do", params= {"id=java", "pw=1234"}) // id, pw ���� java, 1234 ���� �Ѵ�.
	public String hello7(String id, String pw, Model md) {
			
		System.out.println(id);
		System.out.println(pw);
		md.addAttribute("id", id);
		md.addAttribute("pw", pw);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	@RequestMapping(value="hello8.do", params= {"id=java", "!pw"}) // id ���� java pw �Ķ���ʹ� ����� �Ѵ�.
	public String hello8(String id, String pw, Model md) {
			
		System.out.println(id);
		System.out.println(pw);
		md.addAttribute("id", id);
		md.addAttribute("pw", pw);
			
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	
	// value : �Ķ���͸�     required : �Ķ���� �ʼ� ���� ����     defaultValue : �⺻��
	@RequestMapping("hello9.do")
	public String hello9(@RequestParam() String msg) { // msg �� �ʿ��ϴ�
		
			
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	@RequestMapping("hello10.do")
	public String hello10(@RequestParam("id") String msg) { // id �� �ʿ��ϴ�

			
		return "/WEB-INF/views/spring01/hello.jsp";
	}	
	
	@RequestMapping("hello11.do")
	public String hello11(@RequestParam(value="msg", defaultValue="hello") String msg){ 
		//value�� �⺻���� ������
			
		System.out.println(msg);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}		
	
	@RequestMapping("hello12.do")
	public String hello12(@RequestParam(value="msg", required=false) String msg){
		// msg �� ���� �� �־��־ ������ ������
			
		System.out.println(msg);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}	

	@RequestMapping("hello13.do")	// �ڵ��α���, ��ȿ�� �˻� ���ִ� ���
	public String hello13(	
			@RequestParam(value="id", required=true) String id,
			@RequestParam(value="pw", required=true) String pw,
			@RequestParam(value="auto", required=false, defaultValue="0")String auto
			){

					
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}		
}
