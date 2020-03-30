package com.run.leetcode.medium;

public class KthLargestElementInArray {
	int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KthLargestElementInArray ko = new KthLargestElementInArray();
		int ans = ko.findKthLargest(new int[] {9,8,7,6,5,4,3,2,1}, 3);
		System.out.println(ans);
		System.out.println(ko.count);
	}
	
	
	    public int findKthLargest(int[] nums, int k) {
	        int start = 0, end = nums.length - 1, index = nums.length - k;
	        while (start < end) {
	            int pivot = partion(nums, start, end);
	            if (pivot < index) start = pivot + 1; 
	            else if (pivot > index) end = pivot - 1;
	            else return nums[pivot];
	        }
	        return nums[start];
	    }
	    
	    private int partion(int[] nums, int start, int end) {
	        int pivot = start, temp;
	        while (start <= end) {
	        	count++;
	            while (start <= end && nums[start] <= nums[pivot]) start++;
	            while (start <= end && nums[end] > nums[pivot]) end--;
	            if (start > end) break;
	            temp = nums[start];
	            nums[start] = nums[end];
	            nums[end] = temp;
	        }
	        temp = nums[end];
	        nums[end] = nums[pivot];
	        nums[pivot] = temp;
	        return end;
	    }
	}


