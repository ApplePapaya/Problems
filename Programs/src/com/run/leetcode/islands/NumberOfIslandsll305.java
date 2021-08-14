package com.run.leetcode.islands;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsll305 {
    // O(m * n + positions.length) time, because the UF constructor costs O(m * n) time (does initializing a 2D array cost the size of the array?), and we do at most 4 union() calls for each element in positions, one union() call costs O(1) time in practice (in theory its very very sligthly above O(1)).
    //The Union-Find solution can be just O(L) if you don't initialize the data structure upfront and do it only on demand--in my implementation when I move to a new position in the list I initialize it (with parent and rank) in the data structure, and if a node does not exist in the parent dict it is assumed to be a zero/water.
    //Tighter bound is O(L), but yes L approaches mn in the worst case.
// O(m * n) space because of the UF parent array.

    public static void main(String[] args) {
        NumberOfIslandsll305 d = new NumberOfIslandsll305();
        System.out.println(d.numIslands2(3, 3, new int[][] {{0,0},{0,1},{1,2},{2,1}}));
    }
    int[] parent;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        parent = new int[n * m + 1];
        List<Integer> res = new ArrayList<>();
        int num = 0;
        for (int[] po: positions) {
            int row = po[0];
            int col = po[1];
            int offset = row * n + col + 1;
            if (parent[offset] != 0) {
                res.add(num);
                continue;
            }
            parent[offset] = offset;
            num ++;
            //Check the same column one row above
            if (row > 0 && parent[offset - n] != 0 && union(offset, offset - n)) {
                num --;
            }
            //check the same column one row below
            if (row < m - 1 && parent[offset + n] != 0 && union(offset, offset + n)) {
                num --;
            }
            //same row, one column to left
            if (col > 0 && parent[offset - 1] != 0 && union(offset, offset - 1)) {
                num --;
            }
            if (col < n - 1 && parent[offset + 1] != 0 && union(offset, offset + 1)) {
                num --;
            }
            res.add(num);
        }
        return res;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    private boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY) return false;
        parent[parentX] = parentY;
        return true;
    }
    /*************END ****************************************************/
    int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<Integer> numIslands28ms(int m, int n, int[][] positions) {

        List<Integer> list = new ArrayList<>();
        int[] parent = new int[m*n];
        Arrays.fill(parent,-1);
        int count = 0;

        for(int[] position:positions){
            int x = position[0];
            int y = position[1];
            int p = x*n+y;
            if(parent[p]!=-1){
                list.add(count);
                continue;
            }
            parent[p] = p;
            count++;
            for(int[]direction:directions){
                int newx = x+direction[0];
                int newy = y+direction[1];
                int newp = newx*n+newy;
                if(newx < 0 || newx >= m || newy < 0 || newy >= n ||parent[newp]==-1){
                    continue;
                }
                int newroot = findisland(parent,newp);
                if(newroot!=p){
                    parent[p] = newroot;
                    p = newroot;
                    count--;
                }
            }
            list.add(count);
        }
        return list;
    }
    int findisland(int[]parent,int newp){
        if(newp!=parent[newp]){
            parent[newp] = findisland(parent,parent[newp]);
        }
        return parent[newp];
    }

    public int find(int p, int[] parent) {
        int pRoot = p;
        while (pRoot != parent[pRoot]) {
            pRoot = parent[pRoot];
        }

        // Path compression: make every node on the path from p to its root point to the root directly.
        while (p != parent[p]) {
            int temp = parent[p];
            parent[p] = pRoot;
            p = temp;
        }

        return pRoot;
    }
}
