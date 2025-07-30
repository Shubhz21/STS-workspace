package com.mypkg;

public class FourthH {
	
	public static void main(String []a) {
		
		int arr[]= {50,10,02,20,40,30};
		
		int first,second,third,fourth;
		
		first=Integer.MIN_VALUE;
		second=Integer.MIN_VALUE;
		third=Integer.MIN_VALUE;
		fourth=Integer.MIN_VALUE;
		
		
		for(int num : arr) {
			
			if(num > first) {
				fourth=third;
				third=second;
				second=first;
				first=num;
			}
			else if(num > second && num != first) {
				fourth=third;
				third=second;
				second=num;
				
			}
			else if(num > third && num != first && num != second) {
				fourth=third;
				third=num;
			}
			else if(num > fourth && num!= first && num != second && num != third) {
				fourth=num;
			}
		}
		
		if(fourth==Integer.MIN_VALUE) {
			System.out.println("there is no fourth num present in this arr");
		}
		else {
			System.out.println("fourth highest num is:"+fourth);
		}
		
		
	}

}
