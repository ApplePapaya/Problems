package com.run.leetcode.dynamicProgramming;
/**
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, mines = [[4,2]]
 * Output: 2
 * Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
 * Example 2:
 *
 *
 * Input: n = 1, mines = [[0,0]]
 * Output: 0
 * Explanation: There is no plus sign, so return 0.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * All the pairs (xi, yi) are unique.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LargestPlusSign764 {
    /**

     Algorithms: For each position (i, j) of the grid matrix, we try to extend in each of the four directions (left, right, up, down) as long as possible, then take the minimum length of 1's out of the four directions as the order of the largest axis-aligned plus sign centered at position (i, j).

     Optimizations: Normally we would need a total of five matrices to make the above idea work -- one matrix for the grid itself and four more matrices for each of the four directions. However, these five matrices can be combined into one using two simple tricks:

     For each position (i, j), we are only concerned with the minimum length of 1's out of the four directions. This implies we may combine the four matrices into one by only keeping track of the minimum length.

     For each position (i, j), the order of the largest axis-aligned plus sign centered at it will be 0 if and only if grid[i][j] == 0. This implies we may further combine the grid matrix with the one obtained above.

     Implementations:

     Create an N-by-N matrix grid, with all elements initialized with value N.
     Reset those elements to 0 whose positions are in the mines list.
     For each position (i, j), find the maximum length of 1's in each of the four directions and set grid[i][j] to the minimum of these four lengths. Note that there is a simple recurrence relation relating the maximum length of 1's at current position with previous position for each of the four directions (labeled as l, r, u, d).
     Loop through the grid matrix and choose the maximum element which will be the largest axis-aligned plus sign of 1's contained in the grid.

     Solutions: Here is a list of solutions for Java/C++/Python based on the above ideas. All solutions run at O(N^2) time with O(N^2) extra space. Further optimizations are possible such as keeping track of the maximum plus sign currently available and terminating as early as possible if no larger plus sign can be found for current row/column.

     Note: For those of you who got confused by the logic within the first nested for-loop, refer to andier's comment and my comment below for a more clear explanation.


     **/
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> banned = new HashSet();
        int[][] dp = new int[N][N];

        for (int[] mine: mines)
            banned.add(mine[0] * N + mine[1]);
        int ans = 0, count;

        for (int r = 0; r < N; ++r) {
            count = 0;
            for (int c = 0; c < N; ++c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = N-1; c >= 0; --c) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; ++c) {
            count = 0;
            for (int r = 0; r < N; ++r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N-1; r >= 0; --r) {
                count = banned.contains(r*N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }

        return ans;
    }

    public int orderOfLargestPlusSignCombiningLoop(int N, int[][] mines) {
        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], N);
        }

        for (int[] m : mines) {
            grid[m[0]][m[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));  // left direction
                grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));  // right direction
                grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));  // up direction
                grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));  // down direction
            }
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }

        return res;
    }
}
