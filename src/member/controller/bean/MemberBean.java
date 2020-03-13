package member.controller.bean;



import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

// 쿼리문 작성, 쿼리문 가져오기

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
			// 회원 가입처리
			// 메인으로 이동
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
				
				// 쿠키의 유효시간을 0으로 설정하여 만료시킨다.
				cookies[i].setMaxAge(0);
				
				// 응답 헤더에 추가한다.
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
