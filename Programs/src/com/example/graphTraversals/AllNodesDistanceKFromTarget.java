package com.example.graphTraversals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class AllNodesDistanceKFromTarget {
	
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
