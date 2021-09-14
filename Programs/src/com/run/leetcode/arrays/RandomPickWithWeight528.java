package com.run.leetcode.arrays;

import java.util.Random;

/**
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 *
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 *
 * More formally, the probability of picking index i is w[i] / sum(w).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
 * Example 2:
 *
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 *
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 *
 * Constraints:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Accepted
 */
public class RandomPickWithWeight528 {
    /**8

     Explanation of why prefixSum works:

     Think that if we had an array [1,2,3,4,3]. Normal random pickIndex would pick any index from 0 to 4 with equal probability. But we want that index=1 is picked by 2/13 probability, index=0 with 1/13 probability and so on. (13 is sum of weights). To ensure that one way to think of it if we make a larger array (of size 13) where the values are the indices such that index i is repeated w[i] times then if we do a normal rand on this array then index 0 to 12 will be picked randomly with equal probability. 13 index array -> [0, 1,1, 2,2,2, 3,3,3,3, 4,4,4]. So there is a 3/13 chance of picking 2 as 2 is repeated thrice in the new array.

     Now instead of actually constructing this 13 index array, we just know the range of the index of the 13 index array where value = i. Eg:

     for index=0, range is {0,0}
     index =1, range of indices of the new array is {1,2}
     index=2, range={3,5}
     index=3, range ={6,9}
     index = 4, range = {10,12}

     In other words,

     index=0, range is <1
     index=1, range is <3
     index=2, range is <6
     index = 3, range is < 10
     index = 4, range is < 13

     If you notice the above numbers 1,3,6,10,13 - they are cumulative sum.
     The reason this happens is because for every range: right = left + (w[i] - 1) and left is (prev right+1). So if we substitute 2nd equation into 1st. right = (prev right)+w[i]; i.e. keep adding prev sum to current weight.

     Thus the prefixSum is able to implement this.

     **/
    private int[] prefixSums;
    private int totalSum;
    Random random;
    public RandomPickWithWeight528(int[] w) {
        this.prefixSums = new int[w.length];
        random = new Random();
        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }
    //Linear solution
    public int pickIndexLinear() {
        double target = this.totalSum * Math.random();
        int i = 0;
        // run a linear search to find the target zone
        for (; i < this.prefixSums.length; ++i) {
            if (target < this.prefixSums[i])
                return i;
        }
        // to have a return statement, though this should never happen.
        return i - 1;
    }
    //BInary search solution
    public int pickIndex() {
        //double target = this.totalSum * Math.random(); //This is one option or choose Random class as below
        //random .next int(val) is 0 inclusive and val exclusive. Hence adding 1 helps to exclude 0 and include the last sum value
        int target = random.nextInt(this.totalSum) + 1;

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
/**




 class Solution {
 private int[] prefixSums;
 private int totalSum;

 public Solution(int[] w) {
 this.prefixSums = new int[w.length];

 int prefixSum = 0;
 for (int i = 0; i < w.length; ++i) {
 prefixSum += w[i];
 this.prefixSums[i] = prefixSum;
 }
 this.totalSum = prefixSum;
 }

 public int pickIndex() {
 double target = this.totalSum * Math.random();

 // run a binary search to find the target zone
 int low = 0, high = this.prefixSums.length;
 while (low < high) {
 // better to avoid the overflow
 int mid = low + (high - low) / 2;
 if (target > this.prefixSums[mid])
 low = mid + 1;
 else
 high = mid;
 }
 return low;
 }
 }**/