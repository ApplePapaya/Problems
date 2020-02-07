package com.run.leetcode;
//https://medium.com/algorithm-and-datastructure/fixed-point-1befab82e4ae
public class FixedPoint {

	public static void main(String[] args) {
		int[] arr = {-2,1,4,5,6};
		System.out.println(isFixedPoint(arr));

	}
	
	private static int isFixedPoint(int[] arr) {
		int start = 0;
		int end = arr.length-1;
		return binarySearch(arr, start, end);
	}

	private static int binarySearch(int[] arr, int start, int end) {
		if(start <= end) {
			int mid = start + (end - start)/2;
			if(arr[mid] == mid) {
				return mid;
			}else if(arr[mid]> mid)
				return binarySearch(arr, start, mid-1);
			return binarySearch(arr, mid+1, end);
		}
		return -1;
	}

}
