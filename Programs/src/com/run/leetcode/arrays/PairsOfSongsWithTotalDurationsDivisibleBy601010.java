package com.run.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 *
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 *
 * Constraints:
 *
 * 1 <= time.length <= 6 * 104
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy601010 {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : time)
        {
            if(t % 60 == 0)
                count += map.getOrDefault(0, 0);
            else
                count += map.getOrDefault(60 - (t % 60), 0);
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return count;
    }


    public int numPairsDivisibleBy602(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }
        return count;
    }

    public int numPairsDivisibleBy601msn(int[] time) {
        // get the remainders of each time
        for (int i = 0; i < time.length; ++i) {
            time[i] = time[i] % 60;
        }

        // count the freq of each number
        int[] count = new int[60];
        for (int i : time) {
            count[i]++;
        }

        int res = 0;
        for (int i = 0; i <= 30; ++i) {
            if (i == 0 || i == 30) {
                res += (count[i] * (count[i] - 1)) / 2;
            } else {
                res += (count[i] * count[60-i]);
            }
        }

        return res;
    }
}
