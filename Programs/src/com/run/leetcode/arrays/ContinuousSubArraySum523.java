package com.run.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 */
public class ContinuousSubArraySum523 {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0 && nums[i - 1] == 0) {
                return true;
            }
            sum += nums[i];
        }
        if (sum < k) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            int sub = sum;
            for (int j = nums.length - 1; j > i; j--) {
                if (sub % k == 0) {
                    return true;
                } else if (sub < k || j == i + 1) {
                    sum -= nums[i];
                    break;
                }
                sub -= nums[j];
            }
        }
        return false;
    }
    //5 ms
    public boolean checkSubarraySum3(int[] nums, int k) {
        int n=nums.length;
        //Initialize an array of length n and store sum of the nums so far in the arrya
        int dp[]= new int[n];dp[0]=nums[0]; //dp[0]  = nums[0]
        for(int i=1;i<n;i++)
        {
            dp[i]=nums[i]+dp[i-1]; // keep summing up the nums values
            // have to check the zero first
            if(nums[i]==0&&nums[i-1]==0)return true;//2 consecutive nums summing to 0 is true since 0 is considered as multiple
            if(dp[i]%k==0)return true;

        }

        if(dp[n-1]<k)return false;

        for(int i=0;i<n-2;i++)
        {
            for(int j=i+2;j<n;j++)
            {
                if((dp[j]-dp[i])%k==0)return true;
            }
        }
        return false;
    }

    //25ms
    /**
     helpful ; tnx ; regarding "<0,-1> can allow it to return true when the runningSum%k=0" and "if first element is divisible by k , index -1 will help us not to return true":

     For example is [0,0],0 or [6,6,1],1; if we put(0,0) instead of put(0,-1), program will wrongly return false. If we don't put anything it still returns false since first 0 will get index 0 and second one will be mapped to index 1, which results to invalid case.
     It's hard to wrap my head around it, but i file it under edge cases ... how am i gonna figure this out if seeing it first time in interview? no way.

     **/
    public boolean checkSubarraySum22(int[] nums, int k) {

        Map<Integer,Integer> modVals=new HashMap<>();
        modVals.put(0, -1);// ifnoting the index we will put -1 and if count then 0
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            ////  if(i >0 && nums[i]==0&&nums[i-1]==0)return true;
            ///  if(i > 0 && sum%k==0)return true;
            int mod=sum%k;
            if(modVals.containsKey(mod) && i-modVals.get(mod)>1)// this implies that the prev time we had the mod till so far the diff will be multiple of k and check that the diff in indices is atleast 2
                return true;
            else
                modVals.putIfAbsent(mod, i);//put only if absent..else we want the first inde to stay
        }
        return false;
    }
}
