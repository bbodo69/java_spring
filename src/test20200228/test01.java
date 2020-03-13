package test20200228;

public class test01 {
	
	public int mySum(int a, int b) {
				
		int c = a + b ;		
		return c;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("a출력");
		
		test01 test = new test01();
		
		System.out.println("ㅠ출력");
		System.out.println(test.mySum(5, 3));
		
		
		

	}

}
