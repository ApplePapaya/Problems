package com.run.leetcode.Djikstra;

import java.util.PriorityQueue;

/**
 * Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
 *
 * The score of a path is the minimum value in that path.
 *
 *     For example, the score of the path 8 ? 4 ? 5 ? 9 is 4.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 * Explanation: The path with the maximum score is highlighted in yellow.
 *
 * Example 2:
 *
 * Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 *
 * Example 3:
 *
 * Input: grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 100
 *     0 <= grid[i][j] <= 109
 */
public class PathWithMaximumMinimumValue1102 {
    static class Cell {
        int i, j, val;
        Cell(int i, int j, int val) { this.i = i; this.j = j; this.val = val; }
    }

    static int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] A) {
        PriorityQueue<Cell> q = new PriorityQueue<>((a, b) -> b.val - a.val);
        q.add(new Cell(0, 0, A[0][0]));
        boolean[][] discovered = new boolean[A.length][A[0].length];
        discovered[0][0] = true;
        int min = A[0][0];
        while (!q.isEmpty()) {
            Cell bestMove = q.poll();
            if (bestMove.val < min) min = bestMove.val;

            if (bestMove.i == A.length - 1 && bestMove.j == A[0].length - 1) {
                return min;
            }
            for (int[] move : moves) {
                int i = bestMove.i + move[0];
                int j = bestMove.j + move[1];
                if (i >= 0 && i < A.length && j >= 0 && j < A[0].length && !discovered[i][j]) {
                    q.add(new Cell(i, j, A[i][j]));
                    discovered[i][j] = true;
                }
            }
        }
        throw new IllegalStateException("Unable to reach end cell");
    }
}
