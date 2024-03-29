package com.run.leetcode.dfs;
/**
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 *
 * The robot starts at an unknown location in the root that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
 *
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 *
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using the following APIs:
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 *
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 *
 *
 *
 * Custom testing:
 *
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.
 *
 *
 *
 * Example 1:
 *
 * Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
 * Output: Robot cleaned all rooms.
 * Explanation: All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 *
 * Example 2:
 *
 * Input: room = [[1]], row = 0, col = 0
 * Output: Robot cleaned all rooms.
 *
 *
 *
 * Constraints:
 *
 *     m == room.length
 *     n == room[i].length
 *     1 <= m <= 100
 *     1 <= n <= 200
 *     room[i][j] is either 0 or 1.
 *     0 <= row < m
 *     0 <= col < n
 *     room[row][col] == 1
 *     All the empty cells can be visited from the starting position.
 */

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner489 {
    /**
     * This solution is based on the same idea as maze solving algorithm called right-hand rule.
     * Go forward, cleaning and marking all the cells on the way as visited.
     * At the obstacle turn right, again go forward, etc. Always turn right at the obstacles and then go forward.
     * Consider already visited cells as virtual obstacles.
     * Algorithm
     *
     * Time to write down the algorithm for the backtrack function backtrack(cell = (0, 0), direction = 0).
     *
     *     Mark the cell as visited and clean it up.
     *
     *     Explore 4 directions : up, right, down, and left (the order is important since the idea is always to turn right) :
     *
     *         Check the next cell in the chosen direction :
     *
     *             If it's not visited yet and there is no obtacles :
     *
     *                 Move forward.
     *
     *                 Explore next cells backtrack(new_cell, new_direction).
     *
     *                 Backtrack, i.e. go back to the previous cell.
     *
     *             Turn right because now there is an obstacle (or a virtual obstacle) just in front.
     */
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
 //   Set<Pair<Integer, Integer>> visited = new HashSet();
 Set<String> visited = new HashSet();
    Robot robot;

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backtrack(int row, int col, int d) {
        visited.add(row +"-" + col);
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];

            if (!visited.contains(newRow+"-"+newCol) && robot.move()) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }
}
interface Robot {
    public boolean move();
    public void turnLeft();
    public void turnRight();
    public void clean();
}
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */