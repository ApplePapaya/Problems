package com.run.leetcode.binarySearch.diff;

/**
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.
 *
 * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
 *
 * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
 *
 * Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
 * Output: 0.50000
 *
 * Example 2:
 *
 * Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
 * Output: 14.00000
 *
 *
 *
 * Constraints:
 *
 *     10 <= stations.length <= 2000
 *     0 <= stations[i] <= 108
 *     stations is sorted in a strictly increasing order.
 *     1 <= k <= 106
 */
public class MinimizeMaxDistanceToGasStation774 {
    /**

     Intuition

     Let's ask possible(D): with K (or less) gas stations, can we make every adjacent distance between gas stations at most D? This function is monotone, so we can apply a binary search to find D*D^{\text{*}}D*.

     Algorithm

     More specifically, there exists some D* (the answer) for which possible(d) = False when d < D* and possible(d) = True when d > D*. Binary searching a monotone function is a typical technique, so let's focus on the function possible(D).

     When we have some interval like X = stations[i+1] - stations[i], we'll need to use ?XD?\lfloor \frac{X}{D} \rfloor?DX?? gas stations to ensure every subinterval has size less than D. This is independent of other intervals, so in total we'll need to use ?i?XiD?\sum_i \lfloor \frac{X_i}{D} \rfloor?i??DXi??? gas stations. If this is at most K, then it is possible to make every adjacent distance between gas stations at most D.

     Complexity Analysis

     Time Complexity: O(Nlog?W)O(N \log W)O(NlogW), where NNN is the length of stations, and W=1014W = 10^{14}W=1014 is the range of possible answers (10810^8108), divided by the acceptable level of precision (10?610^{-6}10?6).

     Space Complexity: O(1)O(1)O(1) in additional space complexity.

     **/

    public double minmaxGasDist(int[] stations, int k) {
        double[] diff = new double[stations.length - 1];
        double max = 0.0;
        for(int i = 1; i < stations.length; i++) {
            diff[i - 1] = stations[i] - stations[i - 1];
            max = Math.max(max, diff[i - 1]);
        }
        double min = 0;
        while(max - min > 1e-6) {
            double mid = (max + min) / 2.0;
            if (isFeasible(diff, mid, k)) {
                max = mid;
            } else {
                min = mid;
            }
        }
        return min;
    }

    private boolean isFeasible(double[] diff, double threshold, int k) {
        int total = 0;
        for(double dif : diff) {
            total += (int) (dif / threshold);
        }
        return total <= k;
    }
    public double minmaxGasDist22(int[] stations, int K) {
        double lo = 0, hi = 1e8;
        while (hi - lo > 1e-6) {
            double mi = (lo + hi) / 2.0;
            if (possible(mi, stations, K))
                hi = mi;
            else
                lo = mi;
        }
        return lo;
    }

    public boolean possible(double D, int[] stations, int K) {
        int used = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            used += (int) ((stations[i+1] - stations[i]) / D);
        return used <= K;
    }
}
