package com.run.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 317. Shortest Distance from All Buildings
 * Hard
 *
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 *     each 0 marks an empty land that you can pass by freely,
 *     each 1 marks a building that you cannot pass through, and
 *     each 2 marks an obstacle that you cannot pass through.
 *
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 * Example 2:
 *
 * Input: grid = [[1,0]]
 * Output: 1
 *
 * Example 3:
 *
 * Input: grid = [[1]]
 * Output: -1
 *
 *
 *
 * Constraints:
 *
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 100
 *     grid[i][j] is either 0, 1, or 2.
 *     There will be at least one building in the grid.
 *
 *     Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)
 */
public class ShortestDistanceFromAllBuildings317 {

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings317 s = new ShortestDistanceFromAllBuildings317();
        int[][] grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(s.shortestDistance(grid));
    }
    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int totalBuildings = 0;
        int[][] reach = new int[rows][cols];
        int[][] dist = new int[rows][cols];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(grid[i][j] == 1){
                    totalBuildings++;
                    bfs(grid, i , j, reach, dist);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(reach[i][j] == totalBuildings && dist[i][j] < minDistance)
                    minDistance = dist[i][j];
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;

    }
    int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private void bfs(int[][] grid, int row, int col, int[][] reach, int[][] dist){
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        q.offer(new int[]{row, col});
        visited[row][col] = true;
        int d = 0;
        while(!q.isEmpty()){
            int size = q.size();
            d++;
            for(int i = 0 ; i < size; i++){
                int[] curr = q.poll();
                //will go in all 4 directions from this index

                for(int[] dir : dirs)
                {
                    int rr = curr[0] + dir[0];
                    int cc = curr[1] + dir[1];
                    if(!isValid(grid, rr, cc, visited)) continue;

                    q.offer(new int[]{rr, cc});
                    visited[rr][cc] = true;
                    reach[rr][cc]++;
                    dist[rr][cc] += d;
                }

            }
        }

    }

    private boolean isValid(int[][] grid, int rr, int cc, boolean[][] visited){
        if(rr < 0 || cc < 0 || rr >= grid.length || cc >= grid[0].length)
            return false;
        if(visited[rr][cc])
            return false;
        if(grid[rr][cc] != 0)
            return false;

        return true;
    }


    /**
     * Step 1: start from every point 1 (building point), doing BFS traversal to fill out (or accumulate) distance array
     * (MUST start over and doing every BFS traversal individually)
     *
     * Step 2: find minimum distance from dp array
     *
     * @param dp: store distance sum to all building for every point 0. Update values when we do BFS traversal
     * @param reach: store number of buildings that every point 0 can reach. Used for checking if current point is valid
     *             while we want to find final result
     * @param countBuilding: count total number of point 1
     * */
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistanceWithArrayDeque(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int[][] reach = new int[m][n];
        int countBuilding = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> zeroQueue = new ArrayDeque<>();

        // step 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    countBuilding++;
                    queue.offer(new int[]{i, j});
                    bfs(queue, grid, dp, reach, m, n, countBuilding);
                } else if (grid[i][j] == 0){
                    zeroQueue.offer(new int[]{i, j});
                }
            }
        }

        // step 2
        int result = Integer.MAX_VALUE;
        while(!zeroQueue.isEmpty()) {
            int[] cur = zeroQueue.poll();
            int x = cur[0];
            int y = cur[1];
            if (reach[x][y] == countBuilding) {
                result = Math.min(result, dp[x][y]);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public void bfs(Queue<int[]> queue, int[][] grid, int[][] dp, int[][] reach, int m, int n, int countBuilding) {
        int level = 1;
        boolean[][] visited = new boolean[m][n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curPoint = queue.poll();
                int x = curPoint[0];
                int y = curPoint[1];
                for (int[] dir: DIRS) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || nx >= m  || ny < 0 || ny >= n || grid[nx][ny] != 0 || visited[nx][ny]) {
                        continue;
                    }
                    visited[nx][ny] = true;
                    dp[nx][ny] += level;
                    reach[nx][ny]++;
                    if (reach[nx][ny] == countBuilding) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            level++;
        }
    }
}
