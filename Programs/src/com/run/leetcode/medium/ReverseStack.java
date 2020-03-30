package com.run.leetcode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReverseStack {
 static Stack<int[]> javaStack = new Stack<int[]>();
 static Stack<String> javaStringStack = new Stack<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<Integer>();
		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
	    stack.addAll(intList);
	    System.out.println(stack);
	    System.out.println(reverse(stack));
	  //  System.out.println(javaStack);
	    javaStringStack.stream()
	    				.forEach(System.out :: println);
	    javaStack.stream()
	    			.forEach(i -> System.out.println(Arrays.toString(i)));
	}

	 static void recurse(int depth) {
	        for (StackTraceElement each: new Exception().getStackTrace()) System.out.println(each);
	        if (depth > 0) recurse(depth - 1);
	    }

	
	public static Stack<Integer> reverse(Stack<Integer> stack){
		if(stack == null || stack.isEmpty())
			return stack;
		
		int temp = stack.pop();
		reverse(stack);
		insertAtBottom(stack, temp);
		return stack;
	}
	
	public static void insertAtBottom(Stack<Integer> stack, int x) {
		int[] arr = new int[stack.size() + 2];
		arr = stack.stream().mapToInt(Number :: intValue).toArray();
		//arr[stack.size()] = x;
		javaStack.add(arr);
		javaStringStack.add("{" + Arrays.toString(arr) + "}  Value of x :" + x);
		if(stack.isEmpty())
		{
			stack.push(x);
			return;
		}
		int temp = stack.pop();
		insertAtBottom(stack, x);
		stack.push(temp);
	}
}
