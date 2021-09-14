package com.example.graphTraversals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 *
 * Example 2:
 *
 * Input: root = [1], target = 1, k = 3
 * Output: []
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 500].
 *     0 <= Node.val <= 500
 *     All the values Node.val are unique.
 *     target is the value of one of the nodes in the tree.
 *     0 <= k <= 1000
 */
//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class 	AllNodesDistanceKFromTarget863 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	Map<TreeNode, List<TreeNode>> map  = new HashMap<>();
	
	public List<Integer> findNodesAtDistanceK(TreeNode root, TreeNode target, int K){
		List<Integer> res = new ArrayList<>();
		if(root == null || K < 0)
			return res;
		
		buildMap(root, null);
		
		Queue<TreeNode> q = new LinkedList<>();
		Set<TreeNode> visited = new HashSet<>();
		
		q.add(target);
		visited.add(target);
		
		while(q.isEmpty() == false)
		{
			int size = q.size();
			
			if(K == 0)
			{
				for(int i = 0; i < size; i ++)
				{
					res.add(q.poll().val);
				}
				return res;
			}
			
			
			for(int i = 0; i < size; i++)
			{
				TreeNode node = q.poll();
				for(TreeNode next : map.get(node))
				{
					if(visited.contains(next) == false)
					{
						visited.add(next);
						q.add(next);
					}
				}
			}
			K--;
		}
		
		return res;
	}
	
	public void buildMap(TreeNode node, TreeNode parent) {
		if(node == null)
			return ;
		
		if(map.containsKey(node) == false)
		{
			map.put(node, new ArrayList<>());
			
			if(parent != null)
			{
				map.get(node).add(parent);
				map.get(parent).add(node);
			}
			buildMap(node.left, node);
			buildMap(node.right, node);
		}
		
	}

	
	 
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
