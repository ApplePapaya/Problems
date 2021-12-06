package com.run.leetcode.Djikstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort1631 {

    /**

     The answer lies in the fact that: in the min-path-sum question, you can only move right and down, while here you can move four-directionally. This difference rules out that you can use DP efficiently here. Basically in DP you are trying to update your new state based on states that you previously seen. When you can move four-directionally, based on your state definition, say dp[i][j] := min cost to reach (i,j) from (0,0),then if optimal path to reach (i,j) comes from the cell below (i+1,j), then this is actually a state you've not calculated yet.

     One way to realize that it isn't dynamic programming is to notice that the hiker can go in all four directions. This means that a dp algorithm would need to look into subproblems that haven't been solved yet.
     This problem does not comply with the Bellman principle, we can't choose the most optimal solution at each step. Hence not DP.

     why we can use Dijkstra here:

     here, the total path cost is the maximum absolute difference, its a different kind of cost function compared to the vanilla adding all costs together along the path
     for Dijkstra, edge weight cannot be negative, but what does that really mean. It effectively means total path cost cannot go down when a new edge joins the path. In the vanilla Dijkstra, it's in the form of negative edge weight. If we translate that to this problem, we can see that the total path cost (maximum absolute difference) will never go down when a new edge joins the path


     ***/

    /**

     Algorithm

     We use a differenceMatrix of size row?col\text{row} \cdot \text{col}row?col where each cell represents the minimum effort required to reach that cell from all the possible paths. Also, initialize we all the cells in the differenceMatrix to infinity (MAX_INT)\text{(MAX\_INT)}(MAX_INT) since none of the cells are reachable initially.

     As we start visiting each cell, all the adjacent cells are now reachable. We update the absolute difference between the current cell and adjacent cells in the differenceMatrix. At the same time, we also push all the adjacent cells in a priority queue. The priority queue holds all the reachable cells sorted by its value in differenceMatrix, i.e the cell with minimum absolute difference with its adjacent cells would be at the top of the queue.

     We begin by adding the source cell (x=0, y=0) in the queue. Now, until we have visited the destination cell or the queue is not empty, we visit each cell in the queue sorted in the order of priority. The less is the difference value(absolute difference with adjacent cell) of a cell, the higher is its priority.

     Get the cell from the top of the queue curr and visit the current cell.

     For each of the 4 cells adjacent to the current cell, calculate the maxDifference which is the maximum absolute difference to reach the adjacent cell (adjacentX, adjacentY) from current cell (curr.x, curr.y).

     If the current value of the adjacent cell (adjacentX, adjacentY) in the difference matrix is greater than the maxDifference, we must update that value with maxDifference. In other words, we have found that the path from the current cell to the adjacent cell takes lesser efforts than the other paths that have reached the adjacent cell so far. Also, we must add this updated difference value in the queue.

     Ideally, for updating the priority queue, we must delete the old value and reinsert with the new maxDifference value. But, as we know that the updated maximum value is always lesser than the old value and would be popped from the queue and visited before the old value, we could save time and avoid removing the old value from the queue.

     At the end, the value at differenceMatrix[row - 1][col - 1] is the minimum effort required to reach the destination cell (row-1,col-1).
     Complexity Analysis

     Time Complexity : O(m?nlog?(m?n))\mathcal{O}(m \cdot n \log (m \cdot n))O(m?nlog(m?n)), where mmm is the number of rows and nnn is the number of columns in matrix heights\text{heights}heights. It will take O(m?n)\mathcal{O}(m \cdot n)O(m?n) time to visit every cell in the matrix. The priority queue will contain at most m?nm \cdot nm?n cells, so it will take O(log?(m?n))\mathcal{O}(\log (m \cdot n))O(log(m?n)) time to re-sort the queue after every adjacent cell is added to the queue. This given as total time complexiy as O(m?nlog?(m?n))\mathcal{O}(m \cdot n \log(m \cdot n))O(m?nlog(m?n)).

     Space Complexity: O(m?n)\mathcal{O}(m \cdot n)O(m?n), where mmm is the number of rows and nnn is the number of columns in matrix heights\text{heights}heights. The maximum queue size is equal to the total number of cells in the matrix height\text{height}height which is given by m?nm \cdot nm?n. Also, we use a difference matrix of size m?nm \cdot nm?n. This gives as time complexity as O(m?n+m?n)\mathcal{O}(m \cdot n + m \cdot n)O(m?n+m?n) = O(m?n)\mathcal{O}(m \cdot n)O(m?n)


     **/
    public int minimumEffortPath(int[][] heights) {
        int M = heights.length;
        int N = heights[0].length;

        int[][] costs = new int[M][N];
        for (int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }

        costs[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(0, 0, 0));

        int[] rowOffset = new int[] { -1, 0, 1, 0 };
        int[] colOffset = new int[] { 0, -1, 0, 1 };
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int oldCost = node.cost;
            int oldRow = node.row;
            int oldCol = node.col;

            if (oldRow == M - 1 && oldCol == N - 1) {
                return oldCost;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = oldRow + rowOffset[i];
                int newCol = oldCol + colOffset[i];

                if (newRow < 0 || newRow > M - 1 ||
                        newCol < 0 || newCol > N - 1) {
                    continue;
                }

                int newCost = Math.max(oldCost, Math.abs(heights[newRow][newCol] - heights[oldRow][oldCol]));
                if (costs[newRow][newCol] > newCost) {
                    costs[newRow][newCol] = newCost;
                    pq.offer(new Node(newCost, newRow, newCol));
                }
            }
        }
        return -1;

    }

    public class Node {
        int cost;
        int row;
        int col;

        public Node(int cost, int row, int col) {
            this.cost = cost;
            this.row = row;
            this.col = col;
        }
    }
}

class Solution2 {
    int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] differenceMatrix = new int[row][col];
        for (int[] eachRow : differenceMatrix)
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        differenceMatrix[0][0] = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] visited = new boolean[row][col];
        queue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            visited[curr.x][curr.y] = true;
            if (curr.x == row - 1 && curr.y == col - 1)
                return curr.difference;
            for (int[] direction : directions) {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[row - 1][col - 1];
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
}

class Cell {
    int x;
    int y;
    Integer difference;

    Cell(int x, int y, Integer difference) {
        this.x = x;
        this.y = y;
        this.difference = difference;
    }
}
