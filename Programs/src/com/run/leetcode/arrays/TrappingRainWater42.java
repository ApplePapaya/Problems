package com.run.leetcode.arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingRainWater42 {


    /**
     Have 2 pointers left and right.
     At every point the min height value between these 2 is to be considered until another bigger heigt comes.

     So logic goes as below
     while(left < right)
     is the height at left <= height at right
     yes - then as long as the height is decreasing towareds right compute the result
     as summing up fo the difference in current height and height at left pointer                         in the beginning
     No - then as long as the height is decreasing towards left compute the result
     as summing up the the difference in current height and at initial right pointer in the

     **/
    public int trap(int[] height) {
        int result = 0;
        int start = 0;
        int end = height.length - 1;

        while(start < end)
        {
            if(height[start] <= height[end])
            {
                int current = height[start];
                while(current > height[++start])
                {
                    result += current - height[start];
                }
            }else
            {
                int current = height[end];
                while(current > height[--end])
                {
                    result += current - height[end];
                }
            }
        }
        return result;
    }
}
