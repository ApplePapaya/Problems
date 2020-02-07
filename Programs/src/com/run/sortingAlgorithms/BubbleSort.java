package com.run.sortingAlgorithms;

import java.util.Arrays;
// In place stable algo
// Best case O(n)  when array is sorted. Else O(n^2 ) when reverse sorted.	
public class BubbleSort {

	public static void main(String[] args) {
	int[] a = {9,8,7,6,5,4,3,2,1,0};
	bubblesort(a);
	int[] ab = {1,2,3,4,5,6,7,8,9};
	bubblesort(ab);

	}
	
	public static void bubblesort(int[] a) {
		
		int n = a.length;
		System.out.println("Original : "+ Arrays.toString(a));
		for(int i =0;i<n ;i ++) {
			boolean isSwapped = false;
			for(int j =0;j< n-i-1;j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
					isSwapped = true;
				}
			}
			System.out.println("Sorting : "+ Arrays.toString(a));
			if(!isSwapped) {
				break;
			}
		}
		System.out.println("Sorted : "+ Arrays.toString(a));
	}

}
