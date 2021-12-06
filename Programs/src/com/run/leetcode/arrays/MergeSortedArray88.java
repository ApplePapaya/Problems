package com.run.leetcode.arrays;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * Example 2:
 *
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * Example 3:
 *
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 *
 * Constraints:
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 */
public class MergeSortedArray88 {
    /***
     HEre  we need to interwine array 2 into array1. We cant do from the 0 index, since we will have an issue of moving the elements and retaining the existing sorting.

     So we can start from the end. We can compare the values from the end of the arrays and populate it to the end of the actual length of array
     arr1 length = m
     arr2 length = n

     compare values at m-1 of arr1 and n-1 of arr2 and populate it into the array at m + n -1. Since the array has the length to accomodate.

     Keep populating until the arrays are not exhausted. let i and j be the pointers for arr1 and arr2 starting from the end.

     compare and populate the arr1.

     If j is exhausted ( reaches 0 first) first then array is good to be returned.
     else we need to populate the j values to the array
     int i=m-1,j=nums1.length-1,p2=n-1;
     while(p2>=0)
     if(i>=0&&nums1[i]>nums2[p2])
     nums1[j--]=nums1[i--];
     else
     nums1[j--]=nums2[p2--];

     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int k = m + n - 1;

        int i = m - 1;
        int j = n - 1;

        while( j >= 0)
        {
            if(i >= 0 && nums1[i] > nums2[j])
            {
                nums1[k] = nums1[i];
                k--;
                i--;
            }else{
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }


    }
}
