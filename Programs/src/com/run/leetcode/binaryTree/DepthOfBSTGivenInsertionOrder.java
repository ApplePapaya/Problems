package com.run.leetcode.binaryTree;

import java.util.Map;
import java.util.TreeMap;

/**
 * 1902. Depth of BST Given Insertion Order
 * Medium
 *
 * You are given a 0-indexed integer array order of length n, a permutation of integers from 1 to n representing the order of insertion into a binary search tree.
 *
 * A binary search tree is defined as follows:
 *
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * The binary search tree is constructed as follows:
 *
 *     order[0] will be the root of the binary search tree.
 *     All subsequent elements are inserted as the child of any existing node such that the binary search tree properties hold.
 *
 * Return the depth of the binary search tree.
 *
 * A binary tree's depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 */
public class DepthOfBSTGivenInsertionOrder {

    public int maxDepthBST(int[] order) {
        int n = order.length, maxDepth = 1;

        // our map will associate a value in our BST to its depth
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(order[0], 1);

        for (int i = 1; i < n; i++) {
            int val = order[i];

            // getting left and right nextdoor entries! (and their corresponding depths)
            Map.Entry<Integer, Integer> left = map.ceilingEntry(val);
            Map.Entry<Integer, Integer> right = map.floorEntry(val);
            int leftDepth = 0, rightDepth = 0;

            if (left != null) leftDepth = left.getValue();
            if (right != null) rightDepth = right.getValue();

            // the depth of our value is the highest of the two, plus one (since it's a child)
            int depth = Math.max(leftDepth, rightDepth) + 1;
            maxDepth = Math.max(maxDepth, depth);

            map.put(val, depth);
        }
        return maxDepth;
    }
}
