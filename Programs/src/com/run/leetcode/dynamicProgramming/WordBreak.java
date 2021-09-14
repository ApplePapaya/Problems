package com.run.leetcode.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 * @author Home-Laptop
 *
 */
public class WordBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set.add("leet");
		set.add("code");
		System.out.println(wordBreak2("leetcodeleetcode",set));
	}

	/**
	 Complexity Analysis

	 nnn is the length of the input string.

	 Time complexity : OO(n^3). There are two nested loops, and substring computation at each iteration. Overall that results in OO(n^3) time complexity.

	 Space complexity : O(n). Length of ppp array is n+1n+1n+1.

	 **/
	//3ms solution
	public boolean wordBreakn3Solution(String s, List<String> wordDict) {
		boolean[] dp = new boolean[s.length() + 1];
		Set<String> set = new HashSet<>();
		set.addAll(wordDict);

		dp[0] = true;
		//Time complexity : O(n3)O(n^3)O(n3). There are two nested loops, and substring computation at each iteration. Overall that results in O(n3)O(n^3)O(n3) time complexity.
		for (int i = 1; i <= s.length(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				dp[i] = dp[j] && set.contains(s.substring(j, i));
				if(dp[i]) break;
			}
		}
		return dp[s.length()];
	}


	//Below is O(mn)  size of string * size of dict. if dict is big then its not efficent
	public static boolean wordBreak(String s, Set<String> dict) {
        boolean[] t = new boolean[s.length()+1];
        t[0] = true; //set first to be true, why?
        //Because we need initial state
 
        for(int i=0; i<s.length(); i++){
            //should continue from match position
            if(!t[i]) 
                continue;
 
            for(String a: dict){
                int len = a.length();
                int end = i + len;
                if(end > s.length())
                    continue;
 
                if(t[end]) continue;
 
                if(s.substring(i, end).equals(a)){
                    t[end] = true;
                }
            }
        }
 
        return t[s.length()];
    }
	
	
	// Below is o(n2) where n is length of string useful when dict is huge
	
	public static boolean wordBreak2(String s, Set<String> set) {
		int[] pos = new int[s.length()+1] ;
		
		Arrays.fill(pos, -1);
		for(int i =0; i < s.length(); i++) {
			if(pos[i] != -1) {// only if there are valid words until the ith position continue. Else no point checking ahead
				for(int j = i + 1; j<= s.length(); j++) {
					String sub = s.substring(i,j);
					if(set.contains(sub)) {
						pos[j] = i; // Mark the position as valid word so far
					}
				}
			}
		}
		
		return pos[s.length()] != -1;
		
	}

}
