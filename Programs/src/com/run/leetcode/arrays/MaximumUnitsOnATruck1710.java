package com.run.leetcode.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 *
 * Return the maximum total number of units that can be put on the truck.
 *
 *
 *
 * Example 1:
 *
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are:
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the third type.
 * The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 * Example 2:
 *
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 *
 *
 * Constraints:
 *
 * 1 <= boxTypes.length <= 1000
 * 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * 1 <= truckSize <= 106
 */
public class MaximumUnitsOnATruck1710 {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] arr = new int[1001];
        int max = 0;
        for (int[] box : boxTypes) {
            int value = box[1];
            arr[value] += box[0];
            max = Math.max(value, max);
        }

        int sum = 0;
        // for (int i = 1000; i >= 0; i--) {
        for (int i = max; i >= 0; i--) {
            if (arr[i] == 0) continue;

            int loads = Math.min(truckSize, arr[i]);
            sum += loads * i;
            truckSize -= loads;

            if(truckSize== 0)
                return sum;

        }

        return sum;
    }

    //time = O(nlogn) Space = o(1)
    public int maximumUnitsSorting(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;
        for (int[] boxType : boxTypes) {
            int boxCount = Math.min(truckSize, boxType[0]);
            unitCount += boxCount * boxType[1];
            truckSize -= boxCount;
            if (truckSize == 0)
                break;
        }
        return unitCount;
    }


    public int maximumUnitsPQ(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->b[1] - a[1]);
        queue.addAll(Arrays.asList(boxTypes));
        int unitCount = 0;
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int boxCount = Math.min(truckSize, top[0]);
            unitCount += boxCount * top[1];
            truckSize -= boxCount;
            if(truckSize == 0)
                break;
        }
        return unitCount;
    }
}