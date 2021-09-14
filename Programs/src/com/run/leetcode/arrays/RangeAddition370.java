package com.run.leetcode.arrays;

/**
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 *
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 *
 * Return arr after applying all the updates.
 *
 *
 *
 * Example 1:
 *
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 *
 * Example 2:
 *
 * Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
 * Output: [0,-4,2,2,2,4,4,-4,-4,-4]
 *
 *
 *
 * Constraints:
 *
 *     1 <= length <= 105
 *     0 <= updates.length <= 104
 *     0 <= startIdxi <= endIdxi < length
 *     -1000 <= inci <= 1000
 */
public class RangeAddition370 {
    /**
     * The algorithm makes use of the above intuition to simply store changes at the borders of the update ranges (instead of processing the entire range). Finally a single post processing operation is carried out over the entire output array.
     *
     * The two steps that are required are as follows:
     *
     *     For each update query (start,end,val)(start, end, val)(start,end,val) on the array arrarrarr, we need to do only two operations:
     *         Update startstartstart boundary of the range:
     *
     *     arrstart=arrstart+val arr_{start} = arr_{start} + val arrstart?=arrstart?+val
     *         Update just beyond the endendend boundary of the range:
     *
     *     arrend+1=arrend+1?val arr_{end+1} = arr_{end+1} - val arrend+1?=arrend+1??val
     *
     *     Final Transformation. The cumulative sum of the entire array is taken (0 - based indexing)
     *
     *     arri=arri+arri?1?i?[1,n) arr_i = arr_i + arr_{i-1} \quad \forall \quad i \in [1, n) arri?=arri?+arri?1??i?[1,n)
     * update the value at start index, because it will be used in the future when we are adding up the values for the sum at each index between start index and end index (both inclusive). We update the negative value at the end index + 1, because the positive value of it should be only added at its previous indices (from start index to end index). Thus, when we accumulate the sum at the end for each index, we will get the correct values for each index. If the end index is the last index in the resulting array, we don't have to do the end index + 1 part, because there is no more index after the last index and there will be no error when we accumulate the sum.
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {

        int[] res = new int[length];
        for(int[] update : updates) {
            int value = update[2];
            int start = update[0];
            int end = update[1];

            res[start] += value;

            if(end < length - 1)
                res[end + 1] -= value;

        }

        int sum = 0;
        for(int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }

        return res;
    }
}
