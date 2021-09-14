package com.run.leetcode.Parantheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 * Example 2:
 *
 * Input: s = "((("
 * Output: 3
 * Example 3:
 *
 * Input: s = "()"
 * Output: 0
 * Example 4:
 *
 * Input: s = "()))(("
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 * Accepted
 */
public class MinimumAddtoMakeParenthesesValid921 {

    // no need for extra spaces
    public int minAddToMakeValid(String S) {
        int left = 0, right = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                right++;
            } else if (right > 0) {
                right--;
            } else {
                left++;
            }
        }
        return left + right;
    }

    /**
     * Space is used which can be avoided.
     * @param S
     * @return
     */
    public int minAddToMakeValidWithSpace(String S) {
        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0;

        for(int i = 0; i < S.length(); i++)
        {
            char c = S.charAt(i);
            if(c == '(' )
            {
                stack.push(i);
                count++;
            }else if(stack.size() > 0)
            {
                stack.pop();
                count--;
            }else
                count++;

        }

        return count;
    }
}
