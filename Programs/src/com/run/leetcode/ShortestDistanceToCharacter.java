package com.run.leetcode;

import java.util.Arrays;

public class ShortestDistanceToCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(shortestDistanceToCharacter("loveleetcode", 'e')));

	}
	
	public static int[] shortestDistanceToCharacter(String s, char c) {
		int n = s.length();
		int result[] = new int[n];
		int distance = n;
		
		for(int i =0;i<n ;i++) {
			distance = s.charAt(i) == c ? 0 : distance +1;
			result[i] = distance;
		}
		
		for(int i = n-1; i >= 0;i--) {
			distance = s.charAt(i) ==c ? 0 : distance +1;
			result[i] = Math.min(distance, result[i]);
		}
		return result;
	}

}
