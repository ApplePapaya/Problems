package com.run.leetcode.arrays;

import java.util.Arrays;

/**
 * There are three stones in different positions on the X-axis. You are given three integers a, b, and c, the positions of the stones.
 *
 * In one move, you pick up a stone at an endpoint (i.e., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints. Formally, let's say the stones are currently at positions x, y, and z with x < y < z. You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
 *
 * The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).
 *
 * Return an integer array answer of length 2 where:
 *
 *     answer[0] is the minimum number of moves you can play, and
 *     answer[1] is the maximum number of moves you can play.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2, c = 5
 * Output: [1,2]
 * Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
 *
 * Example 2:
 *
 * Input: a = 4, b = 3, c = 2
 * Output: [0,0]
 * Explanation: We cannot make any moves.
 *
 * Example 3:
 *
 * Input: a = 3, b = 5, c = 1
 * Output: [1,2]
 * Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
 *
 *
 *
 * Constraints:
 *
 *     1 <= a, b, c <= 100
 *     a, b, and c have different values.
 */
public class MovingStonesUntilConsecutive1033 {

    public int[] numMovesStones(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        int maxCount = nums[2] - nums[0] - 2;// max is making one step move at a time.
        int minCount = 2;// min is moving leftmost to left of mid, and rightmost to righ of mid.SO 2
        // If any 2 values are just apart by 1 we can move the third one in that spot.So only 1 step
        if (nums[2] - nums[1] < 3 || nums[1] - nums[0] < 3) minCount = 1;
        //if the numbers are already consecutive then no steps required.
        if (nums[2] - nums[1] == 1 && nums[1] - nums[0] == 1) minCount = 0;
        return new int[] { minCount, maxCount };
    }
}
