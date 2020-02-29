package com.run.leetcode.dynamicProgramming;
/**
 * Input:   str1 = "geek",  str2 = "eke"
Output: "geeke"

Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
Output:  "AGXGTXAYB"
 Based of longest common subsequence + uncommon charACTERS
 
 Length of the shortest supersequence  = (Sum of lengths of given two strings) -
                                        (Length of LCS of two given strings) 
                                        
                                        O(2min(m, n)). S
 *
 */
public class ShortestCommonSupersequence {

	public static void main(String[] args) {
		String X = "AGGTAB"; 
	    String Y = "GXTXAYB"; 
	    System.out.println("Length of the shortest" +  
	                        "supersequence is: "
	            + shortestCommonSuperSequnceRecursive(X, Y, X.length(),Y.length())); 
	    System.out.println("Length of the shortest" +  
                "supersequence is: "
    + shortestCommongSequenceRecursiveDP(X, Y, X.length(),Y.length())); 

	}
	
	static int shortestCommonSuperSequnceRecursive(String x, String y, int m, int n) {
		if(m==0)
			return n;
		if(n==0)
			return m;
		
		if(x.charAt(m-1) == y.charAt(n-1))
			return 1 + shortestCommonSuperSequnceRecursive(x, y, m-1, n-1);
		else
			return 1 + Math.min(shortestCommonSuperSequnceRecursive(x, y, m, n-1), 
					shortestCommonSuperSequnceRecursive(x, y, m-1, n));
		
	}
	
	static int shortestCommongSequenceRecursiveDP(String x, String y, int m, int n) {
		int dp[][] = new int [m+1][n+1];
		
		for(int i = 0;i<= m;i++) {
			for(int j = 0; j<= n; j++) {
				if(i == 0) {
					dp[i][j] = j;
				}else if(j == 0)
					dp[i][j] = i;
				else if(x.charAt(i-1) == y.charAt(j-1))
					dp[i][j] = 1+ dp[i-1][j-1];
				else
					dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
				
			}
		}
		
		return dp[m][n];
		
	}

}
