package com.run.leetcode.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * @author prparasu
 *
 */
public class LongestSubstringWithoutRepeatingChars {

	
	
	
	public static void main(String[] args) {
		String[] str = {"pwwkew", "bbbbb","" , "abcabcbb"};
		/*
		 * Stream.of(str) .mapToInt(LongestSubstringWithoutRepeatingChars :: bruteForce)
		 * .forEach(System.out :: println); System.out.println(
		 * "------------------------------------------------------------------");
		 * Stream.of(str) .mapToInt(LongestSubstringWithoutRepeatingChars ::
		 * slidingWindow) .forEach(System.out :: println);
		 */
		
		Map<Character, Integer> map = new HashMap<>();
		map.put('c', 10);
		System.out.println(map);
		map.put('c', 100);
		System.out.println(map);
		
 		System.out.println(bruteForce("pwwkew"));
		System.out.println(slidingWindow("pwwkew"));
		System.out.println(slidingWindowOptimized("abcdefdhbaaaaa"));
		System.out.println(usingAscii("pwwkew"));

	}
	
	/**
	 * Time complexity : O(n)O(n). Index jj will iterate nn times.

Space complexity (HashMap) : O(min(m, n))O(min(m,n)). Same as the previous approach.

Space complexity (Table): O(m)O(m). mm is the size of the charset.
	 * @param input
	 * @return
	 */
	private static int usingAscii(String input) {
		int maxLength =0;// This can be initialized to 1 if we are sure input is atleast length of 1 and never null or emptystring
		int n = input.length();
		int[] arr = new int[128];
		for(int i =0,j=0;j<n ;j++) {
			i = Math.max(i, arr[input.charAt(j)]);
			maxLength = Math.max(maxLength, j-i+1);
			arr[input.charAt(j)] = j+1;
			
		}
		
		return maxLength;
	}


	private static int slidingWindowOptimized(String input) {
			int maxLength =0;
			int n = input.length();
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for(int i =0,j=0; j<n;j++) {
				System.out.println(map);
				if(map.containsKey(input.charAt(j)))
				{
					System.out.println(" i value : " + i +"    prev i val :" +  map.get(input.charAt(j)));
					
					i = Math.max(i, map.get(input.charAt(j)));
				}
				map.put(input.charAt(j), j+1);// Here since we havej+1 we need the same below too. Else we can 
				//remove +1 from both above and below.
				maxLength = Math.max(maxLength, j-i +1);
				
			}
			return maxLength;
		}
/**
 * Here the sliding window moves only one step at a time to the right - expected.
 * However if there is a duplicate found then also it just slides one from the left e.g. pqrsq -- here once we get pqrs and then we 
 * see q and since now its not unique, we just proceed to remove p and go another iteration where again we observe non unique string
 * and then we reqmove q. Hence its not optimized.
 * @param input
 * @return
 */
	private static int slidingWindow(String input) {
		int maxLength =0 ;
		int n = input.length();
		Set<Character> set = new HashSet<>();
		System.out.print( input + "     ");

		int i =0, j=0;
		while(i<n && j<n) {
			Character ch = input.charAt(j);
			if(!set.contains(ch))
			{
				set.add(ch);
				j++;
				maxLength = Math.max(maxLength, j-i);//since j incremented above, we are not doing +1 here
			}else {
				set.remove(input.charAt(i));
				i++;
			}
		}
		
		
		return maxLength;
	}

	/**
	 * Here every i,j combination of the string is checked to get the max length.
	 * Time complexity : O(n^3).
	 * Space complexity : O(min(n, m))O(min(n,m)). We need O(k)O(k) space for checking a substring has no duplicate characters, where kk is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
	 * @param input
	 * @return
	 */
	private static int bruteForce(String input) {
		int maxLength =0;
		int n = input.length();
		System.out.print( input + "     ");
		for(int i =0;i< n;i++)
		{
			for(int j =i+1;j<=n;j++) {
				if(allUnique(input, i,j ))
					maxLength= Math.max(maxLength, j-i);
			}
		}
		return maxLength;
	}

	/**
	 * Check if the part of the string defined by start and end has unique all elements.
	 * @param input
	 * @param start
	 * @param end
	 * @return
	 */
	private static boolean allUnique(String input, int start, int end) {
		Set<Character> set 	= new HashSet<>();
		for(int i=start ; i< end ;i++)
		{
			Character ch = input.charAt(i);
			if(set.contains(ch))
					return false;
			set.add(ch);
		}
		return true;
	}

}
