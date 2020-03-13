package test20200228;

import java.util.List;

public class Programerse {

	public int [] solution(int n) {
        
        int [] size = new int [n];
        
        for(int i = 0 ; i < n ; i++){
            size[i] = i+1;    
        }
        
        return size;
    }
	
	public static void main(String[] args) {

		Programerse pro = new Programerse();
		
		int [] size = pro.solution(5);
		
		System.out.println(size);
		
		for(int i = 0 ; i < size.length ; i ++) {
			System.out.println(size[i]);
		}
		
	}

}
