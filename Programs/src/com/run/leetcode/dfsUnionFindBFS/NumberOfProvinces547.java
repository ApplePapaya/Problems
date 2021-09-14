package com.run.leetcode.dfsUnionFindBFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces547 {
    //DFs
    /**
     There are 3 solutions to this problem
     1> DFS
     HEre we create a visited boolean array/int array to record which nodes are visited. Althought this is matrix
     it is basically symmetric undirected graph. meaning, only the upper right diagonal half is what matters.
     1> Initialise an int array of size M.length = number of friends
     2> Initialize count =0
     3> For i = 0 to M.length, we will check if the visited value for that element is 0 then do dfs for that node
     4> Increment the count since we have found one circle, we will mark all nodes as visited by doing dfs from this node.
     5> The dfs function is simple function which loops through the  0 to M - 1 and checks if the value at i/j is 1 and not yet visisted j then mark it visited and further dfs on j now.
     6> Here if 1 is friend of 2 and 2 of 3 then they form one connected component. Once we do dfs from 1and mark 2 and 3 as visited. there is no need to do dfs on 3 since 3 is no way connected to other nodes.

     We dont have to check every node or element in the matrix like islands problem, since if 1 is friend off 2 then 2 is friend of 1.


     **/

    public void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0 ; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == false) {
                visited[j] = true;
                dfs(M, visited, j );
            }
        }
    }
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == false) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    //BFS
    public int findCircleNumBFS(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue< Integer > queue = new LinkedList< >();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     * UNION FIND
     */

    class UF{
        private int[] parent, size;
        private int count;

        public UF(int n){
            parent = new int[n];
            size = new int[n];
            count = n;
            for(int i = 0; i < n; i++)
            {
                parent[i] = i;
            }
        }
        // Find which component/set 'p' belongs to, takes amortized constant time.
        public int find(int p){
            int root = p;
            // Find the root of the component/set
            while(root != parent[root])
                root = parent[root];
            // Compress the path leading back to the root.
            // Doing this operation is called "path compression"
            // and is what gives us amortized time complexity.
            while(p != root){
                int next = parent[p];
                parent[p] = root;
                p = next;
            }
            return root;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            // union by size
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int count() {
            return count;
        }

    }
    public int findCircleNum2(int[][] M) {
        int n = M.length;
        UF uf = new UF(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }

}
