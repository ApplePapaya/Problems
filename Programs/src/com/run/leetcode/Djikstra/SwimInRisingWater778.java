package com.run.leetcode.Djikstra;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 *
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 */
public class SwimInRisingWater778 {

    /**
     This problem can be solved in 3 ways
     1> PriorityQueue -s imple
     2> union find
     3> DFS

     PriorityQueue Solution
     1> Initialize a priority queue of int[] and define the comparator as
     (a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]
     which means the priority queue is going to hold the row col value of the grid element. And priority queue will sort based on the value of that element in the grid with minimum value at the top of the queue.
     2> Intialize level = 0. This si the level which will be returned and will track the time taken to reach at a particular index.
     3> Insert int[] {0, 0} into the priority queue
     4> while the queue is not empty
     a> pop up the top element from the queue.
     b> level = Max value of level  or the grid value.
     c> is the top element indices the last cell int he matrix n-1 , n-1
     if yes then break the loop and return the level.
     d> else we need to traverse all 4 directions and see if the the location is within limits and not yet visited, then mark it visited and add it to the priority queue.
     5> return the level once the loop is completed

     **/
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        int time = 0;
        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[] {0, 0});
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(pq.isEmpty() == false)
        {
            int[] top = pq.poll();
            time = Math.max(time, grid[top[0]][top[1]]);
            if(top[0] == n - 1 && top[1] == n - 1)
                break;

            for(int[] dir : dirs)
            {
                int x = top[0] + dir[0];
                int y = top[1] + dir[1];

                if(isValid(x, y, visited, n))
                {
                    visited[x][y] = true;
                    pq.offer(new int[] {x, y});
                }
            }


        }
        return time;



    }

    private boolean isValid(int x, int y, boolean[][] visited, int n){
        if(x < 0 || x > n - 1 || y < 0 || y > n -1 || visited[x][y] == true)
            return false;

        return true;
    }
}
