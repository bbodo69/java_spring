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
			// 회원 가입처리
			// 메인으로 이동
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
				
				// 쿠키의 유효시간을 0으로 설정하여 만료시킨다.
				cookies[i].setMaxAge(0);
				
				// 응답 헤더에 추가한다.
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
//	@ResponseBody // 문자열로리턴하게 나옴, body 에 응답하기 때문에 ajax 와 같이 많이 사용됨
//	public String ajaxIdAvail(String id) throws Exception {
	public ResponseEntity<String> ajaxIdAvail(String id) throws Exception{
		String result = "";
//		System.out.println("ajax!!");
//		System.out.println(id);
		int check = memberDAO.idAvailCheck(id);
		
		// 리턴 받은게 0 이어야 사용가능
		if(check ==1) {
			result ="이미 사용되는 아이디";
		}else if(check ==0) {
			result="사용가능한 아이디";
		}
		System.out.println(result);
		HttpHeaders responseHeaders = new HttpHeaders();	// 헤더 객체를 만들어
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");	// 헤더 정보를 추가
		
		//return result;
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
		// body 결과, 헤더정보, 상태정보
	}

}
