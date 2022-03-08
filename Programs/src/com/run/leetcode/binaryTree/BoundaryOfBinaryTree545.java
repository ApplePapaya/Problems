package com.run.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.
 *
 * The left boundary is the set of nodes defined by the following:
 *
 *     The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 *     If a node in the left boundary and has a left child, then the left child is in the left boundary.
 *     If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 *     The leftmost leaf is not in the left boundary.
 *
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 *
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 *
 * Given the root of a binary tree, return the values of its boundary.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 *   4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
 * Output: [1,2,4,7,8,9,10,6,3]
 * Explanation:
 * - The left boundary follows the path starting from the root's left child 2 -> 4.
 *   4 is a leaf, so the left boundary is [2].
 * - The right boundary follows the path starting from the root's right child 3 -> 6 -> 10.
 *   10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
 * - The leaves from left to right are [4,7,8,9,10].
 * Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 104].
 *     -1000 <= Node.val <= 1000
 */
public class BoundaryOfBinaryTree545 {


    private List<Integer> result = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        result.add(root.val);

        if(root.left != null) {
            dfs(root.left, true, false);
        }

        if(root.right != null) {
            dfs(root.right, false, true);
        }

        return result;
    }

    private void dfs(TreeNode root, boolean left_bound, boolean right_bound) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }

        if(left_bound) {
            result.add(root.val);
        }


        if(left_bound) {
            if(root.left != null) {
                dfs(root.left, true, false);
                dfs(root.right, false, false);
            }else{
                dfs(root.right, true, false);
            }
        }else if(right_bound) {
            if(root.right != null) {
                dfs(root.left, false, false);
                dfs(root.right, false, true);
            }else{
                dfs(root.left, false, true);
            }
        }else{
            dfs(root.left, false, false);
            dfs(root.right, false, false);
        }

        if(right_bound) {
            result.add(root.val);
        }
    }
    public List<Integer> boundaryOfBinaryTree1(TreeNode root) {
        List<Integer> ls = new ArrayList<Integer>();
        if(root!=null){
            ls.add(root.val);
            lookupElems(root.left,ls,true,false);
            lookupElems(root.right,ls,false,true);
        }
        return ls;
    }

    private void lookupElems(TreeNode root, List<Integer> ls, boolean isLeftBoundary, boolean isRightBoundary){
        if (root==null) {
            return;
        }
        if (root.left==null && root.right==null) {
            ls.add(root.val);
            return;
        }
        if (isLeftBoundary) {
            ls.add(root.val);
        }
        lookupElems(root.left,ls,root.left!=null && isLeftBoundary,root.right==null && isRightBoundary);
        lookupElems(root.right,ls,root.left==null && isLeftBoundary,root.right!=null && isRightBoundary);
        if (isRightBoundary) {
            ls.add(root.val);
        }
    }

    public List<Integer> boundaryOfBinaryTree123(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.add(root.val);
            getBounds(root.left, res, true, false);
            getBounds(root.right, res, false, true);
        }
        return res;
    }

    private void getBounds(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) res.add(node.val);
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);
        getBounds(node.left, res, lb, rb && node.right == null);
        getBounds(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }
}
