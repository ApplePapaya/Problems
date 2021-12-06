package com.run.leetcode.arrays;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class KthLargestElementInArray215 {
	int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KthLargestElementInArray215 ko = new KthLargestElementInArray215();
		int ans = ko.findKthLargest(new int[] {9,8,7,6,5,4,3,2,1}, 3);
		System.out.println(ans);
		System.out.println(ko.count);
	}

	/**



	 Time complexity : O(N)\mathcal{O}(N)O(N) in the average case, O(N2)\mathcal{O}(N^2)O(N2) in the worst case.
	 Space complexity : O(1)\mathcal{O}(1)O(1).

	 **/
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
	        	//count++;
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


