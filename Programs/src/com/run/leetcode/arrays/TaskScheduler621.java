package com.run.leetcode.arrays;

import java.util.Arrays;

/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler621 {

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

	/**
	 * Below are the steps for the solution algo
	 * 1> Get the frequency Map for the tasks. Since its upper case be sure to subtract A and not a
	 * 2> Initialize 2 variables, max and maxCount. here max is the max value of the frequency and
	 * 		maxCount is the number of characters that have the frequency = max
	 * 3> For every task, increase the frequency in the freq array of letters
	 * 4> If you find the frequency now equal to max value, then we have another character which is max
	 * frequent.
	 * 		Else if the max value is less than the current character frequency, we have found a new max
	 * 		value. So this will be the max and since we are finding this for the first time, we have
	 * 		to reset the maxCount = 1
	 * 5> So if max is 10 we need 9 sets of idle slots or 9 * n idle slots. That is we dont need idle slot
	 * 		after the last one(or 10th one as in here). That is the partCount below.
	 * 6> Now what needs to be the size of each partcount or its length. Initially its n. However we
	 *    can fill it with characters which are also maxFrequent in between except for the one already
	 * 	  used. so partLength = n - ( maxCount - 1);
	 * 7> Empty slots are partCount * partLength. However this doesnt include tasks which are of maxcount
	 * 8> Available tasks which are not in maxFrequent count is total tasks - max * maxCount
	 * 9> So the idles would be emptySlots - remaining tasks which are not maxCount
	 * 10> if the idles > 0 then we need to add this to tasks.length for total length
	 * 11> However if the idles is negative, then we are good and need not add any idles. So
	 * idles can be set as math.max(0. emptySlots - availableTasks):
	 */
	public int leastInterval2(char[] tasks, int n) {
		int[] counter = new int[26];
		int max = 0;
		int maxCount = 0;
		for(char task : tasks) {
			counter[task - 'A']++;
			if(max == counter[task - 'A']) {
				maxCount++;
			}
			else if(max < counter[task - 'A']) {
				max = counter[task - 'A'];
				maxCount = 1;
			}
		}

		int partCount = max - 1;
		int partLength = n - (maxCount - 1);
		int emptySlots = partCount * partLength;
		int availableTasks = tasks.length - max * maxCount;
		int idles = Math.max(0, emptySlots - availableTasks);
		return tasks.length + idles;
	}



	public int leastInterval3(char[] tasks, int n)
	{
		char[] hash = new char[26];
		int l = tasks.length;

		for (int i = 0; i < l; ++i)
			hash[tasks[i] -'A']++;

		int max = 0;
		int count = 1;

		for(int i = 0; i < 26; ++i)
		{
			if(hash[i] > max)
			{
				max = hash[i];
				count = 1;
			}
			else if(hash[i] == max) count++;
		}

		if(l >= (max-1) * (n+1) + count) return l;

		return ((max-1) * (n+1) + count);
	}
}
