package member.controller.bean;



import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

// ������ �ۼ�, ������ ��������

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;

import member.model.dao.MemberDAO;
import member.model.dao.MemberDAOImpl;
import member.model.vo.MemberVO;


@Controller
@RequestMapping("/member/")
public class MemberBean {
	
	@Autowired
	private MemberDAOImpl memberDAO = null;

	@RequestMapping("main.do")
	public String main() {
		
		
		
		return "member/main";
	}
	
	@RequestMapping("signupForm.do")
	
	public String signupForm(){
		
		return "member/signupForm";
	}
	
	@RequestMapping("signupPro.do")
	public String signupPro(MemberVO vo) throws Exception {
			// ȸ�� ����ó��
			// �������� �̵�
		memberDAO.insertMember(vo);
		
		return "member/main";
	}
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		
		System.out.println("loginForm.do");
				
		return "member/loginForm";
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
		
		return "member/loginPro";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
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
		
		return "member/main";
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(String id, Model md, MemberVO vo, HttpServletRequest request) throws Exception {
				
		id = (String)request.getSession().getAttribute("memId");
		
		vo = memberDAO.selectMember(id);
		md.addAttribute("userId", vo.getId());
		md.addAttribute("userPw", vo.getPw());
		md.addAttribute("userName", vo.getName());
		md.addAttribute("userAge", vo.getAge());
		md.addAttribute("userEmail", vo.getEmail());
		
		return "member/modifyForm";
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
	
	@RequestMapping("modifyPro.do")
	public String modifyPro(MemberVO vo, String pwCh, Model md) throws Exception {
		
		int check = 0;
				
		String pw = memberDAO.selectMember(vo.getId()).getPw();	
		if(pw.equals(pwCh)) {
			memberDAO.updateMember(vo);
			check = 1;
		}else {
			check = 0;
		}	
		md.addAttribute("check", check);
		
		return "member/modifyPro";
	}
	
}
