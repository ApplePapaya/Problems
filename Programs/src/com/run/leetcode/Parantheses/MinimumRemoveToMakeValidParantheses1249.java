package com.run.leetcode.Parantheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinimumRemoveToMakeValidParantheses1249 {
    /**
     * Shortened Two Pass String Builder
     *
     * Intuition
     *
     * This approach is a simplification of the previous one, and only needs to keep track of the balance. It does not need a stack. Instead of doing the full procedure twice, we can do the first pass and then look at the balance to see how many "(" we need to remove. It turns out that if we remove the rightmost '(', we are guaranteed to have a balanced string. So for the second pass, we only need to remove balance "(", starting from the right.
     *
     * It might be difficult initially to see why this works, so here's a justification.
     *
     * Consider a string s that contains no invalid ")" (it has had all the invalid ")" removed by the first pass of the algorithm). It's important to understand that we therefore know there is a way of removing balance "(" that will make it valid. For example, one of our examples from above.
     * For a given "(" to be valid, there needs to be more ")" than "(" after it in s (if not, there won't be a ")" leftover for it). If this is true for all "(" in s, then s would be valid.
     *
     * When we remove a "(", all other "(" to the left see their ratio of ")" to "(" go up (in other words, they have less others to compete for the ")" with).
     *
     * So by removing balance "(" from the right, every other "(" now has balance less "(" after it, which is the biggest improvement in the ratios we could have possibly got. If any "(" was still not valid after this, then that would mean s had invalid ")" at the start (which it didn't, because it had all of those removed already).
     *
     * Therefore, this has to be a valid solution.
     *
     * Algorithm
     *d
     * In order to avoid iterating backwards (which adds needless complexity to the algorithm), we also keep track of how many "(" are in the string in the first pass. This way, we can calculate how many "(" we are keeping, and count these down as we iterate through the string on the second pass. Then once we've kept enough, we can start dropping them.
     */
//Time : O(N) and space O(N)
    public String minRemoveToMakeValid(String s) {

        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Pass 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }



    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }
        return sb;
    }

    public String minRemoveToMakeValid2(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }


    // Time O(4N)

    public String minRemoveToMakeValid3(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
