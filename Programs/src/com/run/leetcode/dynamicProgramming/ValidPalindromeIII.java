package com.run.leetcode.dynamicProgramming;

/**
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 * Example 2:
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 1000
 *     s consists of only lowercase English letters.
 *     1 <= k <= s.length
 */
public class ValidPalindromeIII {
//same as longest subsequence
    public boolean isValidPalindrome(String str, int k) {
        int n = str.length();

        StringBuilder stringBuilder = new StringBuilder(str).reverse();
        int lps = lcs(str, stringBuilder.toString(), n, n);

        return (n - lps <= k);
    }

    /*
    longest palindromic subsequence:
    LCS of the given string & its reverse will be the longest palindromic sequence.
     */
    private int lcs(String X, String Y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
