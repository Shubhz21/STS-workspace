package com.mypkg;

public class SecondH {
	
	public static void main(String []a) {
		
		int arr[]= {50,10,02,20,45,30};
		
		int first,second;
		
		first=Integer.MIN_VALUE;
		second=Integer.MIN_VALUE;
		
		for(int num : arr) {
			
			if(num > first) {
				second= first;
				first= num;
				
			}else if(num > second && num != first) {
				second=num;
			}
		}
		if(second==Integer.MIN_VALUE) {
			System.out.println("there is no second highest num is present in this array");
		}else {
			System.out.println("second highest number is:"+second);
		}
	}

}
