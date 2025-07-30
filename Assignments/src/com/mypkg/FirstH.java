package com.mypkg;

public class FirstH {
	
	public void  pttrn() {
		int i,j;
		
		for(i=1; i<6; i++) {
			for(j=1; j<=i; j++) {
				if(i%2==0) System.out.print(j);
				
				else System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void firstH() {
		
int [] arr= {10,02,20,40,30};
		
		int first=Integer.MIN_VALUE;
		
		for(int num : arr) {
			if(num > first) {
				first=num;
			}
		}
		if(first == Integer.MIN_VALUE) {
			
			System.out.println("there is no first highest");
		}
		else {
			System.out.println("first highest num is: "+first);
		}
		
	}
	
	public void secondH() {
		
		int []arr= {10,02,20,40,30};
		
		int first,second;
		
		first=Integer.MIN_VALUE;
		second=Integer.MIN_VALUE;
		
		for(int num : arr) {
			
			if(num > first) {
				second=first;
				first=num;
			}
			else if(num > second && num != first) {
				second=num;
			}
		}
		
		if(second == Integer.MIN_VALUE) {
			System.out.println("there is no second highest number in this: ");
		}else {
			System.out.println("second highest number is: "+second);
		}
		
	}
	
	
	public void thirdH() {
		
		int []arr= {10,02,20,40,30,49};
		
		int first=Integer.MIN_VALUE,second=Integer.MIN_VALUE,third=Integer.MIN_VALUE;
		
		for(int num : arr) {
			if(num > first) {
				third=second;
				second=first;
				first=num;
			}
			else if(num > second && num != first) {
				third=second;
				second= num;
			}
			else if(num > third && num != first && num != second) {
				
				third=num;
				
			}
		}
		
		if(third == Integer.MIN_VALUE) {
			System.out.println("there is no third highest number in this array");
		}
		else {
			System.out.println("third highest value is: "+third);
		}
		
	}
	
	public static void  main(String []a) {
		
		FirstH obj = new FirstH();
		
		obj.pttrn();
		obj.firstH();
		obj.secondH();
		obj.thirdH();
		
		
		
	}

}
