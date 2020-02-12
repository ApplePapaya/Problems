package com.run.leetcode.dynamicProgramming;

import java.util.Arrays;

public class LongestCommonSubsequence {
	
	
	public static void main(String[] args) {
		
		  String s1 = "mhunuzqrkzsnidwbun"; String s2="szulspmhwpazoxijwbq";
		 
		/*
		 * String s1="ABCDEFG"; String s2="DFG";
		 */
		int[][] memo = new int[s1.length()][s2.length()];
		for(int[] row :memo) {
			Arrays.fill(row, -1);
		}
		System.out.println("Length of longest common subsequence : "+ lcsRecursive(s1,s2,s1.length()-1,s2.length()-1));
		System.out.println("Length of longest common subsequence : "+ lcsMemoization(s1,s2,s1.length()-1,s2.length()-1,memo));
		System.out.println("Length of longest common subsequence : "+ lcsDP(s1,s2,s1.length(),s2.length()));
		System.out.println("Length of longest common subsequence : "+ lcsDPOptimised(s1,s2,s1.length(),s2.length()));
			 

	}

	private static int lcsRecursive(String s1, String s2, int l1, int l2) {
		if(l1<0 || l2<0) {
			return 0;
		}else if(s1.charAt(l1) == s2.charAt(l2)) {
			return 1+ lcsRecursive(s1,s2, l1-1,l2-1);
		}else {
			return Math.max(lcsRecursive(s1, s2,l1-1,l2),lcsRecursive(s1,s2,l1,l2-1));
		}
		
	}
	
	private static int lcsMemoization(String s1, String s2, int l1, int l2, int[][] memo) {
		if(l1<0||l2<0) {
			return 0;
		}
		if(memo[l1][l2] != -1)
		{
			return memo[l1][l2];
		}
		if(s1.charAt(l1)== s2.charAt(l2)) {
			memo[l1][l2] = 1 + lcsMemoization(s1, s2, l1-1, l2-1, memo);
			return memo[l1][l2];
		}else {
			memo[l1][l2] = Math.max(lcsMemoization(s1, s2, l1-1, l2, memo), lcsMemoization(s1, s2, l1, l2-1, memo));
			return memo[l1][l2];
		}
	}
	
	private static int lcsDP(String s1, String s2, int l1, int l2) {
		int dp[][] = new int[l1+1][l2+1];
		for(int i =0;i<=l1;i++) {
			for(int j =0;j<=l2 ;j++) {
				if(i==0 || j==0) {
					dp[i][j] =0;
				}else if(s1.charAt(i-1)== s2.charAt(j-1))
				{
					dp[i][j] = dp[i-1][j-1] +1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				
			}
		}
		return dp[l1][l2];
	}

	private static int lcsDPOptimised(String s1, String s2, int l1, int l2) {
		int dp[] = new int[l2+1];
		for(int i =1;i<= l1;i++) {
			int prev =0;
			for(int j=1;j<=l2;j++) {
				int temp = dp[j];
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[j] = prev + 1;
				}else {
					dp[j] = Math.max(dp[j-1], dp[j]);
				}
				prev = temp;
			}
		}
		
		return dp[l2];
	}
}
