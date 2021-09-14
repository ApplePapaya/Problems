package com.run.leetcode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 *
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView199 {
    /**
     * Here we need to scan all the nodes and get the rigcht most node. For that we can use either dfs or bfs.
     * BFS solution
     * Initialize a queue and add the root
     * while the queue is not empty
     * get the size of the queue
     * iterate through the list of nodes in the queue
     * poll it and add the first one to result since they would be the right most
     * next add the right most node if not null and then the left most ndoe if not null
     * <p>
     * DFS solution
     * Call DFS with root and level 0 and resultList to be populated with the right most view
     * 1> inside the dfs function, check if the root is null , then return
     * 2> Logic is the first node that comes here is the one which is the rightmost for
     * any level and so should be equal to the result size list
     * 3> if resultlist.size() == level then add the node value
     * 4> call recursive dfs  on the root.right first with level = level +1
     * 5> call recrusive dfs on the root.left next with level = level + 1
     **/

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        dfs(root, 0, result);
        return result;
    }

    public void dfs(TreeNode node, int level, List<Integer> result) {
        if (node == null)
            return;

        if (level == result.size())
            result.add(node.val);

        dfs(node.right, level + 1, result);
        dfs(node.left, level + 1, result);
    }


    //BFS solution
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (q.isEmpty() == false) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.right != null) q.offer(node.right);
                if (node.left != null) q.offer(node.left);
            }

        }
        return result;
    }
}

