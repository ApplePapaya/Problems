package com.run.leetcode.slidingWindow;

public class test {
	
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
