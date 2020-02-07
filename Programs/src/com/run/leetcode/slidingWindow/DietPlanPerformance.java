package com.run.leetcode.slidingWindow;
/**
 * A dieter consumes calories[i] calories on the i-th day. For every consecutive sequence of k days, they look at T, 
 * the total calories consumed during that sequence of k days:

If T < lower, they performed poorly on their diet and lose 1 point;
If T > upper, they performed well on their diet and gain 1 point;
Otherwise, they performed normally and there is no change in points.
Return the total number of points the dieter has after all calories.length days.

Note that: The total points could be negative.
Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
Output: 0
Explaination: calories[0], calories[1] < lower and calories[3], calories[4] > upper, total points = 0.


Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
Output: 0
Explaination: calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, total points = 0.

Input: calories = [3,2], k = 2, lower = 0, upper = 1
Output: 1
Explaination: calories[0] + calories[1] > upper, total points = 1.

Constraints:

1 <= k <= calories.length <= 10^5
0 <= calories[i] <= 20000
0 <= lower <= upper



 *
 */
public class DietPlanPerformance {
	
	public static void main (String[] args) {
		int[] calories = {1,2,3,4,5};
		int k =1, lower =3, upper = 3;
				
		System.out.println(getDietaryPointsSlidingWindow(calories, k, lower,upper));
		
		int[] calories2 = {6,5,0,0};
		 k =2;  lower =1; upper = 5;
		 
		 System.out.println(getDietaryPointsSlidingWindow(calories2, k, lower,upper));
	}

	private static int getDietaryPointsSlidingWindow(int[] calories, int k, int lower, int upper) {
		int points = 0;
		int calWindow=0;
		int start =0;
		for (int i =0;i<k ;i++) {
			calWindow += calories[i];
		}
		for(int i=k;i< calories.length;i++)
		{
			points = updatePoints(calWindow, lower, upper, points);
			calWindow -= calories[start++];
			calWindow += calories[i];
		}
		points = updatePoints(calWindow, lower, upper, points);
		
		return points;
	}

	private static int updatePoints(int calWindow, int lower, int upper, int points) {
		int retPoints =points;
		if(calWindow< lower) retPoints--;
		if(calWindow>upper) retPoints++;
		return retPoints;
	}

}
