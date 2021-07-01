package com.run.leetcode.slidingWindow;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 */
public class MaxConsecutiveOnesIII1004 {
    /**

     Intuition

     To find the longest subarray with contiguous 1's we might need to find all the subarrays first. But do we really need to do that? If we find all the subarrays we are essentially finding out so many unnecessary overlapping subarrays too.

     We can use a simple sliding window approach to solve this problem.

     In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current window and then we have the left pointer whose job is to contract a given window. At any point in time only one of these pointers move and the other one remains fixed.

     The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has reached the limit of 0's allowed, we contract (if possible) and save the longest window till now.

     The answer is the longest desirable window.

     Algorithm

     Initialize two pointers. The two pointers help us to mark the left and right end of the window/subarray with contiguous 1's.

     left = 0, right = 0, window_size = 0

     We use the right pointer to expand the window until the window/subarray is desirable. i.e. number of 0's in the window are in the allowed range of [0, K].

     Once we have a window which has more than the allowed number of 0's, we can move the left pointer ahead one by one until we encounter 0 on the left too. This step ensures we are throwing out the extra zero.
     Note: As suggested in the discussion forum. We can solve this problem a little efficiently. Since we have to find the MAXIMUM window, we never reduce the size of the window. We either increase the size of the window or remain same but never reduce the size.
     Observe we don't contract the window if it's not needed and thus save on some computation.



     **/

    public int longestOnes(int[] A, int K) {
        //1:10
        int N = A.length;
        int left = 0;
        for (int right=0; right<N; right++) {
            K -= 1 - A[right];
            if (K < 0)
                K += 1 - A[left++];
        }
        return A.length - left;
    }
    //2ms
    public int longestOnes2ms(int[] A, int K) {
        int left = 0, right;
        for (right = 0; right < A.length; right++) {
            // If we included a zero in the window we reduce the value of K.
            // Since K is the maximum zeros allowed in a window.
            if (A[right] == 0) K--;
            // A negative K denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
            if (K < 0) {
                // If the left element to be thrown out is zero we increase K.
                if (A[left] == 0) K++;
                left++;
            }
        }
        return right - left;
    }

    public static int longestOnes2msWithextraconstantspace(int[] A, int K) {
        int i = 0;
        int j = 0;
        int ans = 0;
        while (j < A.length) {
            if (A[j] == 1 || A[j] == 0 && K > 0) {
                if (A[j] == 0) K--;
                j++;
            } else {
                ans = Math.max(ans, j - i);
                while (K == 0) if (A[i++] == 0) K++;
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}
