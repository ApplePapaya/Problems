package com.run.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/submissions/
public class MinimumNumberOfStepsToMakeStringsAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minSteps("practice", "leetcode"));
		System.out.println(minSteps2("aab", "bba"));
	}

	 public static int minSteps(String s, String t) {
	        int[] count = new int[26];
	        int res =0;
	               
	        for(char c : s.toCharArray()){
	            ++count[c-'a'];
	        }
	        
	        for(char c : t.toCharArray()){
	            --count[c-'a'];
	           
	        }
	        
	        return Arrays.stream(count)
	                      .map(Math :: abs)
	                       .sum()/2 ;
	        
	    }
	    
	    
	     public static int minSteps2(String s, String t) {
	        int[] count = new int[26];
	        int res =0;
	        int freq=0;
	        
	        for(char c : s.toCharArray()){
	            ++count[c-'a'];
	        }
	        
	        for(char c : t.toCharArray()){
	            --count[c-'a'];
	            if(count[c-'a']<0)
	                res++;
	        }
	        
	        return res;
	        
	    }
	    
	    
	    
}
