package com.run.leetcode.arrays;

/**
 * 670. Maximum Swap
 * Medium
 *
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 *
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 *
 *
 * Constraints:
 *
 *     0 <= num <= 108
 */
public class MaximumSwap670 {
    /**
     * Below is using bucket sort. Convert the input number to String to char array.
     * Char - '0' gives the int value of the character.
     * Steps for below
     * 1> Convert the integer to Char array.
     * 2> Create a int array with length 10 - ( 0 - 9)
     * 3> Get the max index occurence of each number.
     * 4> Get the max int value in the number.
     * 5> Iterate through the integer from left to right MSD to LSD and for each digits, if there is a number greater than that to the right
     * swap it with that. We chose the greatest number from the right side and swap witht he MSD>
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int max = digits[0] -'0';
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
            max = Math.max(max, digits[i] - '0');
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = max; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;
    }
}
