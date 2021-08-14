package com.run.leetcode.dfs;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 *
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     m == maze.length
 *     n == maze[i].length
 *     1 <= m, n <= 100
 *     maze[i][j] is 0 or 1.
 *     start.length == 2
 *     destination.length == 2
 *     0 <= startrow, destinationrow <= m
 *     0 <= startcol, destinationcol <= n
 *     Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 *     The maze contains at least 2 empty spaces.
 */
public class TheMaze490 {
    int rowNum;
    int colNum;
    boolean[][] visited;
    int[][] directions = {{0,1}, {0,-1}, {1,0},{-1,0}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        rowNum = maze.length;
        colNum = maze[0].length;
        visited = new boolean[rowNum][colNum];
        int r = start[0];
        int c = start[1];
        return dfs(maze, r, c, destination);
    }

    public boolean dfs(int[][] maze, int r, int c, int[] destination) {
        if (r < 0 || r >= rowNum) return false;
        if (c < 0 || c >= colNum) return false;
        if (maze[r][c] == 1) return false;
        if (visited[r][c]) return false;
        if (r == destination[0] && c == destination[1]) return true;
        visited[r][c] = true;
        for (int[] dir: directions) {
            int newR = r;
            int newC= c;
            while (newR >= 0 && newR < rowNum && newC >= 0 && newC < colNum && maze[newR][newC] == 0) {
                newR += dir[0];
                newC += dir[1];
            }
            if (dfs(maze, newR-dir[0], newC-dir[1], destination)) {
                return true;
            }
        }
        return false;
    }
}
