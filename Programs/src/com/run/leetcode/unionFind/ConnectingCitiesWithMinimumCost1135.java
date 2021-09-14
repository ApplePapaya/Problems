package com.run.leetcode.unionFind;

import java.util.*;

public class ConnectingCitiesWithMinimumCost1135 {


    /**
     *
     * 1135. Connecting Cities With Minimum Cost
     * Medium
     *
     * There are n cities numbered from 1 to n.
     *
     * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
     *
     * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
     * Output: 6
     * Explanation:
     * Choosing any 2 edges will connect all cities so we choose the minimum 2.
     *
     * Example 2:
     *
     * Input: n = 4, connections = [[1,2,3],[3,4,4]]
     * Output: -1
     * Explanation:
     * There is no way to connect all cities even if all edges are used.
     *
     *
     *
     * Note:
     *
     *     1 <= n <= 10000
     *     1 <= connections.length <= 10000
     *     1 <= connections[i][0], connections[i][1] <= n
     *     0 <= connections[i][2] <= 105
     *     connections[i][0] != connections[i][1]
     * For Prim's Algorithm we use a Priority Queue to get the edge with least cost, and a visited set to keep nodes that are added to the MST.
     *
     *     Build the graph based on the edges.
     *     Randomly pick a node to start with(in this case, pick node with id 1).
     *     Pop the edge with least cost:
     *         if the edge does NOT exist in the MST(visited set), add its cost to total cost and add new edges starting from the end node to the queue.
     *         if the edge does exist in the MST(visted set)
     *         Minimum Spanning Tree: In an undirected weighted graph, there is a tree (N nodes, N - 1 edges so no circle) that connects all nodes in the graph, and the sum of path weights are minimum.
     *
     * Kruskal's Algorithm: The approach to find the Minimum Spanning Tree in the Graph. We sort the edges by weight in non - descending order and loop sorted edges, pick the edge as long as there are no connectivity already set up between two nodes and add this edge weight to the total weight.
     *
     * Disjoint Set: The data structure used to check the connectivity of graph efficiently in dynamic by union the nodes into one set, and find the number of disconnected sets.
     * @param N
     * @param connections
     * @return
     */
    //39ms
    public int minimumCostPrim(int N, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Integer> visited = new HashSet<>();
        int costs = 0;

        for (int[] conn : connections) {
            int n1 = conn[0], n2 = conn[1], cost = conn[2];

            graph.computeIfAbsent(n1, (k) -> new ArrayList<>());
            graph.computeIfAbsent(n2, (k) -> new ArrayList<>());
            graph.get(n1).add(new int[] {n2, cost});
            graph.get(n2).add(new int[] {n1, cost});
        }

        heap.add(new int[] {1, 1, 0});
        while (!heap.isEmpty()) {
            int[] conn = heap.poll();
            int n1 = conn[0], n2 = conn[1], cost = conn[2];

            if (!visited.contains(n2)) {
                costs += cost;
                visited.add(n2);
                for (int[] next : graph.get(n2)) {
                    heap.add(new int[] {n2, next[0], next[1]});
                }
            }
        }

        return visited.size() == N ? costs : -1;
    }

    /**
     * Kruskal Algorithm using union find
     *

     Time complexity: Assuming NNN to be the total number of nodes (cities) and MMM to be the total number of edges (connections). Sorting all the MMM connections will take O(M?logM).
     Performing union find each time will take log??N\log^{\ast} Nlog?N (Iterated logarithm). Hence for M edges, it's O(M?log??N)O(M \cdot \log^{\ast} N)O(M?log?N) which is practically O(M)O(M)O(M) as the value of iterated logarithm, log??N\log^{\ast} Nlog?N never exceeds 5.

     Space complexity: O(N), space required by parents and weights.

     */

    int[] parent;
    int n;

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
            n--;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public int minimumCost22(int N, int[][] connections) {
        parent = new int[N + 1];
        n = N;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int res = 0;

        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (find(x) != find(y)) {
                res += c[2];
                union(x, y);
            }
        }

        return n == 1 ? res : -1;
    }
//22ms

    /**
     * 43cfcvf
     * @param N
     * @param connections
     * @return
     */
    public int minimumCostKruskal(int N, int[][] connections) {


        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int res = 0;
        UnionFind uf = new UnionFind(N);
        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (uf.find(x) != uf.find(y)) {
                // if(uf.union(x, y)){
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
        private boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {

                if (size[px] < size[py]) {
                    size[py] += size[px];
                    parent[px] = py;
                } else {
                    size[px] += size[py];
                    parent[py] = px;
                }
                //parent[px] = py;
                n--;
                return true;
            }else{
                return false;
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
