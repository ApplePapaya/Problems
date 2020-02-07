package com.run.geekForgeeks;
//https://www.geeksforgeeks.org/active-inactive-cells-k-days/
public class ActiveInactiveCells {

	
	
	static void activeInactiveCells() {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  int[] arr = {0, 1, 0, 1, 0, 1, 0, 1};
  System.out.println();
  int[] result = computeActiveInactive(arr, 3);
  for(int i =0;i< result.length;i++) {
	  System.out.print(result[i] + "   ");
  }
	}

	
	static int[] computeActiveInactive(int arr[], int days) {
		int n = arr.length;
		int temp[] = new int[n];
		
		while(days-- >0) {
			temp[0] = 0^ arr[1];
			temp[n-1] = 0 ^ arr[n-2];
			
			for(int i =1;i< n-1;i++) {
				temp[i] = arr[i-1] ^ arr[i+1];
			}
			
			for(int i =0;i< n;i++) {
				arr[i]= temp[i];
			}
		}
		
		return arr;
	}
	
	 static int gcd(int a, int b) 
	    { 
		 System.out.println("a :" +a + "  b :"+b);
	        if (a == 0) 
	            return b; 
	        return gcd(b % a, a); 
	    } 
}
