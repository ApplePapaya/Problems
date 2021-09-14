package com.run.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
 *
 * The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
 *
 * Your task is to move the box 'B' to the target position 'T' under the following rules:
 *
 * The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
 * The character '.' represents the floor which means a free cell to walk.
 * The character '#' represents the wall which means an obstacle (impossible to walk there).
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#",".","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 3
 * Explanation: We return only the number of times the box is pushed.
 * Example 2:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#","#","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: -1
 * Example 3:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T",".",".","#","#"],
 *                ["#",".","#","B",".","#"],
 *                ["#",".",".",".",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 5
 * Explanation:  push the box down, left, left, up and up.
 * Example 4:
 *
 * Input: grid = [["#","#","#","#","#","#","#"],
 *                ["#","S","#",".","B","T","#"],
 *                ["#","#","#","#","#","#","#"]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid contains only characters '.', '#', 'S', 'T', or 'B'.
 * There is only one character 'S', 'B', and 'T' in the grid.
 */
public class MinimumMovestoMoveaBoxtoTheirTargetLocation1263 {
    //Time : O(n^2) ?
    //Space: O(m*n) ?
    char[][] grid;
    int m, n;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int step = 0;
        boolean[][][] visited = new boolean[m][n][4]; // considering 4 directons

        Queue<int[]> boxQ = new LinkedList<>();
        Queue<int[]> playerQ = new LinkedList<>();
        int[] boxLoc=new int[2], targetLoc=new int[2] , playerLoc=new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'B') boxLoc = new int[]{i, j};
                if (grid[i][j] == 'T') targetLoc = new int[]{i, j};
                if (grid[i][j] == 'S') playerLoc = new int[]{i, j};
            }
        }
        boxQ.offer(new int[]{boxLoc[0], boxLoc[1]});
        playerQ.offer(new int[]{playerLoc[0], playerLoc[1]});

        while (!boxQ.isEmpty()) {
            for (int i = 0, l = boxQ.size(); i < l; i++) { //as we care about all directions, it should be like this.--> it's related to calculating 'step'
                int[] currBoxLoc = boxQ.poll();
                int[] currPlayerLoc = playerQ.poll();
                if (currBoxLoc[0] == targetLoc[0] && currBoxLoc[1] == targetLoc[1]) return step; // If box arrives at the target, it returns 'step'
                for (int j = 0; j < dir.length; j++) { // Checking all directions
                    if (visited[currBoxLoc[0]][currBoxLoc[1]][j]) continue;
                    int[] d = dir[j];
                    int r0 = currBoxLoc[0] + d[0], c0 = currBoxLoc[1] + d[1];  // where player stands, need a space to push
                    if (r0 < 0 || r0 >= m || c0 < 0 || c0 >= n || grid[r0][c0] == '#') continue; //if no space, ignore(/continue)
                    int r = currBoxLoc[0] - d[0], c = currBoxLoc[1] - d[1];  // the box location after pushed
                    if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '#') continue; // if no space for box, ignore(/continue)
                    if (!reachable(r0, c0, currBoxLoc, currPlayerLoc)) continue; // Check if the player can reach (r0, c0). if not, continue
                    visited[currBoxLoc[0]][currBoxLoc[1]][j] = true; // After pushed, the player is at 'currBoxLoc'.
                    boxQ.offer(new int[]{r,c}); // update queues accordingly.
                    playerQ.offer(new int[]{currBoxLoc[0],currBoxLoc[1]});
                }
            }
            step++;
        }
        return -1;
    }

    private boolean reachable(int i, int j, int[] boxLoc, int[] playerLoc) {
        // (i,j) is a location where the play will push the box.
        Queue<int[]> q = new LinkedList<>();
        q.offer(playerLoc);
        boolean[][] visited = new boolean[m][n];
        visited[boxLoc[0]][boxLoc[1]] = true; //player cannot go through the spot where the box is located at.
        while (!q.isEmpty()) {
            int[] currPlLoc = q.poll();
            if (currPlLoc[0] == i && currPlLoc[1] == j) return true;
            for (int[] d : dir) {
                int r = currPlLoc[0] + d[0], c = currPlLoc[1] + d[1];   // player's location after moving
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == '#') continue; // check if player can move to (r,c)
                visited[r][c] = true; // if possible, check it visited.
                q.offer(new int[]{r, c});
            }
        }
        return false;
    }
}
