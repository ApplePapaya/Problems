package com.run.leetcode.Parantheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class GenerateParanthesis22 {

    ///used StringBuilder and backtrack when necessary, mainly for efficiency.

    /**
     * Following is my reasoning behind the time complexity of O(2^2n).
     *
     * Let's say n = 3 pairs. We only have to consider the tree with "(" at the root (because ")" is not a valid root).
     *
     * To start with, let's create the full binary tree for string length=6 (as total chars is 6) using only "(" and ")". At each level (starting from root), you will have 2^0 , 2^1, 2^2, 2^3, 2^4 and 2^5 nodes. So the total number of nodes in the tree is 2^6 -1 (which is 2^2n -1). But due to backtracking we don't need to explore all the nodes, at most half as any string with more ")" than "(" is invalid.
     *
     * so the total number of nodes to explore is (2^2n -1)/2 and the complexity would be O( 2^2n) dropping the constants.
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
        if(open == n && close == n) {
            res.add(sb.toString());
            return;
        }

        if(open < n) {
            sb.append("(");
            helper(res, sb, open+1, close, n);
            sb.setLength(sb.length()-1);
        }
        if(close < open) {
            sb.append(")");
            helper(res, sb, open, close+1, n);
            sb.setLength(sb.length()-1);
        }
    }


    //String costly operation
    public List<String> generateParenthesis22(int n) {
        List<String> output = new ArrayList<>();
        backtrack(output, "", 0, 0, n);
        return output;
    }

    public void backtrack(List<String> result, String val, int open, int close, int max){
        if(val.length() == max * 2)
        {
            result.add(val);
            return;
        }

        if(open < max )
            backtrack(result, val + "(", open+1, close, max);

        if(close < open)
            backtrack(result, val + ")", open, close + 1, max);
    }
}
