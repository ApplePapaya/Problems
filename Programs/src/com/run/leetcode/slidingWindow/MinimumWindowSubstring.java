package com.run.leetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * @author prparasu
 *
 */
public class MinimumWindowSubstring {

	public static void main(String[] args) {
		System.out.println(minimumWindow("ADOBECODEBANC", "ABC")); //BANC

	}

	private static String minimumWindow(String input, String t) {
		String window ="";
		Map<Character, Integer> map = new HashMap<>();
		for(int i =0;i <t.length();i++) {
			map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0)+1);
		}
		System.out.println(map);
		for(int i =0, j=0;j< input.length();j++)
		{
			
		}
		
		return window;
	}

}
