package com.run.leetcode.binaryTree;

public class ValidateValidBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[5,1,4,null,null,3,6]
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(6);
		
	System.out.println(isValidBST(root));	
	}

	 public static boolean isValidBST(TreeNode root) {
	      if(root == null || (root.left == null && root.right == null))
	          return true;
	        
	        return helper(Long.MIN_VALUE, root, Long.MAX_VALUE);
	    }
	    
	    public static boolean helper(long min, TreeNode node, long max){
	        if(node == null)
	            return true;
	        
	        if(node.val < min || node.val > max)
	        {
	            return false;
	        }
	        
	        return helper(min, node.left, node.val) && helper(node.val, node.right, max);
	    }
}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }