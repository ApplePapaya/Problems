package com.run.leetcode.dfs;

/**
 * 827. Making A Large Island
 * Hard
 *
 * 1562
 *
 * 39
 *
 * Add to List
 *
 * Share
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */
public class MakingALargeIsland827 {

    int[] size;
    int numRows;
    int numCols;

    //49ms
    public int largestIsland(int[][] grid) {
        numRows = grid.length;
        numCols = grid[0].length;
        size = new int[numRows * numCols + 2];// if no + 2 then this will fail [[1]]
        int areaNumber = 2;
        // starting from 2 since we are marking grid with numbers and 0 and 1 are used to denote island or not.. hence the pluz
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 1) {
                    dfs(grid, row, col, areaNumber);
                    areaNumber++;
                }
            }
        }

        boolean allOnes = true;
        int max = 0;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 0) {
                    allOnes = false;
                    max = Math.max(max, calculate(grid, row, col));
                }
            }
        }

        return allOnes ? numRows * numCols : max;
    }

    private void dfs(int[][] grid, int row, int col, int areaNumber) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || grid[row][col] != 1) return;

        grid[row][col] = areaNumber;
        size[areaNumber]++;

        dfs(grid, row + 1, col, areaNumber);
        dfs(grid, row - 1, col, areaNumber);
        dfs(grid, row, col + 1, areaNumber);
        dfs(grid, row, col - 1, areaNumber);
    }

    private int calculate(int[][] grid, int row, int col) {
        int sum = 1;
        int p1 = findParent(grid, row + 1, col);
        int p2 = findParent(grid, row - 1, col);
        int p3 = findParent(grid, row, col + 1);
        int p4 = findParent(grid, row, col - 1);

        if (p1 != -1) sum += size[p1];
        if (p2 != -1 && p1 != p2) sum += size[p2];
        if (p3 != -1 && p3 != p1 && p3 != p2) sum += size[p3];
        if (p4 != -1 && p4 != p1 && p4 != p2 && p4 != p3) sum += size[p4];

        return sum;
    }

    int findParent(int[][] grid,int i,int j){
        if(i < 0 || i >= numRows || j < 0 || j >= numCols) return -1;
        return grid[i][j];
    }
}
