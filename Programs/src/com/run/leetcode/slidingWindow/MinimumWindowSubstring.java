package com.run.leetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC" Note:
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there is such window, you are guaranteed that there will
 * always be only one unique minimum window in S.
 * 
 * @author prparasu
 *
 */
public class MinimumWindowSubstring {

	public static void main(String[] args) {
		System.out.println(minimumWindow("ADOBECODEBANC", "ABC")); // BANC
		System.out.println(minimumWindow("CBAAAAABBBCCC", "ABC")); // BANC
		
		System.out.println(minimumWindowCorrect("ab", "b")); // BANC
		System.out.println(minimumWindowCorrect("AJOBAZZZZZZZZZZZZBANC", "ABC")); // BANC
		System.out.println(minimumWindowCorrect("CBAAAAABBBCCC", "ABC")); // BANC
		

	}
//The below checks only in the order of the T
	private static String minimumWindow(String S, String T) {
		int start = 0;
		String result = "";
		while (start < S.length()) {
			int j = 0;
			for (int i = start; i < S.length(); i++) {
				if (S.charAt(i) == T.charAt(j) && j == 0) {
					start = i;
				}
				if (S.charAt(i) == T.charAt(j)) {
					j++;
				}
				if (j == T.length()) {
					if (result.equals("") || (i - start + 1) < result.length()) {
						result = S.substring(start, i + 1);
					}
					start = start + 1;
					break;
				}
				if (i == S.length() - 1) {
					return result;
				}
			}
		}
		return result;
	}
	
	
	private static String minimumWindowCorrect(String S, String T) {
		int start = 0;
		String result = "";
		int min = Integer.MAX_VALUE;
		
		if(S.equals(T)){
            return S;
        }
		S.contains(T)
		//Map<Character, Integer>  map = new HashMap<>();
		int[] arrT = new int[256];
		int[] arrS;// = new int[256];
		for(int i =0;i< T.length();i++)
		{
		//	map.put(T.charAt(i), map.getOrDefault(T.charAt(i), 0) +1);
			arrT[T.charAt(i)]++;
		}
		int count = T.length();
		//System.out.println(map);
		while (start <= S.length() - T.length() ) {
			//int j = 0;
			count =0;
			arrS = new int[256];
			for (int i = start; i < S.length(); i++) {
				if((arrT[S.charAt(i)] >0 )&& (arrT[S.charAt(i)] > arrS[S.charAt(i)])) {
					count++;
					arrS[S.charAt(i)]++;
					
				}
				
				if(count == T.length())
				{
					if(min > i- start+1) {
						min = i-start +1;
						result = S.substring(start,i+1);
						
					}
					start++;
					break;
				}
				if(i== S.length()-1) {
					start++;
					// break;
				}
					
			
			}
		}
		return result;
	}

}
