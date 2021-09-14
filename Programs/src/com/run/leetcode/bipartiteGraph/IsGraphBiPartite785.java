package com.run.leetcode.bipartiteGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
 * Example 2:
 *
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 *
 *
 * Constraints:
 *
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All the values of graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 */
public class IsGraphBiPartite785 {

    /**
     For graph to be bipartrite, it should be possible to split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

     Here we will define an int array for color for each node. The given input graph is an 2d array. For each row we get the ndoes connected to index row. So the graph length is the number of nodes and each int array in the graph is the number of edges and nodes connected to the row node.

     Steps
     1> Define a color array fo length of graph.
     2> Iterate throug the graph and for each int array
     if the colors is not set == 0 and is Valid to assign 1 as color to the node returns false the return false;
     End - Return true;

     isValidColor method
     1> This takes the graph to get the edges , color int array and possible color for the index and index.
     2> If the color at the given index is not 0, then return if the color in int array is the expected color in the input args.
     3> Else assign the color to that index in the array.
     4> Now iterate through all child nodes for that index node and see if we can assign -color as the color to the child nodes.
     5> If we get false here, we will simply return that false to the calling method




     **/
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for(int i = 0; i < n ; i++)
        {
            if(colors[i] == 0 && !isValidColor(graph, colors, 1, i))
                return false;
        }
        return true;
    }

    private boolean isValidColor(int[][] graph, int[] colors, int color, int index){
        if(colors[index] != 0)
            return colors[index] == color;

        colors[index] = color;

        for(int n : graph[index])
        {
            if(!isValidColor(graph, colors, -color, n))
                return false;
        }
        return true;
    }

    public boolean isBipartiteBFS(int[][] graph) {
        //BFS
        // 0(not meet), 1(black), 2(white)
        int[] visited = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length != 0 && visited[i] == 0) {
                visited[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while(! q.isEmpty()) {
                    int current = q.poll();
                    for (int c: graph[current]) {

                        if (visited[c] == 0) {
                            visited[c] = (visited[current] == 1) ? 2 : 1;
                            q.offer(c);
                        } else {
                            if (visited[c] == visited[current]) return false;
                        }
                    }
                }

            }
        }

        return true;
    }

}
