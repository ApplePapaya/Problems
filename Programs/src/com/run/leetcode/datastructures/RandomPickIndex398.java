package com.run.leetcode.datastructures;

import java.util.Random;

/**
 * 398. Random Pick Index
 * Medium
 *
 * 705
 *
 * 880
 *
 * Add to List
 *
 * Share
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * Output
 * [null, 4, 0, 2]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * target is an integer from nums.
 * At most 104 calls will be made to pick.
 */
public class RandomPickIndex398 {

    //Reservoir sampling methodm https://www.youtube.com/watch?v=A1iwzSew5QY
    int[] nums;
    Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int idx = 0;

        for(int i = 0; i < nums.length; i++) {

            // only if equal to target it is a potential candidate
            if(nums[i] != target) {
                continue;
            }

            // reservoir sampling with 1/count
            int sample = rand.nextInt(++count);
            System.out.println(sample);
            if(sample == 0) {// when there is only 1 item the chances of it getting picked is 100%.
                //So we will always have a value fo idx, the first occurence of the target.
                //Subsequently the probability is 1/number of times it has occured
                idx = i;
            }
        }
        return idx;
    }



    /***
     // Hashmap caching method. takes 67 ms
     private HashMap<Integer, List<Integer>> indices;
     private Random rand;

     public Solution(int[] nums) {
     this.rand = new Random();
     this.indices = new HashMap<Integer, List<Integer>>();
     int l = nums.length;
     for (int i = 0; i < l; ++i) {
     if (!this.indices.containsKey(nums[i])) {
     this.indices.put(nums[i], new ArrayList<>());
     }
     this.indices.get(nums[i]).add(i);
     }
     }

     public int pick(int target) {
     int l = indices.get(target).size();
     // pick an index at random
     int randomIndex = indices.get(target).get(rand.nextInt(l));
     return randomIndex;
     }**/
}
