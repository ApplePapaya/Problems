package com.run.leetcode.binarySearch;

import javax.swing.tree.TreeNode;

/**
 * 270. Closest Binary Search Tree Value
 * Easy
 *
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Example 2:
 *
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 104].
 *     0 <= Node.val <= 109
 *     -109 <= target <= 109
 */
public class ClosestBinarySearchTreeValue270 {


    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
}