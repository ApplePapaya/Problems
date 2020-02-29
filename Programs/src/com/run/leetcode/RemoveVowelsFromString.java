package com.run.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveVowelsFromString {

	public static void main(String[] args) {
		System.out.println(removeVowelsFromString("Happy ending starting loveeing"));

	}

	public static String removeVowelsFromString(String s) {
		StringBuilder sb = new StringBuilder();
		Set<Character> set  = new HashSet<>();
		set.addAll(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
		//Another way of initializingR
		Set<Character> set2 = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
		
		for(char c : s.toCharArray()) {
			if(!set.contains(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
