	package com.run.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReverseVowels {

	public static void main(String[] args) {
	System.out.println(reverseVowels("leetcode is fun"));

	}

	public static String reverseVowels(String s) {
		 List<Character> vowels = new ArrayList<>();
	        vowels.add('a');
	        vowels.add('e');
	        vowels.add('i');
	        vowels.add('o');
	        vowels.add('u');
	        vowels.add('A');
	        vowels.add('E');
	        vowels.add('I');
	        vowels.add('O');
	        vowels.add('U');
	        
	        int i =0;
	        int j = s.length()-1;
	        
	        char[] arr = s.toCharArray();
	        
	        while(i<j) {
	        	if(!(vowels.contains(arr[i]))) {
	        		i++;
	        		continue;
	        	}
	        	if(!(vowels.contains(arr[j]))) {
	        		j--;
	        		continue;
	        	}
	        	// Both i and j are pointing to vowels now
	        	char temp = arr[i];
	        	arr[i] = arr[j];
	        	arr[j] = temp;
	        	i++;
	        	j--;
	        	
	        }
		return new String(arr);
	}
}
