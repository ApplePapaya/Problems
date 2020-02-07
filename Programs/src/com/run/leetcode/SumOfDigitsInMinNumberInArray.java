package com.run.leetcode;
//https://bloggie.io/@rugved/leetcode-1085-sum-of-digits-in-the-minimum-number-java-solution
public class SumOfDigitsInMinNumberInArray {

	public static void main(String[] args) {
		int[] arr = {34,23,1,24,75,33,54,8};
		System.out.println(checkEvenOddOfSumOfDigitsInMinNum(arr));
		
		int[] arr2 = {99,77,33,66,55};
		System.out.println(checkEvenOddOfSumOfDigitsInMinNum(arr2));

	}

	public static int checkEvenOddOfSumOfDigitsInMinNum(int[] arr) {
		int n = arr.length;
		int min = Integer.MAX_VALUE;
		for(int i=0,j=n-1;i<j;i++,j--) {
			if(arr[i]<min) {
				min = arr[i];
			}
			if(arr[j]<min) {
				min = arr[j];
			}
		}
		int sum =0;
		//Sum of digits
		while(min>0) {
			int x = min %10;
			sum += x;
			min = min/10;
		}
		if(sum %2 ==0)
			return 1;
		else 
			return 0;
	}
}
