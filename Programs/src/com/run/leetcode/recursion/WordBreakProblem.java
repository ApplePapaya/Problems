package com.run.leetcode.recursion;

import java.util.Arrays;
import java.util.List;

public class WordBreakProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> dict = Arrays.asList("this", "th", "is", "famous", "word", "break", "problemd",
				"br","bre");
		wordBreak("wordbreakproblem", dict, "");
	}

	public static void wordBreak(String st, List<String> dict, String output) {
		if(st.length() == 0) {
			System.out.println(output);
		}
		
		for(int i = 1; i < st.length(); i++) {
			String sub = st.substring(0, i);
			if(dict.contains(sub)) {
				wordBreak(st, dict, output + " " + sub);
				
			}
		}
	}
}
