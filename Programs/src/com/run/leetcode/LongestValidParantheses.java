package com.run.leetcode;

import java.util.Stack;
import java.util.stream.Stream;

public class LongestValidParantheses {

	public static void main(String[] args) {
		String[] arrStrings = {"(())" ,"()()()",")(((",""};
		Stream.of(arrStrings)
					.forEach(s->System.out.println(getLongestValidParantheses(s)));
								

	}

	private static int getLongestValidParantheses(String s) {
		Stack<int[]> stack = new Stack<>();
		int result = 0;
		for(int i =0;i< s.length();i++) {
			char c = s.charAt(i);
			if(c==')') {
				if(!stack.isEmpty() && stack.peek()[0] ==0) {
					stack.pop();
					result = Math.max(result, i - (stack.isEmpty() ?-1 : stack.peek()[1]));// if stack is empty then stack.peek will give null pointer
				}else {
					stack.push(new int[] {1,i});
				}
			}else {// Since string contains only ( or )
				stack.push(new int[] {0,i});
			}
			//System.out.println(stack);
		}
		return result;
	}

}
