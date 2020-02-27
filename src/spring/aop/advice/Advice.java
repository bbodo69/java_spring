package spring.aop.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Advice {

	// Joinpoint : ��� ��ü �� ȣ��Ǵ� �޼��忡���� ���� �Ǵ� ���޵Ǵ� �Ķ���Ϳ� ���� ������ �ʿ��Ͽ� �ۼ�.
	// * ���������� aop�� ������ �޴� ���� ó���� �Ұ���, ���� ��� �α��� üũ�ϴ� �� ������ ��ȿ��
	// �˻������ ����� ������.
	public void before(JoinPoint j) {
		System.out.println("before �߻�!!");
		System.out.println("=====" + j.getTarget()); // Ÿ���� � ��ü���� ���
		
		// AOP���� MVC request ��ü �����
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes)ra;
		HttpServletRequest request = sa.getRequest();
		HttpSession session = request.getSession();
	/*
	  	RequestContextHolder
	  	controller, service, DAO �� ��������
	  	HttpServletRequest�� ������ �� �ֵ��� �����ִ� Ŭ����
	  	
	  	#HttpServletRequest
	  	��û���� HTTP URI, HTTP method, HTTP body ���� ����Ҽ� �ְ�
	  	HTTP header ���� cookie �� Ȯ�� �� �� �ִ�.
	  	
	  	# session�� ���� ���ǻ���
	  	
	  
	 */
		
	}
	
	public void after() {
		System.out.println("after �߻�!!!");
	}
	
	public void afterReturning() {
		System.out.println("afterReturning �߻�!!!");
	}
	
	public void afterThrowing() {
		System.out.println("afterThrowing �߻�!!!");
	}
	
/*
	  around advice �޼��� ���� ��� !
	  org.aspectj.lang.ProceedingJoinpoint Ÿ���� �Ű������� ù��°�� �����������.
  	     �׷��� ����� �ʱ�ȭ �������� �μʼ� �߻�
  	     ����Ÿ���� Object Ÿ������ ����.
  	  j.proceed() ȣ��� �����ִ� ��ü�� Ÿ����  Object�̸�, �� ��ü�� ���������
  	     ���ϴ� ���������� �� ó���� �ȴ�.  	     	      
*/
	
	public Object around(ProceedingJoinPoint j) throws Throwable {
		
		// around�� ���� ������ �����ϴ°��� �ƴ϶�, ������ �������� ���̰� ������
		// �����������
		// proceed() �޼���� �ٽɸ޼��� ��, ���� ȣ��Ǿ���ϴ¸޼���(main*) �� ȣ�����ش�.
		
		System.out.println("around before!!!");		
		Object obj = j.proceed();
		System.out.println("around after!!!");
		return obj;
		
	}
}
