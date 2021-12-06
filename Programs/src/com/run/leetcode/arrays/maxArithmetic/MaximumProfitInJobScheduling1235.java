package com.run.leetcode.arrays.maxArithmetic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumProfitInJobScheduling1235 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;

        for (int i = 1; i < len; i++) {
            int e = endTime[i], s = startTime[i], p = profit[i], j = i - 1;
            while (j >= 0 && endTime[j] > e) {//Sort by end time ascending order
                endTime[j + 1] = endTime[j];
                startTime[j + 1] = startTime[j];
                profit[j + 1] = profit[j];
                j--;
            }
            endTime[j + 1] = e;
            startTime[j + 1] = s;
            profit[j + 1] = p;
        }


        int[] dp = new int[len];
        dp[0] = profit[0]; //for first task, the profit is just that
/**
 * For every successive start
 * 1> Check if the prev End is greater than current start(meaning overlap)
 * 2> Then move the pointer to the left until, there some prev index which has end < current start
 * 3> If this index < 0 , meaning no non overlapping task found for the current one
 */
        for(int j = 1; j < len; j++){
            int sta = startTime[j];
            // int end = jobs.get(i).get(1);
            int earn = profit[j];
            // int prevEarn = jobs.get(i-1).get(1);
            int prevEnd = endTime[j-1];

            if(sta < prevEnd){
                int prev = j - 1;
                while(prev >= 0 && endTime[prev] > sta) prev--;
                if(prev < 0){
                    dp[j] = Math.max(earn, dp[j-1]);
                }else{
                    dp[j] = Math.max(dp[prev] + earn, dp[j-1]);
                }
            }else{

                dp[j] = dp[j-1] + earn;
            }
        }
        return dp[len-1];
    }



    /***************************************/
    class The_Comparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            return list1.get(0) - list2.get(0);
        }
    }

    private int findMaxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        // min heap having {endTime, profit}
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(new The_Comparator());

        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0), end = jobs.get(i).get(1), profit = jobs.get(i).get(2);

            // keep popping while the heap is not empty and
            // jobs are not conflicting
            // update the value of maxProfit
            while (pq.isEmpty() == false && start >= pq.peek().get(0)) {
                maxProfit = Math.max(maxProfit, pq.peek().get(1));
                pq.remove();
            }

            ArrayList<Integer> combinedJob = new ArrayList<>();
            combinedJob.add(end);
            combinedJob.add(profit + maxProfit);

            // push the job with combined profit
            // if no non-conflicting job is present maxProfit will be 0
            pq.add(combinedJob);
        }

        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while (pq.isEmpty() == false) {
            maxProfit = Math.max(maxProfit, pq.peek().get(1));
            pq.remove();
        }

        return maxProfit;
    }

    public int jobScheduling22(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        // storing job's details into one list
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);

            jobs.add(currJob);
        }

        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        return findMaxProfit(jobs);
    }
}
