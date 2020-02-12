package com.run.leetcode.dynamicProgramming;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		System.out.println(longestPalindromicSubstring("bacca"));

	}
	
	public static String longestPalindromicSubstring(String s) {
		if(s== null || s.length()<=1)
			return s;
		
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		
		int maxLen = 0;
		String longest = null;
		// the below attribute l is to maintain the constant distance betweent he 2 pointers
		for(int l= 0;l<n;l++) {
			for(int i=0;i< n-l; i++) {
				int j =i +l;// j-i will be constant and will check all substrings for that length
				/**
				 * The below condition checks if the current chars at i and j are matching
				 * if yes then one of the below must be true
				 * 1> Either the length of substring must be less than or equal to 2
				 *    we can take 2 since the current chars are matching
				 *    e.g. i =0 and j =1 . So substring is length 2. Since the chars are same
				 *    this is palindromic of length 2. For length 1 its self expalnatory
				 *    
				 * 2> Dp[i+1][j-1] should be true. basically for substring abccba, when we are checking
				 * the a's automatically i+1 to j-1 is nothing but the substring with current i /j chars
				 * left out. So if this is true and current i/j are matching then yes its palindrome
				 * 				 */
				if(s.charAt(i) == s.charAt(j)   && ( (j-i<= 2)   ||
													dp[i+1][j-1] == true)){
					dp[i][j] = true;
					if(maxLen< j-i+1) {// we need to add 1 because both pointers needs ot be included 4-6 is
										// 3 chars and not 6-4 = 2 chars
						maxLen = j-i+1;
						longest = s.substring(i,j+1);//Substring goes with end index -1 for substring
						
					}
					
				}
			}
		}return longest;
	}

}
