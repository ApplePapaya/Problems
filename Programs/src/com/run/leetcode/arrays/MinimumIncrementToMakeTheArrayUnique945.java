package com.run.leetcode.arrays;

import java.util.Arrays;

/**
 * 945. Minimum Increment to Make Array Unique
 * Medium
 *
 * Given an array of integers nums, a move consists of choosing any nums[i], and incrementing it by 1.
 *
 * Return the least number of moves to make every value in nums unique.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 *
 *
 *
 * Note:
 *
 *     0 <= nums.length <= 40000
 *     0 <= nums[i] < 40000
 */
public class MinimumIncrementToMakeTheArrayUnique945 {

    public static void main(String[] args) {
       // int[] input = {3,2,1,2,1,7};
        int[] input = {3,6,8,9};
        System.out.println(minIncrementForUnique(input));
    }
    public static int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0, need = 0;
        for (int a : A) {
            res += Math.max(need - a, 0);
            need = Math.max(a, need) + 1;
        }
        return res;
    }

}
