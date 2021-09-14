package com.run.leetcode.bfsdfs;
/**
 * This is an interactive problem.
 *
 * There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size m x n, and each cell in the grid is either empty or blocked. It is guaranteed that the starting cell and the target cell are different, and neither of them is blocked.
 *
 * You want to find the minimum distance to the target cell. However, you do not know the grid's dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the GridMaster object.
 *
 * Thr GridMaster class has the following functions:
 *
 *     boolean canMove(char direction) Returns true if the robot can move in that direction. Otherwise, it returns false.
 *     void move(char direction) Moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, the move will be ignored, and the robot will remain in the same position.
 *     boolean isTarget() Returns true if the robot is currently on the target cell. Otherwise, it returns false.
 *
 * Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions up, down, left, and right, respectively.
 *
 * Return the minimum distance between the robot's initial starting cell and the target cell. If there is no valid path between the cells, return -1.
 *
 * Custom testing:
 *
 * The test input is read as a 2D matrix grid of size m x n where:
 *
 *     grid[i][j] == -1 indicates that the robot is in cell (i, j) (the starting cell).
 *     grid[i][j] == 0 indicates that the cell (i, j) is blocked.
 *     grid[i][j] == 1 indicates that the cell (i, j) is empty.
 *     grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
 *
 * There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,2],[-1,0]]
 * Output: 2
 * Explanation: One possible interaction is described below:
 * The robot is initially standing on cell (1, 0), denoted by the -1.
 * - master.canMove('U') returns true.
 * - master.canMove('D') returns false.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns false.
 * - master.move('U') moves the robot to the cell (0, 0).
 * - master.isTarget() returns false.
 * - master.canMove('U') returns false.
 * - master.canMove('D') returns true.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns true.
 * - master.move('R') moves the robot to the cell (0, 1).
 * - master.isTarget() returns true.
 * We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
 *
 * Example 2:
 *
 * Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]
 * Output: 4
 * Explanation: The minimum distance between the robot and the target cell is 4.
 *
 * Example 3:
 *
 * Input: grid = [[-1,0],[0,2]]
 * Output: -1
 * Explanation: There is no path from the robot to the target cell.
 */

/**
 * The original grid is hidden to us and so we try to map that in the 4x grid using the master object given to us.
 * Now, why do we need to run DFS and map the master grid to our 4x grid?
 * Because we cannot freely run the BFS from start just like we do in a normal way by storing the xy co-ordinates layer by layer using the master grid, because it is hidden and we cannot store the xy of it.
 * So what we do? -> DFS
 * We map the whole master grid just like copying that into our 4x grid using DFS and taking the start point of master grid as our center in 4x grid. We can do this in one run of DFS because we are not interested in storing the xy coordinates in DFS, we go as deep as possible and then we backtrack if we get stuck to explore a different route. This way the whole hidden master grid can be mapped to our 4x grid in O(mxm) time, and we do map the whole grid and not just till we find the target in DFS. Since that is not sufficient to get the shortest path. (DFS doesn't give shortest path)
 * BFS
 * Then we run our old style BFS on the mapped 4x grid from the point m,m and find the target if it is reacheable. (BFS gives the shortest path)
 */

import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathInAHiddenGrid1778 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static char[] FORWARD = {'U', 'D', 'L', 'R'};
    private static char[] BACKWARD = {'D', 'U', 'R', 'L'};

    private static final int UNVISITED = 0;
    private static final int PATH = 1;
    private static final int BLOCK = -1;

    private int N = 500;
    private int[][] board = new int[2 * N + 1][2 * N + 1];

    private GridMaster master;
    private int[] target;

    public int findShortestPath(GridMaster master) {
        this.master = master;

        dfs(N, N);
        return (target == null ? -1 : bfs());
    }

    private int bfs() {
        Deque<int[]> queue = new LinkedList<>();
        queue.offerLast(new int[]{N, N});
        board[N][N] = BLOCK;                                  // mark as visited

        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.pollFirst();
                int x = curr[0];
                int y = curr[1];

                for (int d = 0; d < 4; d++) {
                    int newX = x + DIRECTIONS[d][0];
                    int newY = y + DIRECTIONS[d][1];

                    if (board[newX][newY] == BLOCK) continue;
                    if (newX == target[0] && newY == target[1]) return dist + 1;

                    board[newX][newY] = BLOCK;                 // mark as visited
                    queue.offerLast(new int[]{newX, newY});    // add to queue
                }
            }
            dist++;
        }

        return -1;
    }

    private void dfs(int x, int y) {
        if (master.isTarget()) target = new int[]{x, y};

        board[x][y] = PATH;    // mark current cell

        for (int d = 0; d < 4; d++) {
            int newX = x + DIRECTIONS[d][0];
            int newY = y + DIRECTIONS[d][1];

            char front = FORWARD[d];    // direction of moving
            char back = BACKWARD[d];    // direction of backtracking

            if (board[newX][newY] != UNVISITED) continue;

            if (!master.canMove(front)) {
                board[newX][newY] = BLOCK;
            } else {
                master.move(front);
                dfs(newX, newY);
                master.move(back);
            }
        }

    }

    /*******************************************************************************/
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //Uncomment for below methods
  //  private static final char[] FORWARD = {'U', 'D', 'L', 'R'};
  //  private static final char[] BACKWARD = {'D', 'U', 'R', 'L'};

  //  private static final int UNVISITED = 0;
  //  private static final int PATH = 1;
    private static final int TARGET = 2;
 //   private static final int BLOCK = -1;

    public int findShortestPath2(GridMaster master) {
        int N = 500;
        int[][] grid = new int[2 * N][2 * N];

        // [N, N] is start, mark it as PATH in dfs
        dfs(N, N, master, grid);

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{N, N});
        grid[N][N] = BLOCK; // here use BLOCK to mark visited in BFS
        int dis = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int p = cur[0] + DIRS[i][0];
                    int q = cur[1] + DIRS[i][1];
                    if (grid[p][q] == TARGET) return dis + 1;
                    if (grid[p][q] == BLOCK) continue;
                    queue.offer(new int[]{p, q});
                    grid[p][q] = BLOCK;
                }
            }
            dis++;
        }

        return -1;
    }

    private void dfs(int r, int c, GridMaster master, int[][] grid) {
        if (grid[r][c] != UNVISITED) return;
        if (master.isTarget())
            grid[r][c] = TARGET;
        else
            grid[r][c] = PATH;

        for (int i = 0; i < 4; i++) {
            char forward = FORWARD[i];
            char backward = BACKWARD[i];
            int p = r + DIRS[i][0];
            int q = c + DIRS[i][1];

            if (!master.canMove(forward)) {
                grid[p][q] = BLOCK;
            } else {
                master.move(forward);
                dfs(p, q, master, grid);
                master.move(backward);
            }
        }
    }

    interface GridMaster {
        boolean canMove(char direction);

        void move(char direction);

        boolean isTarget();
    }
}
