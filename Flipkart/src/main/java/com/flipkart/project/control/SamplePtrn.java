package com.flipkart.project.control;

public class SamplePtrn {
	
	
	static {
		
		int row, col, v=3;
		
		
		for(row=1; row<5; row++) {
			
			for(col=1; col<8; col++) {
				
				if(col<=4+v && col>=4-v) {
					
					System.out.print("*");
					
				}else {
					System.out.print(" ");
				}
				
				
			}
			
			System.out.println();
			
			v--;
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
	}

}
