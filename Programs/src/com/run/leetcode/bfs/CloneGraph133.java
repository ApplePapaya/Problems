package com.run.leetcode.bfs;

import org.w3c.dom.Node;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 *
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * Example 4:
 *
 *
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
public class CloneGraph133 {
    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
    /**

     We can solve this using BFS and DFS.
     DFS will use the recursive
     **/


        public Node cloneGraph(Node node) {

            if(node == null)
                return node;

            Map<Node, Node> visited = new HashMap<>();

            Queue<Node> queue = new LinkedList<>();
            queue.add(node);

            visited.put(node, new Node(node.val, new ArrayList<Node>()));

            while(!queue.isEmpty() )
            {
                Node n = queue.poll();
                for(Node child : n.neighbors){
                    if(!visited.containsKey(child))
                    {
                        visited.put(child, new Node(child.val, new ArrayList<Node>()));
                        queue.add(child);
                    }
                    // Add the clone of the neighbor to the neighbors of the clone node "n".
                    visited.get(n).neighbors.add(visited.get(child));
                }
            }
            return visited.get(node);
        }
    }

/**


 class Solution {
 private HashMap <Node, Node> visited = new HashMap <> ();
 public Node cloneGraph(Node node) {
 if (node == null) {
 return node;
 }

 // If the node was already visited before.
 // Return the clone from the visited dictionary.
 if (visited.containsKey(node)) {
 return visited.get(node);
 }

 // Create a clone for the given node.
 // Note that we don't have cloned neighbors as of now, hence [].
 Node cloneNode = new Node(node.val, new ArrayList());
 // The key is original node and value being the clone node.
 visited.put(node, cloneNode);

 // Iterate through the neighbors to generate their clones
 // and prepare a list of cloned neighbors to be added to the cloned node.
 for (Node neighbor: node.neighbors) {
 cloneNode.neighbors.add(cloneGraph(neighbor));
 }
 return cloneNode;
 }
 }

 **/