package com.run.leetcode.medium;

import java.util.Stack;
import java.util.function.Function;

//https://leetcode.com/problems/asteroid-collision/solution/
public class AsteroidCollision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	   public static  int[] asteroidCollision(int[] asteroids) {
	        
		   Stack<Integer> stack = new Stack<>();
		   
		   asteroids :
			   for(int ast : asteroids)
			   {
				   while(stack.isEmpty() == false && ast < 0 && 0 > stack.peek())
				   {
					   if(stack.peek() < -ast)
					   {
						   stack.pop();
						   continue;
					   }else if(stack.peek() == -ast)
					   {
						   stack.pop();
					   }
					   
					   break asteroids;
				   }
				   stack.push(ast);
			   }
		   
		   return stack.stream().mapToInt(i -> i ).toArray();
		   
	    }
	}