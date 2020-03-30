package com.run.leetcode.dynamicProgramming;
//https://leetcode.com/problems/maximal-square/submissions/


public class MaximalSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] arr ={{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},{'1','1','0','1','1'},{'0','1','1','1','1'}};
		System.out.println(maximalSquare(arr));
	}

	 public static int maximalSquare(char[][] matrix) {
	        
	        if(matrix == null || matrix.length == 0)
	            return 0;
	        
	        int max = 0;
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        
	        int[] dp = new int[cols + 1];
	        int prev = 0;
	        
	        for(int i = 1; i <= rows; i++)
	        {
	            for(int j = 1; j <= cols; j++)
	            {
	                 int temp = dp[j];
	                if(matrix[i-1][j-1] == '1')
	                {
	                   
	                    dp[j] = Math.min(Math.min(dp[j], dp[j-1]), prev) + 1;
	                    max = Math.max(max, dp[j]);
	                   
	                }else
	                {
	                    dp[j] = 0;
	                }
	                 prev = temp;
	            }
	        }
	        return max * max;
	        
	    }
}
