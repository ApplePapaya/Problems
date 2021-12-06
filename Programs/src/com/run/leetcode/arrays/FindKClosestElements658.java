package com.run.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

/***
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class FindKClosestElements658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left = 0;
        int right = arr.length - k;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int target) {
        return generate(arr, k, target, 0, arr.length - k); // Note: high=n-k
    }

    public List<Integer> generate(int[] nums, int k, int target, int low, int high) {
        if (low == high) {
            List<Integer> list = new ArrayList<>();
            for (int i = low; i < low + k; i++) list.add(nums[i]);
            return list;
        }
        int mid = low + (high - low) / 2;
        if (target - nums[mid] <= nums[mid + k] - target) {
            // arr = { 1,5,9 }, k = 2, Target = 5... Here closest to 5 is 1....So move high and output is 1,5
            return generate(nums, k, target, low, mid);
        } /*else if (target - nums[mid] < nums[mid + k] - target) {
      // arr = { 1,5,10 }, k = 2, Target = 5... Here closest to 5 is 1....So move high and output is 1,5
      return generate(nums, k, target, low, mid);
    } */else {
            // arr = { 1,5,8 }, k = 2, Target = 5... Here closest to 5 is 8....So move low and output is 5,8
            return generate(nums, k, target, mid + 1, high);
        }
    }
}
