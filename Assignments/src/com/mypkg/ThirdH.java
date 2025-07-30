package com.mypkg;

public class ThirdH {
	
	public static void main(String[]a) {
		
		int arr[]= {50,10,02,20,40,30};
		
		int first,second,third;
		
		first=Integer.MIN_VALUE;
		second=Integer.MIN_VALUE;
		third=Integer.MIN_VALUE;
		
		for(int num : arr) {
			
			if(num > first) {
				third=second;
				second=first;
				first=num;
			}
			else if(num > second && num != first) {
				third=second;
				second=num;
				
				
			}
			else if(num > third && num != first && num !=second) {
				third=num;
			}
		}
		if(third == Integer.MIN_VALUE) {
			System.out.println("there no third highest number here");
		}
		else {
			System.out.println("Third highest number is : "+third);
		}
	}

}
