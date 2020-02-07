package com.run.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.

Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation: 
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
1
2
3
4
5
6
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.
1
2
3
Constraints:

1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3
————————————————

 * @author Home-Laptop
 *
 */
public class ShortestDistanceToColor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static List<Integer> shortestDistance(int[] colors, int[][] queries){
		int [][] minDistances = new int[4][];
		
		for(int target =1 ; target<3 ;target++) {
			minDistances[target] = buildMinDistances(colors, target);
		}
		
		return Arrays.stream(queries)
						.map(query -> minDistances[query[1]][query[0]])
						.collect(Collectors.toList());
						
	}

	private static int[] buildMinDistances(int[] colors, int target) {
		int[] leftDistances = buildLeftDistances(colors,target);
		int[] rightDistances = buildRightDistances(colors,target);
		
		return IntStream.range(0, colors.length)
						.map(i-> Math.min(leftDistances[i], rightDistances[i]))
						.map(x-> x == Integer.MAX_VALUE ? -1 : x)
						.toArray();
	}

	private static int[] buildLeftDistances(int[] colors, int target) {
		int[] leftDistance = new int[colors.length];
		int leftIndex = -1;
		for(int i =0;i< colors.length;i++)
		{
			if(colors[i] == target)
			{
				leftIndex = i;
			}
			leftDistance[i] = leftIndex == -1 ? Integer.MAX_VALUE : i- leftIndex;
		}
		return leftDistance;
	}

	private static int[] buildRightDistances(int[] colors, int target) {
		int[] rightDistance = new int[colors.length];
		int rightIndex = -1;
		for(int j = colors.length-1 ;j>=0 ;j--) {
			if(colors[j] == target) {
				rightIndex = -1;
			}
			rightDistance[j] = rightIndex ==-1 ? Integer.MAX_VALUE :rightIndex -j;
		}
		return rightDistance;
	}
}
