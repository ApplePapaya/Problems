package com.run.leetcode.unionFind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 2000
 *     1 <= edges.length <= 5000
 *     edges[i].length == 2
 *     0 <= ai <= bi < n
 *     ai != bi
 *     There are no repeated edges.
 *
 *     When the edges of the graph are dynamic – changing over time – DFS is not a good choice since it cannot be applied progressively; we can compute the connected components faster by using union-find.
 *
 * That said, union-find is helpful only if edges and vertices are never deleted.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph323 {
    /**
     * In an undirected graph, a connected component is a subgraph in which each pair of vertices is connected via a path. So essentially, all vertices in a connected component are reachable from one another.
     *
     * Let's see how we can use DFS to solve the problem. If we run DFS, starting from a particular vertex, it will continue to visit the vertices depth-wise until there are no more adjacent vertices left to visit. Thus, it will visit all of the vertices within the connected component that contains the starting vertex. Each time we finish exploring a connected component, we can find another vertex that has not been visited yet, and start a new DFS from there. The number of times we start a new DFS will be the number of connected components.
     * Algorithm
     *
     *     Create an adjacency list such that adj[v] contains all the adjacent vertices of vertex v.
     *     Initialize a hashmap or array, visited, to track the visited vertices.
     *     Define a counter variable and initialize it to zero.
     *     Iterate over each vertex in edges, and if the vertex is not already in visited, start a DFS from it. Add every vertex visited during the DFS to visited.
     *     Every time a new DFS starts, increment the counter variable by one.
     *     At the end, the counter variable will contain the number of connected components in the undirected graph.
     * @param adjList
     * @param visited
     * @param startNode
     * Complexity Analysis
     *
     * Here EEE = Number of edges, VVV = Number of vertices.
     *
     *     Time complexity: O(E+V){O}(E + V)O(E+V).
     *
     *     Building the adjacency list will take O(E){O}(E)O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
     *
     *     During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited. In addition, when we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of O(E+V){O}(E + V)O(E+V).
     *
     *     Space complexity: O(E+V){O}(E + V)O(E+V).
     *
     *     Building the adjacency list will take O(E){O}(E)O(E) space. To keep track of visited vertices, an array of size O(V){O}(V)O(V) is required. Also, the run-time stack for DFS will use O(V){O}(V)O(V) space.
     */
    //DFS Solution
//Tim COmplexit = O( E + V) and space O(E + V)
    private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;

        for (int i = 0; i < adjList[startNode].size(); i++) {
            if (visited[adjList[startNode].get(i)] == 0) {
                dfs(adjList, visited, adjList[startNode].get(i));
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        //cREATING adjacency list
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                components++;
                dfs(adjList, visited, i);
            }
        }
        return components;
    }
//BFS

    public int countComponentsBFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += bfs(v, graph, visited);
        return components;
    }
    int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src]) return 0;
        visited[src] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        return 1;
    }

    //Union Find Only path compression

    /**
     * Algorithm
     *
     *     Initialize a variable count with the number of vertices in the input.
     *     Traverse all of the edges one by one, performing the union-find method combine on each edge. If the endpoints are already in the same set, then keep traversing. If they are not, then decrement count by 1.
     *     After traversing all of the edges, the variable count will contain the number of components in the graph.
     * @param n
     * @param edges
     * @return
     */
    public int countComponentsUF(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0; i < n; i++) roots[i] = i;

        for(int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if(root1 != root2) {
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        while(roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }

    //UF path compression and by rank/size

    public int countComponentsUF2(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int components = n;
        for (int[] e : edges) {
            int p1 = findParent(parent, e[0]);
            int p2 = findParent(parent, e[1]);
            if (p1 != p2) {
                if (size[p1] < size[p2]) { // Merge small size to large size
                    size[p2] += size[p1];
                    parent[p1] = p2;
                } else {
                    size[p1] += size[p2];
                    parent[p2] = p1;
                }
                components--;
            }
        }
        return components;
    }
    private int findParent(int[] parent, int i) {
        if (i == parent[i]) return i;
        return parent[i] = findParent(parent, parent[i]); // Path compression
    }
}
