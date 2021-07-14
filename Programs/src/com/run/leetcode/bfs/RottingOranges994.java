package com.run.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;
/**
 Here we need to do BFS

 1> First check if the grid is not null and not of length 0;
 2> Get the rows and cols for the grid.
 3> Intialize a queue of int[] which will store the location of each and every rotten oranges with row, col value.
 4> Iterate through the grid and for each grid with value == 2 which implies rotten, we will add it to the queue.
 5> If the grid has value of 1 which implies fresh orange, increment value of fresh oranges.
 6> Once outside the loop check the value of fresh oranges, it its 0 then just return 0. No fresh oranges to get spoiled.
 7> Now we will iterate through the queue while its not empty.
 8> we will intialize a direction array for all 4 directions.
 9> Inside the while queue block, check the size of the queue.
 10> Increment time++
 11> For each element in the queue, check if there are surrounding elements using dir within the range of the matrix and with value 1. If yes populate the queue.
 12> We edont need visisted set or boolean array since we are manipulating the input array itself.
 13> Also decrement the count of fresh oranges in this step. For optimization if the oranges are rotten here itself we can save further loops.
 14> if count of fresh oranges outside loop is 0 then return the count - 1 else -1

 **/
public class RottingOranges994 {


    static int [][] pathArr = {{-1,0},{0,1},{1,0},{0,-1}};
    //This solution is 1 ms
    public int orangesRotting2(int[][] grid) {
        int timeStamp = 2;
        while(Rotted(timeStamp,grid)) {
            timeStamp++;
        }
        for(int i  = 0; i<grid.length; i++) {
            for(int j = 0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }
        return timeStamp-2;
    }
    static boolean Rotted(int val,int [][] grid) {
        boolean isRotted = false;
        for(int i = 0; i< grid.length; i++) {
            for(int j = 0; j<grid[0].length; j++) {
                if (grid[i][j] == val) {
                    for(int k = 0; k<4; k++) {
                        int row = i;
                        int col = j;
                        row += pathArr[k][0];
                        col += pathArr[k][1];
                        if(isValid(row,col,grid)) {
                            isRotted = true;
                            grid[row][col] = val + 1;

                        }
                    }

                }
            }
        }
        return isRotted;
    }
    static boolean isValid(int i , int j , int [][] grid) {
        if (i == -1 || j == -1 || i >= grid.length || j >= grid[0].length) {
            return false;
        } else {
            if (grid[i][j] == 1) {
                return true;
            } else {
                return false;
            }
        }
    }



    /***************This solution is 3 ms*********************************************************/
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0]. length;

        int countMin = 0;
        int countFreshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(grid[i][j] == 2)
                    queue.add(new int[]{i, j});
                else if(grid[i][j] == 1)
                {
                    countFreshOranges++;
                }
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        if(countFreshOranges == 0)
            return 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            countMin++;

            for(int i = 0; i < size; i++)
            {
                int[] node = queue.poll();

                for(int[] dir : dirs)
                {
                    int dx = node[0] + dir[0];
                    int dy = node[1] + dir[1];

                    if(isValidM(dx, dy, grid))
                    {
                        grid[dx][dy] = 2;
                        queue.add(new int[]{dx, dy});
                        countFreshOranges--;// can exit here if zero??
                        if(countFreshOranges == 0)
                            return countMin;
                    }

                }


            }
        }
        return -1;
        //  return countFreshOranges == 0 ? countMin - 1 : -1;
    }

    private boolean isValidM(int dx, int dy, int[][] grid){
        if(dx < 0 || dy < 0 || dx >= grid.length || dy >= grid[0].length || grid[dx][dy] != 1)
            return false;

        return true;
    }
}
