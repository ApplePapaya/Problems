package com.run.leetcode.Djikstra;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * Example 3:
 *
 *
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 * Example 4:
 *
 * Input: grid = [[2,2,2],[2,2,2]]
 * Output: 3
 * Example 5:
 *
 * Input: grid = [[4]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 */
public class MinimumCostToMakeAtleastOneValidPathInAGrid1368 {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }

    public int minCost(int[][] grid) {
        int[][] direction = new int[][]{{0, 1}, {0 , -1}, {1, 0}, {-1, 0}};
        int[][] cost = new int[grid.length][grid[0].length];
        for(int[] a : cost) Arrays.fill(a, Integer.MAX_VALUE);
        cost[0][0] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            return cost[p1.x][p1.y] - cost[p2.x][p2.y];
        });

        pq.add(new Point(0, 0));
        while(!pq.isEmpty()) {
            Point curr = pq.poll();
            int freeDirection = grid[curr.x][curr.y] - 1;
            for(int i = 0; i < direction.length; i++) {
                int targetX = curr.x + direction[i][0];
                int targetY = curr.y + direction[i][1];
                if(targetY >= grid[0].length || targetY < 0
                        || targetX >= grid.length || targetX < 0) continue;
                if(i == freeDirection && cost[targetX][targetY] > cost[curr.x][curr.y]) {
                    cost[targetX][targetY] = cost[curr.x][curr.y];
                    pq.add(new Point(targetX, targetY));
                } else if (cost[targetX][targetY] > cost[curr.x][curr.y] + 1) {
                    cost[targetX][targetY] = cost[curr.x][curr.y] + 1;
                    pq.add(new Point(targetX, targetY));
                }
            }
        }
        return cost[grid.length - 1][grid[0].length - 1];
    }


    //////////////////////////////////////
    class Tuple {
        int row, col, cost;
        public Tuple(int r, int c, int co) {
            row = r;
            col = c;
            cost = co;
        }
    }//Djikstra
    public int minCost34(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int targetRow = grid.length-1, targetColumn = grid[0].length-1;

        boolean[][] traversed = new boolean[rows][columns];
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b)->(a.cost - b.cost));

        pq.add(new Tuple(0, 0, 0));

        while(!pq.isEmpty()) {
            Tuple top = pq.poll();
            int row = top.row, col = top.col;
            traversed[row][col] = true;
            if(row == targetRow && col == targetColumn) {
                return top.cost;
            }
            // traverse neighbors
            if(col + 1 != columns && !traversed[row][col+1])
                pq.add(new Tuple(row, col+1, (grid[row][col] == 1 ? 0 : 1) + top.cost ));

            if(col - 1 != -1 && !traversed[row][col-1])
                pq.add(new Tuple(row, col-1, (grid[row][col] == 2 ? 0 : 1) + top.cost ));

            if(row + 1 != rows && !traversed[row+1][col])
                pq.add(new Tuple(row+1, col, (grid[row][col] == 3 ? 0 : 1) + top.cost ));

            if(row - 1 != -1 && !traversed[row-1][col])
                pq.add(new Tuple(row-1, col, (grid[row][col] == 4 ? 0 : 1) + top.cost ));

        }
        return 0;
    }
    //////////////////////////////
    final int[] x= {0, 0, 0, 1, -1};
    final int[] y= {0, 1, -1, 0, 0};

    public int minCost66(int[][] grid) {

        Queue<int[]> bfs= new LinkedList<>();
        int[][] dp= new int[grid.length][grid[0].length];

        for(int[] e: dp){
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        int cost=0;

        dfs(grid, 0, 0, cost, bfs, dp);

        while(!bfs.isEmpty()){
            cost++;
            for(int k=bfs.size(); k>0; k--){
                int[] curr=bfs.poll();
                int r=curr[0], c=curr[1];
                for(int i=1; i<=4; i++){
                    dfs(grid, r+x[i], c+y[i], cost, bfs, dp);
                }
            }

        }

        return dp[grid.length-1][grid[0].length-1];

    }

    private void dfs(int[][] grid, int r, int c, int cost, Queue<int[]> bfs, int[][] dp){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || dp[r][c]!=Integer.MAX_VALUE){
            return;
        }
        dp[r][c]= cost;
        bfs.offer(new int[]{r, c});
        int nextDir=grid[r][c];

        dfs(grid, r+x[nextDir], c+y[nextDir], cost, bfs, dp);
    }



    int INF = (int) 1e9;
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost22(int[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], INF);
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
            }
        }
        return dp[m - 1][n - 1];
    }

    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        int m = grid.length; int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c}); // add to try change direction later
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }
}
