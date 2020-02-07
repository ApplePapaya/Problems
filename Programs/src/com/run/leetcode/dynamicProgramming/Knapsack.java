package com.run.leetcode.dynamicProgramming;

import java.time.temporal.WeekFields;

public class Knapsack {

	public static void main(String[] args) {
		 int[] profits = {1, 6, 10, 16};
		    int[] weights = {1, 2, 3, 5};
		    int maxProfit = knapsack(profits, weights, 7);
		    System.out.println("Total knapsack profit ---> " + maxProfit);
		    maxProfit = knapsack(profits, weights, 6);
		 //   System.out.println("Total knapsack profit ---> " + maxProfit);
		    /********************KNAP SACK MEMOIZATION*******************************/
		    int capacity = 7;
		    Integer[][] dp = new Integer[profits.length][capacity+1];
		    System.out.println("Knap sack with memoization : "+knapsackRecursiveWithMemoization(profits, weights, capacity, 0, dp));
		    
		    System.out.println("Knap sack with dp bottom up : "+knapSackREcursiveDP(profits, weights, capacity));
		    
		    System.out.println("Knap sack with dp optimised one arry : "+knapSackDPOptimisedOneArray(profits, weights, capacity));

	}

	private static int knapsack(int[] profits, int[] weights, int capacity) {
		return knapsackRecursive(profits, weights, capacity, 0);
	}

	private static int knapsackRecursive(int[] profits, int[] weights, int capacity, int currIndex) {
		//Base case. Capacity less than 0 or all weigths considered
		if(capacity <=0 || currIndex>= profits.length)
			return 0;
		
		int profit1 =0;
		// Max profit when considering the current element
		if(weights[currIndex] <= capacity) {
			profit1 = profits[currIndex] + knapsackRecursive(profits, weights, capacity- weights[currIndex], currIndex+1);
		}
		
		//Max profit when ignoring the current element
		int profit2 = knapsackRecursive(profits, weights, capacity, currIndex+1);
		
		
		return Math.max(profit1, profit2);
		
		
	}
	
	//Knapsack recursive with memoization
	private static int knapsackRecursiveWithMemoization(int[] profits, int[] weights, int capacity, int index, Integer[][] dp) {
		if(capacity <=0 || index >= profits.length) 
			return 0;
			
			if(dp[index][capacity] != null) {
				return dp[index][capacity];
			}
			int profits1 = 0;
			if(weights[index]<= capacity) {
				profits1 = profits[index] + knapsackRecursiveWithMemoization(profits, weights, capacity- weights[index], index+1, dp);
			}
			
			int profits2 = knapsackRecursiveWithMemoization(profits, weights, capacity, index+1, dp);
			
			dp[index][capacity] = Math.max(profits1, profits2);
			return dp[index][capacity];
			
		}
	
	
	//Knapsack DP bottom up approach. Start with zeroes to fill the matrix.
	private static int knapSackREcursiveDP(int[] profits, int[] weights, int capacity) {
		if(capacity<=0 || profits.length==0 || weights.length != profits.length) {
			return 0;
		}
		int n = profits.length;
		int[][] dp = new int[n][capacity+1];
		
		//populate 0th column as 0 profit since the capacity is 0
		for(int i =0;i< n;i++) {
			dp[i][0] =0;
		}
		//populate the first row based on the weight of the first item in the array.
		for(int c=0; c<= capacity; c++) {
			if(weights[0] <= c) {
				dp[0][c] = profits[0];
			}
		}
		
		for(int i =1 ;i< n ; i++) {
			for(int c=1 ;c<= capacity;c++) {
				int profit1 =0, profit2=0;
				if(weights[i] <= c)
					profit1 = profits[i] + dp[i-1][c-weights[i]];
				profit2 = dp[i-1][c];
				dp[i][c] = Math.max(profit1, profit2);
			}
		}
		printSelectedElements(profits, weights, dp, capacity);
		return dp[n-1][capacity];
		
	}
	
	private static void printSelectedElements(int[] profits , int[] weights, int[][]dp, int capacity) {
		int n = weights.length;
		int totalProfit = dp[n-1][capacity];
		for(int i =n-1 ; i>0;i--) {
			if(totalProfit != dp[i-1][capacity]) {
				System.out.println("Weight -" + weights[i]);
				capacity -= weights[i];
				totalProfit -= profits[i];
			}
		}
		
		if(totalProfit !=0) {
			System.out.println("Weight -" + weights[0]);
		}
	}
	
	
	private static int knapSackDPOptimisedOneArray(int[] profits, int [] weights, int capacity) {
		if(capacity<=0 || profits.length==0|| profits.length != weights.length) {
			return 0;
		}
		int n = profits.length;
		
		int[] dp = new int[capacity+1];
		
		for(int c=0;c<= capacity;c++) {
			if(weights[0] <= c)
			{
				dp[c] = profits[0];
			}
		}
		//Weight index we will start with 1 as 0 is already populated above and we increment.
		// however capacity we will start with max so that we can access lesser capacity records-- in 2d array we used to refer prev row..
		//here the curret row has the prev row data for lower capacities hence we need to go from max to min.
		for(int i =1;i<n; i++) {
			for(int c = capacity;c>=0;c--) {
				int profit1 = 0, profit2 =0;
				if(weights[i] <= c)
					profit1 = profits[i] + dp[c- weights[i]];
				
				profit2 = dp[c];
				
				dp[c] = Math.max(profit1, profit2);
			}
		}
		return dp[capacity];
	}
	
}
