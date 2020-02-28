package spring.aop.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aop/")
public class AopBean {
	
	@Autowired
	private Tv tv = null;
	
	// 핵심 기능 구현 메서드
	@RequestMapping("main.do")
	public String main() {
		
		System.out.println("메인 메서드 호출");
		
		tv.power();
		tv.channel();
		
		return "aop/aopMember/testAOP";
	}
	
	// ..../aop/main2.do?id=123123123          ==> 변수 설정해서 넣어줌
	@RequestMapping("main2.do")
	public String main2(String id) {
		
		System.out.println("main2 --->" + id);
		
		return "aop/testAOP";
	}
	
	@RequestMapping("main3.do")
	public String main3() {
		
		System.out.println("main3");
		// System.out.println(0/0);		
		// main100();
		return "aop/aopMember/testAOP";
	}
	
	public void main100() {		
		System.out.println("==== main100 ====");		
	}

}
