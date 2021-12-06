package com.run.leetcode.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Return the number of distinct islands.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 50
 *     grid[i][j] is either 0 or 1.
 */
public class NumberOfDistinctIslands694 {
    /**
      WARNING: DO NOT FORGET to add path for backtracking, otherwise, we may have same result when we count two
     * distinct islands in some cases
     *
     * eg:              1 1 1   and    1 1 0
     *                  0 1 0          0 1 1
     * with b:          rdbr           rdr
     * without b:       rdr            rdr
     *
     *
     * Algorithm
     *
     * Each time we discover the first cell in a new island, we initialize an empty string.
     * Then each time dfs is called for that island, we firstly determine whether or not the cell being
     * entered is un-visited land, in the same way as before. If it is, then we append the direction we entered
     * it from to the string. For example, this is the path that our algorithm would follow to explore the
     * following island.
     *The solution to this is that we also need to record where we backtracked. This occurs each time we exit a call to the dfs(...) function. We'll do this by appending a 0 to the string.
     */
//5ms
    public int numDistinctIslands(int[][] grid) {
        Set<String> patterns = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, grid, sb, "o");
                    patterns.add(sb.toString());
                }
            }
        }

        return patterns.size();
    }

    private void dfs(int i, int j, int[][] grid, StringBuilder sb, String dir) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }

        sb.append(dir);
        grid[i][j] = 0;
        dfs(i + 1, j, grid, sb, "d");
        dfs(i - 1, j, grid, sb, "u");
        dfs(i, j - 1, grid, sb, "l");
        dfs(i, j + 1, grid, sb, "r");

        sb.append("b");
    }
//8-9ms
    public int numDistinctIslands4(int[][] grid) {
        // Non-Empty, so no need to check for boundary conditions
        Set<String> islandShapes = new HashSet<>();
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[row].length; col++) {
                if(grid[row][col] == 0)
                    continue;

                StringBuilder sb = new StringBuilder();     // mark origin
                depthFirstSearch(grid, row, col, sb, "o");  // cover the entire island
                islandShapes.add(sb.toString());
            }
        }
        // System.out.println(islandShapes);
        return islandShapes.size();
    }

    private static void depthFirstSearch(int[][] grid, int row, int col, StringBuilder sb, String direction) {
        if (row < 0 || row >= grid.length)       return;
        if (col < 0 || col >= grid[row].length)  return;
        if (grid[row][col] == 0)                 return;

        grid[row][col] = 0;                             // Mark as visited
        sb.append(direction);                           // Record the incoming direction
        depthFirstSearch(grid, row - 1, col, sb, "u");  // Recurse up
        depthFirstSearch(grid, row + 1, col, sb, "d");  // Recurse down
        depthFirstSearch(grid, row, col - 1, sb, "l");  // Recurse left
        depthFirstSearch(grid, row, col + 1, sb, "r");  // Recurse right
        sb.append("b");                                 // End of traversal. Back to origin, mark the end
    }
    //7ms
    public int numDistinctIslands2(int[][] grid) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "o"); // origin
                    grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if(i < 0 || i == grid.length || j < 0 || j == grid[i].length
                || grid[i][j] == 0) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, "u");
        dfs(grid, i+1, j, sb, "d");
        dfs(grid, i, j-1, sb, "l");
        dfs(grid, i, j+1, sb, "r");
        sb.append("b"); // back
    }
}
