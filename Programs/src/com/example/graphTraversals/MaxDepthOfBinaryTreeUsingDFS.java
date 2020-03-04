package com.example.graphTraversals;


class Node{
	
	int val;
	Node left;
	Node right;
	
	Node(int v){
		this.val = v;
	}
}


public class MaxDepthOfBinaryTreeUsingDFS {

	Node root ;
	public static void main(String[] args) {
		MaxDepthOfBinaryTreeUsingDFS tree = new MaxDepthOfBinaryTreeUsingDFS(); 
		
		 Node root = new Node(1); 
	        root.left = new Node(2); 
	        root.right = new Node(3); 
	        root.left.left = new Node(4); 
	        root.left.right = new Node(5); 
	        root.right.left = new Node(6); 
	        root.right.right = new Node(7); 
	        tree.root = root;
	  
		    System.out.println(maxDepth(tree.root));
		    
		    int maxdepth = maxDepth(tree.root);
		    
		    System.out.println(sumMaxLevel(tree.root, maxdepth));

	}
	
	public static int maxDepth(Node root) {
		if(root == null)
			return 0;
		
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		return 1 + Math.max(leftDepth, rightDepth);
	}
	
	public static int sumMaxLevel(Node node, int height) {
		if(node == null)
			return 0;
		
		if(height == 1)
			return node.val;
		
		int leftLeafVal = sumMaxLevel(node.left, height - 1);
		int rightLeafVal = sumMaxLevel(node.right, height - 1);
		
		return leftLeafVal + rightLeafVal;
	}

}
