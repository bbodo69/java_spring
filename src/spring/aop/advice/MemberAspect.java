package spring.aop.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {

	
//	// pointcut & advice method
//	@Pointcut("execution(* test*(..))")
//	private void testPC() {
//				
//	}
//	
//	@Around("testPC()")
//	public Object around(ProceedingJoinPoint j) throws Throwable {
//		// ����Ÿ�� Object, ������ j.proceed(), ù��° �Ű����� ProceedingJoinPoint Ÿ�� ����.
//		System.out.println("around aop");
//		return j.proceed();
//	}
	
	// advice method�� ()�ȿ��� pointcut ������̼��� ����� �޼���� �Ǵ� pointcut ǥ������ �ü� �ִ�.
	@Around("execution(* test*(..))")
	public Object around(ProceedingJoinPoint j) throws Throwable{
		System.out.println("around");
		
	
	/*	
		// �α��� üũ
		// ProceedingJoinPoint �� HttpServletRequest ������
		// Ÿ�ٸ޼����� �Ű��������� HttpServletRequest request �� �����ؾ���.
		// ����Ÿ�� �����ؾ��ϴµ� ���� Ÿ�ٸ޼����� ����Ÿ���� String
		Object [] obj = j.getArgs(); 	// Ÿ�� �޼���� �Ѿ�� �Ű����� ������.
		for(Object o : obj) {
			if(o instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest)o;
				HttpSession session = request.getSession();
				String memId = (String)session.getAttribute("memId");
				if(memId == null) {
					//�α׾ƿ� ����
					System.out.println("�α��� �ʿ�");
					return "aopMember/loginForm"; // �ٷ� �α��� ������ �̵�
					// �������°� Ÿ�ٸ޼���(�ٽɸ޼���) ����Ÿ�԰� �����ϰ� ó������.
				}
			}
		}
		
		
	*/
		
		// �α��� üũ : MVC ���� HttpServletRequest ������
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes)ra;
		HttpServletRequest request = sa.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("memId") == null) {
			System.out.println("�α����ϼ���");
			return "aopMember/loginForm";
		}
		
		
		return j.proceed();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
