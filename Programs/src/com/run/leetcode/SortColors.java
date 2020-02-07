package com.run.leetcode;
/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
96.1 Java Solution - Counting Sort
We can get the count of each element and project them to the original array.
 * @author Home-Laptop
 *
 */
public class SortColors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void sortColors(int[] arr) {
		if(arr ==null || arr.length <2) {
			return;
		}
		int countArray[] = new int[3];
		for(int i=0;i<arr.length;i++) {
			countArray[arr[i]]++;
		}
		
		int j =0;
		int k=0;
		while(j<=2) {
			if(countArray[j] >0) {
				arr[k++] = j;
				countArray[j]--;
			}else {
				j++;
			}
		}
		
	}

}
