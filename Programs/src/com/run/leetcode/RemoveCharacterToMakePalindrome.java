package com.run.leetcode;

import java.util.Arrays;

public class RemoveCharacterToMakePalindrome {

	public static void main(String[] args) {
		String[] arr = {"abcbca","abbc","abcba",""};
		Arrays.stream(arr)
		           .forEach(s-> System.out.println(String.format("%s  %d" ,s,isSingleCharRemovalMakeValidParantheses(s))));

	}

	private static int isSingleCharRemovalMakeValidParantheses(String s) {
		int low =0; int high = s.length()-1;
		while(low<high) {
			if(s.charAt(low) == s.charAt(high)) {
				low++;
				high--;
			}else {
				if(isPalindrome(s, low+1 , high)) {
					return low;
				}
				
				if(isPalindrome(s, low, high-1)) {
					return high;
				}
				return -1;
			}
			
			
		}return -2;
	}

	private static boolean isPalindrome(String s, int low, int high) {
		while(low<high) {
			if(s.charAt(low)== s.charAt(high)) {
				low++;
				high--;
			}else {
				return false;
			}
		}
		return true;
	}

}
