package com.run.leetcode.bfs;

import java.util.*;

/**
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 *
 *
 * Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] does not contain i.
 * If graph[a] contains b, then graph[b] contains a.
 * The input graph is always connected.
 */
public class ShortestPathVisitingALlNodes847 {
    /**
     Here we need to find the shortest path to all nodes. However the start and end node is not specified. So we will have to do BFS starting on all nodes and record the steps taken to visit all nodes
     Here we need to not only track for each node, we also need the path for each node to check if all nodes are visited or not. We can revisit a node if the cost is less than its current value.
     Since there are only 12 nodes, we can use bit mask to denote the node and set the bits of each nodes
     by marking it as 1.
     Below are the steps of algorithm
     1> Get the graph length which is the number of nodes present
     2> Create a dp array with size 2^n with each array of size n. Since each node can connect to all other nodes execpt itself. So each row in this array will be for each node representing the nodes visited in the path and second element will be the cost ( 1 for each edge visited.)
     3> Fill this array cost for each node/path as MAX value.
     4> Create a queue and offer it with path with corresponding node as visited and mark the index which is the node. Example, graph of 5 nodes ( node 0 to node 4),for node 0 , the queue is offered as {(00001), (0)}
     Similarly say for node 3 the queue is offered as {(01000), (3)}
     Above implies that considerin path from node 3, the nodes visited is currently 3. Subsequently through BFS as and when we visit each node, we set the corresponding bits to visited and if we get better cost we will update it.
     5> Set the dp array cost for that corresponding path as 0. Since for the node to visit itself is 0.
     6> Iterate over the queue.
     7> For each element polled from the queue( path, starting node)
     8> If the path has all bits set, meaning aall nodes visited, then return the cost corresponding to that combination of path and node index.
     9> if all nodes not visited, then iterate over the nodes connected to this node - using node index
     10> Get the new path bit mask, by doin an or operation between the path and this node index
     11> Compare the existing cost of this path with this node index with cost until the previous node + 1.
     12> If better cost then set this new cost to the new path/ node index dp array and offer to queue.
     13> Return -1 if we couldnt find a path with all nodes. Possible with disjoint nodes



     **/
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //Use every node as starting node
            queue.offer(new int[] { 1 << i, i }); // 2^i = 1<<i
            dp[1 << i][i] = 0;
        }
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            // 1<<n) - 1 means all bit set?
            if (pair[0] == (1 << n) - 1) // field1 == final state
                return dp[pair[0]][pair[1]];
            for (int next : graph[pair[1]]) {
                int tmp = (pair[0] | (1 << next));
                if (dp[tmp][next] > dp[pair[0]][pair[1]] + 1) {
                    dp[tmp][next] = dp[pair[0]][pair[1]] + 1;
                    queue.offer(new int[] { tmp, next });
                }
            }
        }
        return -1;
    }
    public int shortestPathLength222(int[][] graph) {
        int n = graph.length;
        int fullMask = (1 << n) - 1;

        Set<String> visited = new HashSet<>();
        Queue<Node> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Node node = new Node(i, 1<<i);
            que.offer(node);
            visited.add(node.toString());
        }

        int level = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Node node = que.poll();
                if (node.mask == fullMask) return level;
                for (int next : graph[node.id]) {
                    Node nextNode = new Node(next, node.mask | (1 << next));
                    if (visited.contains(nextNode.toString())) continue;
                    que.offer(nextNode);
                    visited.add(nextNode.toString());
                }
            }
            level++;
        }

        return level;
    }

    class Node {
        int id;
        int mask;

        Node(int id, int mask){
            this.id = id; this.mask = mask;
        }

        public String toString() {
            return id + " " + mask;
        }
    }
}
/**
 this problem is a little different from classical shortest path problem, we can NOT just do one-time BFS and find out the shortest path that can visit all node( it’s much like a salesman problem but have the same weight on every edge). So we need to change the algorithms a little bit.
 First, we can go through edge and node more than once, so that the status is more complex than basic BFS (unfound; found and in the queue; found and removed from the queue). We should record the status: whether a node is visited by a path (can only memory the node have visited). Besides, no node should be removed from the queue.
 Second, the problem didn't specify the start and end node, so we need to do BFS for every node as the start node. Furthermore, for the speed of runtime, we should take turns to do the BFS. When we find a path that can visit all nodes that is the shortest path for sure. So we put all the nodes in the queue at first then start the BFS algorithm to make it take turns to visit the graph from each node.
 Third, as mentioned above, we are going to remember whether the path is taken before and check if the path visits all nodes. If we use a map or list to represent the path needs plenty of memory, and the checking will cost a lot of time (iterate the whole map or list). The good news is that the number of nodes won’t exceed 12, so we introduce the Bitmask method. In a 16-bit int, each bit can stand for whether a node is visited. So the 16-bit int can be a path( which node is visited) in this question.
 Finally, the remain parts of the solution are quite like the classic BFS algorithm. Every time take the first element in the queue, if the path( mask) matches the goal( visited all nodes), then return the number of steps ( dist). Or try to visit( putting in the queue and marking the path is taken) all the adjacent nodes only when the path is not taken before. Then we can solve the problem.
 **/