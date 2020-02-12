package com.run.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * Example:
 * 
 * Input: "aab" Output: 1 Explanation: The palindrome partitioning ["aa","b"]
 * could be produced using 1 cut.
 * 
 * @author Home-Laptop
 *
 */
public class PalindromePartitioning {

	public static void main(String[] args) {
		System.out.println(partition("aab"));
		System.out.println(allPossiblePalindromes("aab"));

	}
	
	//DP APPROACH BELOW
	
	public static List<String> allPossiblePalindromes(String s){
		List<String> result = new ArrayList<>();
		if(s== null ) {
			return result;
		}
		if(s.length() <= 1) {// empty string or string with one char should be resturned
			result.add(s);
			return result;
		}
		
		int n = s.length();
		boolean dp[][] = new boolean[n][n];
		for(int l= 0;l<n ;l++) {
			for (int i= 0;i<n-l ;i++) {
				int j = i+l;
				if(s.charAt(i) == s.charAt(j) && ((j-i<=2) || (dp[i+1][j-1] ==true))) {
					dp[i][j] = true;
					result.add(s.substring(i,j+1));
				}
				
			}
		}
		
		return result;
	}
	
	
	// DFS APPROACH BELOW
	// Check the generics issue.. leet code has updated
	public static ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		ArrayList<String> partition = new ArrayList<String>();// track each possible partition
		addPalindrome(s, 0, partition, result);
		return result;
	}
//Approach using DFS
	private static void addPalindrome(String s, int start, ArrayList<String> partition, ArrayList<ArrayList<String>> result) {
		// stop condition
		if (start == s.length()) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				partition.add(str);
				System.out.println(" i :" + i  +" start : " + start  + " partition :" + partition);
				addPalindrome(s, i, partition, result);
				System.out.println(" i :" + i  +" start : " + start  + " partition :" + partition);
				partition.remove(partition.size() - 1);
				System.out.println(" i :" + i  +" start : " + start  + " partition :" + partition);
			}
		}
	}

	private static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
