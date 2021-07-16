package com.run.leetcode.binaryTree;
/**
 * 536. Construct Binary Tree from String
 * Medium
 *
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "4(2(3)(1))(6(5))"
 * Output: [4,2,6,3,1,5]
 *
 * Example 2:
 *
 * Input: s = "4(2(3)(1))(6(5)(7))"
 * Output: [4,2,6,3,1,5,7]
 *
 * Example 3:
 *
 * Input: s = "-4(2(3)(1))(6(5)(7))"
 * Output: [-4,2,6,3,1,5,7]
 *
 *
 *
 * Constraints:
 *
 *     0 <= s.length <= 3 * 104
 *     s consists of digits, '(', ')', and '-' only.
 */

import java.util.Stack;

public class ConstructBinaryTreeFromString536 {
//1ms using only recursion stack space
    public TreeNode str2tree(String s) {
        int[] index = new int[]{0};
        return recurse(s, index);
    }

    private TreeNode recurse(String s, int[] index) {
//Base condition
        if(index[0] == s.length()) return null;
//Get the sign
        int v = 0;
        int sign = 1;
//Get the sign
        if(s.charAt(index[0]) == '-') {
            sign = -1;
            index[0]++;
        }
//Get the number or get substring from index intial index[0] to whatever count the digits are
        while(index[0] < s.length() && Character.isDigit(s.charAt(index[0]))) {
            v = v*10 + (s.charAt(index[0]) - '0');
            index[0]++;// the last time it incremnets it gets rid of closing brackets
        }

        v = v*sign;//get the node value positive or negative.

        TreeNode root = new TreeNode(v);//create the node

        if(index[0] < s.length() && s.charAt(index[0]) == '(') {//get the left node first
            index[0]++;//gets rid of opening brackets
            root.left = recurse(s, index);
        }

        if(index[0] < s.length() && s.charAt(index[0]) == '(') {
            index[0]++;//gets rid of opening brackets
            root.right = recurse(s, index);
        }


        index[0]++;///gets rid of the closing brackets of the parent node

        return root;

    }

//7ms Using stack
public TreeNode str2treeStack(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i){
            char c = s.charAt(i);
            if(c == ')')    stack.pop();
            else if(c >= '0' && c <= '9' || c == '-'){
                while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if(!stack.isEmpty()){
                    TreeNode parent = stack.peek();
                    if(parent.left != null)    parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
