package com.run.leetcode.dynamicProgramming;

/**
 * Medium
 *
 * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
 *
 * The line could be horizontal, vertical, diagonal, or anti-diagonal.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
 * Output: 3
 *
 * Example 2:
 *
 * Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
 * Output: 4
 *
 *
 *
 * Constraints:
 *
 *     m == mat.length
 *     n == mat[i].length
 *     1 <= m, n <= 104
 *     1 <= m * n <= 104
 *     mat[i][j] is either 0 or 1.
 */
public class LongestLinesOfConsecutiveOnesInAMatrix562 {

    /**
     * Instead of traversing over the same matrix multiple times, we can keep a track of the 1' along all the lines possible while traversing the matrix once only. In order to do so, we make use of a 4mn4mn4mn sized dpdpdp array. Here, dp[0]dp[0]dp[0], dp[1]dp[1]dp[1], dp[2]dp[2]dp[2] ,dp[3]dp[3]dp[3] are used to store the maximum number of continuous 1's found so far along the Horizontal, Vertical, Diagonal and Anti-diagonal lines respectively. e.g. dp[i][j][0]dp[i][j][0]dp[i][j][0] is used to store the number of continuous 1's found so far(till we reach the element M[i][j]M[i][j]M[i][j]), along the horizontal lines only.
     *
     * Thus, we traverse the matrix MMM in a row-wise fashion only but, keep updating the entries for every dpdpdp appropriately.
     * Complexity Analysis
     *
     *     Time complexity : O(mn)O(mn)O(mn). We traverse the entire matrix once only.
     *
     *     Space complexity : O(mn)O(mn)O(mn). dpdpdp array of size 4mn4mn4mn is used, where mmm and nnn are the number of rows ans coloumns of the matrix.
     */

    public int longestLine(int[][] M) {
        if (M.length == 0) return 0;
        int ones = 0;
        int[][][] dp = new int[M.length][M[0].length][4];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j < M[0].length - 1) ? dp[i - 1][j + 1][3] + 1 : 1;
                    ones =
                            Math.max(
                                    ones,
                                    Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                }
            }
        }
        return ones;
    }

    /**
     *Approach 3: Using 2D Dynamic Programming
     * In the previous approach, we can observe that the current dpdpdp entry is dependent only on the entries of the just previous corresponding dpdpdp row. Thus, instead of maintaining a 2-D dpdpdp matrix for each kind of line of 1's possible, we can use a 1-d array for each one of them, and update the corresponding entries in the same row during each row's traversal. Taking this into account, the previous 3-D dpdpdp matrix shrinks to a 2-D dpdpdp matrix now. The rest of the procedure remains same as the previous approach.
     *

     Time complexity : O(mn)O(mn)O(mn). The entire matrix is traversed once only.

     Space complexity : O(n)O(n)O(n). dpdpdp array of size 4n4n4n is used, where nnn is the number of columns of the matrix.

     *  @param M
     * @return
     */
    public int longestLine2D(int[][] M) {
        if (M.length == 0) return 0;
        int ones = 0;
        int[][] dp = new int[M[0].length][4];
        for (int i = 0; i < M.length; i++) {
            int old = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    dp[j][0] = j > 0 ? dp[j - 1][0] + 1 : 1;
                    dp[j][1] = i > 0 ? dp[j][1] + 1 : 1;
                    int prev = dp[j][2];
                    dp[j][2] = (i > 0 && j > 0) ? old + 1 : 1;
                    old = prev;
                    dp[j][3] = (i > 0 && j < M[0].length - 1) ? dp[j + 1][3] + 1 : 1;
                    ones =
                            Math.max(ones, Math.max(Math.max(dp[j][0], dp[j][1]), Math.max(dp[j][2], dp[j][3])));
                } else {
                    old = dp[j][2];
                    dp[j][0] = dp[j][1] = dp[j][2] = dp[j][3] = 0;
                }
            }
        }
        return ones;
    }
}
