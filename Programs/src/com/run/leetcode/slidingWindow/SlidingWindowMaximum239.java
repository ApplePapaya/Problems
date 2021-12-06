package com.run.leetcode.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 *
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum239 {
    /**
     This we can solve using brute force but it will not be linear time.

     Ideal solution is using deque. This is useful as it is double ended queue.
     To solve this
     1> Check if the array is null or empty. If yes return new int[0]
     2> Initialize result array of length n - k + 1 -- Say n = 10 and k = 3, there are 8 windows of k length. since the last 3 also is a window and cannot be subtracted completely
     3> Initialize deque datastructure using linkedlist to hold the indices for max values in the window
     4> Loop through each element in the array and do the below
     A> Is the difference of the index at first and current element greater than k? If yes pop the first index.
     B> while deque is not empty && the current element value is greater than the element at the index value at the end of the queue. If yes keep popping out elements from the LAST
     C> offer the index to the deque
     D> if the current index + 1 is greater than or equal to k
     then start populating the result matrix.
     as result[i + 1 - k] = nums[ deque.peek()]
     We will not pop it since it may be required for the next window as well.


     **/

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[0];

        int n = nums.length;

        int[] result = new int[n - k + 1];// since n is 0 based and we cant dispose a whole window length since we need to pick one element from that as well.

        Deque<Integer> deque = new LinkedList<Integer>();// Thsi si to hold the indices

        for(int i = 0; i < n; i++)
        {
            //A
            while(deque.isEmpty() == false && deque.peekFirst() == i - k)
                deque.removeFirst();

            while(deque.isEmpty() == false && nums[deque.peekLast()] < nums[i])
                deque.removeLast();

            deque.offerLast(i);

            if(i + 1 >= k)// i + 1 indicates the number of elements traverssed. if thats equal to k then we shd have the max value at the start of the peek
                result[i + 1 - k] = nums[deque.peekFirst()];
        }
        return result;

    }
}
