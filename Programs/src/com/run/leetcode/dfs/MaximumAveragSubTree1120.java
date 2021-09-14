package com.run.leetcode.dfs;

import javax.swing.tree.TreeNode;

/**
 * Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * A subtree of a tree is any node of that tree plus all its descendants.
 * <p>
 * The average value of a tree is the sum of its values, divided by the number of nodes.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [0,null,1]
 * Output: 1.00000
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 105
 */
public class MaximumAveragSubTree1120 {
    double res = Integer.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int sum = l[0] + r[0] + root.val;
        int count = l[1] + r[1] + 1;
        res = Math.max(1.0 * sum / count, res);// res = Math.max(res, (double)sum/(double)num);
        return new int[]{sum, count};
    }

    /**
     * Using a class object for the result
     */
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    public double maximumAverageSubtree2(TreeNode root) {
        return postorder(root).AVERAGE;
    }

    private Result postorder(TreeNode node) {
        if (node == null)
            return new Result(0, 0, 0D);

        Result left = postorder(node.left);
        Result right = postorder(node.right);

        int sum = node.val + left.SUM + right.SUM;
        int count = 1 + left.COUNT + right.COUNT;
        double average = (sum * 1.0) / count;
        // the max average among the parent and two children tree nodes
        double maxAverage = Math.max(average, Math.max(left.AVERAGE, right.AVERAGE));
        return new Result(sum, count, maxAverage);
    }

    private class Result{
        private final int SUM;
        private final int COUNT;
        private final double AVERAGE;

        private Result(final int SUM, final int COUNT, final double AVERAGE){
            this.SUM = SUM;
            this.COUNT = COUNT;
            this.AVERAGE = AVERAGE;
        }
    }
}
