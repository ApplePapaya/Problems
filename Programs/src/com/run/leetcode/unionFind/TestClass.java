package com.run.leetcode.unionFind;

import java.util.Arrays;

public class TestClass {

    public static void main(String[] args){
        TestClass t = new TestClass();
        System.out.println(t.minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}}));
    }
    public int minimumCost(int N, int[][] connections) {


        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int res = 0;
        UnionFind uf = new UnionFind(N);
        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (uf.find(x) != uf.find(y)) {
                res += c[2];
                uf.union(x, y);
            }
        }

        return uf.n == 1 ? res : -1;
    }

    class UnionFind{

        int[] parent;
        private int[] size;
        int n;

        UnionFind(int N){
            parent = new int[N+1];
            size = new int[N+1];
            n = N;
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        private void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {

                if (size[px] < size[py]) {
                    size[py] += size[px];
                    size[px] = py;
                } else {
                    size[px] += size[py];
                    parent[py] = px;
                }
               /// parent[px] = py;
                n--;
            }
        }

        private int find(int p) {
            // Find the root of the component/set
            int root = p;
            while (root != parent[root])
                root = parent[root];

            // Compress the path leading back to the root.
            // Doing this operation is called "path compression"
            // and is what gives us amortized time complexity.
            while (p != root) {
                int next = parent[p];
                parent[p] = root;
                p = next;
            }

            return root;
        }
    }
}
