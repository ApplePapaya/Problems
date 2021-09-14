package com.run.leetcode.arrays;

import java.util.List;

/**
 * (This problem is an interactive problem.)
 *
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 *     BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 *     BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 *
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 *
 * Example 2:
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 *
 * Example 3:
 *
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 * Example 4:
 *
 * Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     rows == mat.length
 *     cols == mat[i].length
 *     1 <= rows, cols <= 100
 *     mat[i][j] is either 0 or 1.
 *     mat[i] is sorted in non-decreasing order.
 */
public class LeftMostColumnWithAtleastAOne1428 {
/**
 *  Start at Top Right, Move Only Left and Down
 */

    /**
     * Complexity Analysis
     *
     * Let NNN be the number of rows, and MMM be the number of columns.
     *
     *     Time complexity : O(N+M)O(N + M)O(N+M).
     *
     *     At each step, we're moving 1 step left or 1 step down. Therefore, we'll always finish looking at either one of the MMM rows or NNN columns. Therefore, we'll stay in the grid for at most N+MN + MN+M steps, and therefore get a time complexity of O(N+M)O(N + M)O(N+M).
     *
     *     Space complexity : O(1)O(1)O(1).
     *
     *     We are using constant extra space.
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);

        // Set pointers to the top-right corner.
        int currentRow = 0;
        int currentCol = cols - 1;

        // Repeat the search until it goes off the grid.
        while (currentRow < rows && currentCol >= 0) {
            if (binaryMatrix.get(currentRow, currentCol) == 0) {
                currentRow++;
            } else {
                currentCol--;
            }
        }

        // If we never left the last column, this is because it was all 0's.
        return (currentCol == cols - 1) ? -1 : currentCol + 1;
    }
}

interface BinaryMatrix {
    public int get(int row, int col);
    public List<Integer> dimensions();
}
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */
