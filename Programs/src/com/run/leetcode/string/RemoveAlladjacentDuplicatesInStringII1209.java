package com.run.leetcode.string;

import java.util.Stack;

/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * 2 <= k <= 104
 * s only contains lower case English letters.
 */
public class RemoveAlladjacentDuplicatesInStringII1209 {

    public String removeDuplicates(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
    }

    public String removeDuplicatesSB(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            sb.append(c);
            int last = sb.length()-1;
            count[last] = 1 + (last > 0 && sb.charAt(last) == sb.charAt(last-1) ? count[last-1] : 0);
            if(count[last] >= k) sb.delete(sb.length()-k, sb.length());
        }
        return sb.toString();
    }
    public String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }
    //method2: stack {54.51%}
/*
idea: similar to LC 1047
    1. add pair(char, count) to stack
    2. pop pair when count==k
*/
    public String removeDuplicatesStack(String s, int k){
        int len=s.length();
        Stack<Node> stack = new Stack<>();
        for(char c : s.toCharArray()){
            //compare prev with cur
            if(!stack.isEmpty() && stack.peek().c == c){
                stack.peek().count++;
            }else{
                //not repeat
                stack.push(new Node(c,1));
            }
            //remove when num of dup == k
            if(stack.peek().count==k) stack.pop();
        }
        //build result
        StringBuilder sb = new StringBuilder();
        for(Node node : stack){
            for(int i=0;i<node.count;i++){
                sb.append(node.c);
            }
        }
        return sb.toString();
    }

    class Node{
        char c;
        int count;
        public Node(char c, int count){
            this.c=c;
            this.count=count;
        }
    }
}
