package com.run.leetcode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//Sort a stack using no more than one another stack.
public class SortStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = Arrays.asList(4,5,7,2,9,8,1,3,2);
		Stack<Integer> stack = new Stack<Integer>();
		stack.addAll(nums);
		System.out.println(sortstack(stack));
		
		/*
		 * stack = sortstack(stack); System.out.println(stack.pop());
		 * System.out.println(stack.pop()); System.out.println(stack.pop());
		 * System.out.println(stack.pop()); System.out.println(stack.pop());
		 * System.out.println(stack.pop());
		 */
	}

	public static Stack<Integer> sortstack(Stack<Integer> stack){
		if(stack == null || stack.isEmpty())
				return stack;
		
		Stack<Integer> newStack = new Stack<Integer>();
		newStack.push(stack.pop());
		
		while(stack.isEmpty() == false)
		{
			int temp = stack.pop();
			while(newStack.isEmpty() == false && newStack.peek() < temp)
				stack.push(newStack.pop());
			newStack.push(temp);
		}
		return newStack;
	}
}
