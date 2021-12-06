package com.run.leetcode.binaryTree;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum124 {
    /**
     BInary tree maximum path  sum, will be either a left subtree  part or right subtree part or subtree
     with node ( child root) with left and right subtree to it as in above examples.
     we can solve this using recursion,.
     There is  at twist in here. generally what we return in recursion helper method and whats computed recursively are aligned . however it is not reason is. final answer can be left , root , right or left left root
     or root right right meaning they dont have to be inverted V.
     However return ans from the helper method cannot be inverted v. since we cannot have a path go up and then down and THEN UP!!!.
     so when returning we will return max of sum of root + left or root + right.
     but as global varible max we will have the summation as all 3
     1> left + root
     2> right + root
     3> left + right + root
     whichever is max

     **/

    int maxValue;
//Very concise solution
    public int maxPathSum2(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    /********/
    int max;
    public int maxPathSum(TreeNode root) {
        //Initialize max to minimum value
        max = Integer.MIN_VALUE;
        helper(root);

        return max;
    }

    public int helper(TreeNode node){
        //Basr case
        if(node == null)
            return 0;
        //get the left and right values
        int left = helper(node.left);// here we can do  int left = Math.max(0, helper(node.left));
        int right = helper(node. right);//here we can do int right = Math.max(0, helper(node.right));
        // left right could be negative - so either put if else or do a max with 0 and the return value
        //putting if else here for momre understanding
        if(left <0 && right <0)
            max = Math.max(max, node.val);
        else if(left < 0)
            max = Math.max(max, node.val + right);// is the right subtree giving maximum path sum
        else if(right < 0)
            max = Math.max(max, node.val + left);//is the left subtree giving max path sum
        else
            max = Math.max(max, node.val + left + right);//may be this sub tree is

        return Math.max(node.val, Math.max(node.val + left, node.val + right));
    }
}


/**
 int ret = Integer.MIN_VALUE;

 public int maxPathSum(TreeNode root) {
 if (root == null) {
 return 0;
 }
 helper(root);
 return ret;
 }

 private int helper(TreeNode root) {
 if (root == null) {
 return 0;
 }

 int left = helper(root.left);
 int right = helper(root.right);
 // Computes the max ret it could get in current recursion
 if (left < 0) {
 ret = Math.max(ret, Math.max(root.val, root.val+right));
 } else if (right < 0) {
 ret = Math.max(ret, Math.max(root.val, root.val+left));
 } else {
 ret = Math.max(ret, left+root.val+right);
 }

 // Returns the largest path starting with current node,
 // It could be the single node, or the node + left or right path
 return Math.max(root.val, Math.max(root.val+left, root.val+right));
 }

 //Concise

 public class Solution {

 }
 **/
