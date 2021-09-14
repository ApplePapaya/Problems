package com.run.leetcode.dfs;
/**
 * 1036. Escape a Large Maze
 * Hard
 *
 * 340
 *
 * 123
 *
 * Add to List
 *
 * Share
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
 *
 * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
 *
 * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.
 *
 * Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.
 *
 *
 *
 * Example 1:
 *
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation: The target square is inaccessible starting from the source square because we cannot move.
 * We cannot move north or east because those squares are blocked.
 * We cannot move south or west because we cannot go outside of the grid.
 * Example 2:
 *
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation: Because there are no blocked cells, it is possible to reach the target square.
 *
 *
 * Constraints:
 *
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 106
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 106
 * source != target
 * It is guaranteed that source and target are not blocked.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 https://www.youtube.com/watch?v=HuYnIDHXqMM

 Here we need to see if the target can be reached from the source. Since the maze is huge we cant traverse full bfs and dfs since it will be TLE
 So we can consider either checking for Manhattan distance or total area coverd.
 Since block size is 200 we can see if we are able to reach the current position away from source and has greater than 200 steps, then we can escape the source. however target could be trapped.
 So we need to do dfs from target as well.
 So steps
 1> Create a set to capture the indices of blocked. We can either consider string or use unique index formula of x * rows + y.
 2> We need to dfs from source to target with current index as source passing the blocks and visited set as neew hashset.
 3> Similarlu we need to dfs from target as well.

 DFS method
 1> Inside dfs method the base condition is if we have reached destination - Current index == target for both x and y . If yes return true.
 2> If Manhattan distance which is |x1-x2| + |y1 - y2| is greater than 200( which is block length) . if the current index is greater than that then it means target can be reached from source if target also satisifies similar condition.
 3> Now we add this index of current location to visited set.
 4> For each directiont hat we can have the next step, we check for the row and column index
 5> if the row is between 0 and 10^6 -1 both inclusive and so is col and the index ( mathed) is not present in the block nor in visited, do a further dfs on that index and if thats true return early.
 6> We do this from both source and target since we are limiting to 200 steps, that is block length


 **/
public class EscapeALargeMaze1036 {
    static int dirs[][] = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
    static int limit = (int)1e6;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target)        {
        Set<Integer> block = new HashSet<>();
        for(int[] b : blocked)
        {
            block.add(b[0] * limit + b[1]);
        }

        return dfs(source, target, source, block, new HashSet<>())
                &&    dfs(target, source, target, block, new HashSet<>());
    }


    private boolean dfs(int[] source, int[] target, int[] curr, Set<Integer> block, Set<Integer> visited){
        if(curr[0] == target[0] && curr[1] == target[1])
            return true;
        if(Math.abs(source[0] - curr[0]) + Math.abs(source[1] - curr[1]) > 200)
            return true;
        // if(visited.size() > 20000)// this is right but takes more time since more area to cover
        //     return true;

        visited.add(curr[0] * limit  + curr[1]);
        for(int[] d : dirs)
        {
            int r = curr[0] + d[0];
            int c = curr[1] + d[1];
            Integer str = r * limit  + c;

            if(r >= 0 && r < 1000000 &&
                    c >= 0 && c < 1000000 &&
                    !block.contains(r * limit + c) &&
                    !visited.contains(str))
            {
                if(dfs(source, target, new int[]{r, c}, block, visited))
                    return true;
            }
        }
        return false;
    }



    //static int dirs[][] = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
    //static int limit = (int)1e6;
    public boolean isEscapePossible22(int[][] blocked, int[] source, int[] target) {
        Set<String> blocks = new HashSet<>();
        for(int block[] : blocked)
            blocks.add(block[0] + ":" + block[1]);
        return bfs(source, target, blocks) && bfs(target, source, blocks);
    }
    public boolean bfs(int[] source, int[] target, Set<String> blocks){
        Set<String> seen = new HashSet<>();
        seen.add(source[0] + ":" + source[1]);
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(source);

        while(!bfs.isEmpty()){
            int cur[] = bfs.poll();
            for(int dir[] : dirs){
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];
                if(nextX < 0 || nextY < 0 || nextX >= limit || nextY >= limit) continue;
                String key = nextX + ":" + nextY;
                if(seen.contains(key) || blocks.contains(key)) continue;
                if(nextX == target[0] && nextY == target[1]) return true;
                bfs.offer(new int[]{nextX, nextY});
                seen.add(key);
            }
            if(seen.size() == 20000) return true;
        }
        return false;
    }
}
