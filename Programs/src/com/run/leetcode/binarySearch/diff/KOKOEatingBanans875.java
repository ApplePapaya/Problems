package com.run.leetcode.binarySearch.diff;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class KOKOEatingBanans875 {

    /**
     Here we need to find the minimum K (minimum banans the koko should eat)
     KOKO can eat max bananas = max value in the pile.
     Minimum bananas that he can eat is total bananas / total number of hours.
     However this minimum value will not work with H hours if there are values in the indices which are multiples of this value.
     Example Input: piles = [30,11,23,4,20], H = 5
     Output: 30
     Total is 88 and hours = 5. 88/5 = 18 bananas minimum koko has to eat
     But if he eats only 18 hours the total time it will take is
     18 * 2 + 1 + 18 * 2 + 1 + 18 * 2 = 8 hours.
     so we need to increase the value to more than 18. Hence we need to do binary search to find the value which lies between minimum and max such that the value takes only H hours

     1> Calculate the sum of bananas and the max value.
     2> Min value = Max of 1 and sum/ H
     3> Max is max value in the array
     4> we start with min value and end with max value
     5> while start < end
     mid = start + (end - start)/2
     checkIfFinish - mid, piles, H
     if yes, then check can we go still lesser value of k
     by saying end = mid
     if no, then increment the start to mid + 1

     6> return start

     7> Can finish
     for each value of the pile in piles array
     check if its less than mid
     if yes decrement hours by 1
     else decrement hours by pile/K + pile %k ==0 ? 0 : 1

     if hours < 0 break;

     return hours >= 0; ifyes then koko can finish

     ********************************************
     Explanation
     Binary search between [1, 10^9] or [1, max(piles)] to find the result.
     Time complexity: O(NlogM)

     (p + m - 1) / m equal to ceil(p / m) (just personal behavior)

     Here you find another similar problem.
     774. Minimize Max Distance to Gas Station


     Complexity
     Time O(Nlog(MaxP))
     Space O(1)
     **/
    public int minEatingSpeed(int[] piles, int H) {
        int n = piles.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = 0;
        for(int pile : piles)
        {
            max = Math.max(max, pile);
            sum += pile;
        }

        min = Math.max(1, sum / H);
        //If we put <= below, then max has to be mid -1. Else if we do l < r then r should be mid
        while(min <= max)//while ( min < max)
        {
            int mid = min + (max - min)/2;
            if(canFinish(mid, piles, H))
                max = mid-1; // max = mid
            else
                min = mid + 1;
        }
        return min;
    }

    private boolean canFinish(int k, int[] piles, int H){
        for(int pile : piles)
        {
            if(pile <= k)
                H--;
            else
                H -= (pile / k) + (pile % k == 0 ? 0 : 1);

            if(H < 0)
                break;
        }

        return H >= 0;

    }
}
