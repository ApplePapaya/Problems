package com.run.leetcode.binarySearch;

public class FindPeakElement162 {

    /**
     *
     * A peak element is an element that is strictly greater than its neighbors.
     *
     * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
     *
     * You may imagine that nums[-1] = nums[n] = -?.
     *
     * You must write an algorithm that runs in O(log n) time.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index number 2.
     * Example 2:
     *
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 5
     * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -231 <= nums[i] <= 231 - 1
     * nums[i] != nums[i + 1] for all valid i.
     Here we need to find any peak in the array as long as its neigbhours are lesser than that.
     If the array is ascending, the last element is the peak and if descending then first element is the array
     Else it will be the last element in the array.

     We can use binary search due to the fact that any element whcih is peak can be returned.

     You may imagine that nums[-1] = nums[n] = -?

     This means a peak will always exist. ex: 3 is peak in following two arrays:
     -? | 0,1,2,3|-?
     -? | 3,2,1,0|-?

     So all one need to do is find the end of any increasing slope in the input array
     **/
    public int findPeakElementIterative(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }


    // Recursive solution

    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    //Linear solution

    public int findPeakElementLinear(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}
