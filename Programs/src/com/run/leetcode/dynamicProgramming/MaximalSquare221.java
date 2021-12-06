package com.run.leetcode.dynamicProgramming;
//https://leetcode.com/problems/maximal-square/submissions/

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 300
 *     matrix[i][j] is '0' or '1'.
 */

public class MaximalSquare221 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] arr ={{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},{'1','1','0','1','1'},{'0','1','1','1','1'}};
		System.out.println(maximalSquare(arr));
	}

	/**
	 * In the previous approach for calculating dp of i^{th}i
	 * th
	 *   row we are using only the previous element and the (i-1)^{th}(i?1)
	 * th
	 *   row. Therefore, we don't need 2D dp matrix as 1D dp array will be sufficient for this.
	 *
	 * Initially the dp array contains all 0's. As we scan the elements of the original matrix across a row, we keep on updating the dp array as per the equation dp[j]=min(dp[j-1],dp[j],prev)dp[j]=min(dp[j?1],dp[j],prev), where prev refers to the old dp[j-1]dp[j?1]. For every row, we repeat the same process and update in the same dp array.
	 *
	 *  Max Square
	 * @param matrix
	 * @return
	 */
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
	                    dp[j] = 0;//because it can contain value from previous row computation and will
						//give incorrect values for next row if not reset
	                }
	                 prev = temp;
	            }
	        }
	        return max * max;
	        
	    }

	public int maximalSquare2D(char[][] a) {
		if (a == null || a.length == 0 || a[0].length == 0)
			return 0;

		int max = 0, n = a.length, m = a[0].length;

		// dp(i, j) represents the length of the square
		// whose lower-right corner is located at (i, j)
		// dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		// return the area
		return max * max;
	}
}
