package spring.aop.advice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.dao.MemberDAOImpl;
import member.model.vo.MemberVO;

@Controller
@RequestMapping("/aopMember/")
public class AopMemberBean {
	
	@Autowired
	private MemberDAOImpl memberDAO = null;
	
	@RequestMapping("testAop.do")
	public String testAop() {
		System.out.println("testAop");
		return "aopMember/main";
	}
	
	@RequestMapping("main.do")
	public String main() {
		return "aopMember/main";
	}
	
@RequestMapping("signupForm.do")
	
	public String signupForm(){
		
		return "aopMember/signupForm";
	}
	
	@RequestMapping("signupPro.do")
	public String signupPro(MemberVO vo) throws Exception {
			// ȸ�� ����ó��
			// �������� �̵�
		memberDAO.insertMember(vo);
		
		return "aopMember/main";
	}
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		
		System.out.println("loginForm.do");
				
		return "aopMember/loginForm";
	}
	
	@RequestMapping("loginPro.do")
	public String loginPro(HttpSession session, MemberVO vo, String auto, Model md) throws Exception {
		
		int check = memberDAO.idPwCheck(vo);
		if(check == 1) {
			session.setAttribute("memId", vo.getId());
		}
		md.addAttribute("check", check);
		
		System.out.println("page = loginPro");
		System.out.println("check = " + check);
		
		return "aopMember/loginPro";
	}
	
	@RequestMapping("logout.do")
	public String testLogout(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("logout");
		
		request.getSession().invalidate();
		
		Cookie[] cookies = request.getCookies();
		
		System.out.println(cookies);
		
		if(cookies != null) {
			for(int i = 0 ; i < cookies.length; i++) {
				
				// ��Ű�� ��ȿ�ð��� 0���� �����Ͽ� �����Ų��.
				cookies[i].setMaxAge(0);
				
				// ���� ����� �߰��Ѵ�.
				response.addCookie(cookies[i]);
			}
		}
		
		return "aopMember/main";
	}
	
	@RequestMapping("modifyForm.do")
	public String testModifyForm(Model md, MemberVO vo, HttpSession session, HttpServletRequest request) throws Exception {
				
		String id = (String)session.getAttribute("memId");		
		vo = memberDAO.selectMember(id);
		md.addAttribute("userId", vo.getId());
		md.addAttribute("userPw", vo.getPw());
		md.addAttribute("userName", vo.getName());
		md.addAttribute("userAge", vo.getAge());
		md.addAttribute("userEmail", vo.getEmail());
		
		return "aopMember/modifyForm";
	}
	
	@RequestMapping("modifyPro.do")
	public String testModifyPro(MemberVO vo) throws Exception {
		System.out.println("Bean");
		memberDAO.updateMember(vo);
		
		
		return "aopMember/main";
	}
	
	@RequestMapping("ajaxIdAvail.do")
//	@ResponseBody // ���ڿ��θ����ϰ� ����, body �� �����ϱ� ������ ajax �� ���� ���� ����
//	public String ajaxIdAvail(String id) throws Exception {
	public ResponseEntity<String> ajaxIdAvail(String id) throws Exception{
		String result = "";
//		System.out.println("ajax!!");
//		System.out.println(id);
		int check = memberDAO.idAvailCheck(id);
		
		// ���� ������ 0 �̾�� ��밡��
		if(check ==1) {
			result ="�̹� ���Ǵ� ���̵�";
		}else if(check ==0) {
			result="��밡���� ���̵�";
		}
		System.out.println(result);
		HttpHeaders responseHeaders = new HttpHeaders();	// ��� ��ü�� �����
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");	// ��� ������ �߰�
		
		//return result;
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
		// body ���, �������, ��������
	}

}
