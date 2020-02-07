package com.run.leetcode;

import java.util.Arrays;

public class ReverseWordsInAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="the sky is blue";
		char[] c = s.toCharArray();
		reverseWords(c);
	}

	public static void reverseWords(char[] s) {
		int i =0;
		for(int j =0;j<s.length;j++) {
			if(s[j] ==' ') {
				reverse(s, i, j-1);
				i=j+1;
			}
		}
		System.out.println("i : " +i);
		System.out.println(Arrays.toString(s));
		
		reverse(s, i, s.length-1);
		System.out.println(Arrays.toString(s));
		
		reverse(s, 0,s.length-1);
		System.out.println(Arrays.toString(s));
		
		
		
	}
	
	public static void reverse(char[] s, int i, int j){
		while(i<j){
		char temp = s[i];
		s[i]=s[j];
		s[j]=temp;
		i++;
		j--;
		}
}
}
