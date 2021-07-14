package com.run.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 *     '*' is your location. There is exactly one '*' cell.
 *     '#' is a food cell. There may be multiple food cells.
 *     'O' is free space, and you can travel through these cells.
 *     'X' is an obstacle, and you cannot travel through these cells.
 *
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 *
 * Example 2:
 *
 * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 *
 * Example 3:
 *
 * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 *
 * Example 4:
 *
 * Input: grid = [["O","*"],["#","O"]]
 * Output: 2
 *
 * Example 5:
 *
 * Input: grid = [["X","*"],["#","X"]]
 * Output: -1
 *
 *
 *
 * Constraints:
 *
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 200
 *     grid[row][col] is '*', 'X', 'O', or '#'.
 *     The grid contains exactly one '*'.
 */
public class ShortestPathToGetFood1730 {
    public int getFood(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return -1;
        }

        int rowLength = grid.length;
        int colLength = grid[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(grid[i][j] == '*') {
                    queue.add(new int[]{i, j, 0});
                    break;
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int step = currentCell[2];

            for(int[] direction : directions) {
                int row = currentCell[0] + direction[0];
                int col = currentCell[1] + direction[1];

                if(row < 0 || row >= rowLength || col < 0 || col >= colLength || grid[row][col] == 'X' || visited[row][col]) {
                    continue;
                }

                if(grid[row][col] == '#') {
                    return step + 1;
                }
                // grid[row][col] = 'X'; //if ok to modify the input
                visited[row][col] = true;
                queue.add(new int[]{row, col, step + 1});
            }
        }

        return -1;
    }
}
