package com.run.leetcode.hardProblems;

import java.util.Arrays;

public class TaskScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] tasks = new char[]{'A','A','A','B','B','B'};
		System.out.println(leastInterval(tasks, 2));
	}
	
	 public static int leastInterval(char[] tasks, int n) {
	        /**
	        1> Construct a frequency map for the tasks.
	        2> Since its just A-Z we can have an int array of 26 count
	        3> Store the frequence of tasks int he int array
	        4> Get the max frequency from all the tasks;
	        5> subtract 1 to get the idle slots required maximum.
	        6> Subtract the remaining tasks since they can be substituted for the intervals
	        7> if the idle slots is greater than 0, then they are minimum required and so the total will be idle slots + tasks.length
	        8> Else idle slots are all covered up if negative and we need to just return only the task lenght
	        **/
	        
	        int[] arr = new int[26];
	        
	        for(char c : tasks)
	            arr[c - 'A']++;
	        
	        Arrays.sort(arr);
	        int max_value = arr[25] - 1;
	        int idleSlots = max_value * n;
	                    System.out.println(max_value);
	        for(int i = 0; i <= 24; i++){
	        	if(arr[i] != 0)
	        	System.out.println(arr[i]);
	            idleSlots -=  arr[i];//Math.min(arr[i], max_value);
	        }
	                    
	        return idleSlots > 0 ? tasks.length + idleSlots : tasks.length;
	    }

}
