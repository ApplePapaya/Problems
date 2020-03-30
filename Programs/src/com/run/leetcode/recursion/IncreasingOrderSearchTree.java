package com.run.leetcode.recursion;

class TreeNode{
	TreeNode left;
	TreeNode right;
	int val;
	
	TreeNode(int v){
		this.val = v;
	}
	
	public String toString() {
		return getVal(new StringBuilder(""), this).toString();
		
	}
	
	public StringBuilder getVal(StringBuilder input, TreeNode root) {
		if(root == null)
			return input;
		getVal(input, root.left);
		input.append("  " + root.val);
		
		getVal(input, root.right);
		return input;
		
	}
	
	
}

public class IncreasingOrderSearchTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(6);
		
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(5);
		
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(3);
		
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		
		System.out.println(root);
		TreeNode res = increasingBST(root);
		System.out.println(res);

	}  public static TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    public static TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }

}
