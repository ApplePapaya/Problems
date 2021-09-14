package com.run.leetcode.arrays;

/**
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops, where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 *
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 *
 *
 *
 * Example 1:
 *
 * Input: m = 3, n = 3, ops = [[2,2],[3,3]]
 * Output: 4
 * Explanation: The maximum integer in M is 2, and there are four of it in M. So return 4.
 *
 * Example 2:
 *
 * Input: m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
 * Output: 4
 *
 * Example 3:
 *
 * Input: m = 3, n = 3, ops = []
 * Output: 9
 *
 *
 *
 * Constraints:
 *
 *     1 <= m, n <= 4 * 104
 *     1 <= ops.length <= 104
 *     ops[i].length == 2
 *     1 <= ai <= m
 *     1 <= bi <= n
 */
public class RangeAdditionII598 {
    /**
     * As per the given problem statement, all the operations are performed on a rectangular sub-matrix of the initial all 0's MMM matrix. The upper left corner of each such rectangle is given by the index (0,0)(0, 0)(0,0) and the lower right corner for an operation [i,j][i, j][i,j] is given by the index (i,j)(i, j)(i,j).
     *
     * The maximum element will be the one on which all the operations have been performed. The figure below shows an example of two operations being performed on the initial MMM array.
     *
     * Range_Addition
     *
     * From this figure, we can observe that the maximum elements will be the ones which lie in the intersection region of the rectangles representing the operations. Further, we can observe that to count the number of elements lying in this intersection region, we don't actually need to perform the operations, but we need to determine the lower right corner of the intersecting region only. This corner is given by (x,y)=(min(op[0]),min(op[1]))\big(x, y\big) = \big(\text{min}(\text{op[0]}), \text{min}(op[1])\big)(x,y)=(min(op[0]),min(op[1])), where min(op[i])\text{min}(\text{op[i]})min(op[i]) reprsents the minimum value of op[i]\text{op[i]}op[i] from among all the op[i]\text{op[i]}op[i]'s in the given set of operations.
     *
     * Thus, the resultant count of elements lying in the intersection is given by: xxxxyyy.
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int minRow = m;
        int minCol = n;
        for (int[] op: ops) {
            minRow = Math.min(m, op[0]);
            minCol = Math.min(n, op[1]);
        }
        return minRow * minCol;
    }
}
