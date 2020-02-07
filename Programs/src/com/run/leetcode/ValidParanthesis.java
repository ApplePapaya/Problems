package com.run.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 *
 */
public class ValidParanthesis {

	public static void main(String[] args) {
		System.out.println(validateParantheses("()"));
	}

	private static boolean validateParantheses(String string) {
		Map<Character, Character> map = new HashMap<>();
		map.put('{','}');
		map.put('(',')');
		map.put('[',']');
		
		Stack<Character> stack = new Stack<>();
		for(int i =0;i<string.length();i++)
		{
			char c = string.charAt(i);
			if(map.keySet().contains(c))
			{
				stack.push(c);
			}else if (map.values().contains(c))
			{
				if(!stack.isEmpty() && map.get(stack.peek()) == c) {
					stack.pop();
				}else {
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}

}
