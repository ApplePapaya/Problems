package com.run.leetcode.palindrome;
/**
 * Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
 * @author Home-Laptop
 *
 */
public class PalindromePermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(canPermutePalindrome("abcd"));
		System.out.println(canPermutePalindrome("abcdabcde"));
	}
	/**
	 * For a string to be permuted to valid palindrome, it should have even number
	 * of characters for all characters except 1 for which it can have odd number.
	 * Hence the mod %2 check to check there is only one odd count of char.
	 * If satisfied, then it can be made as palindrome.
	 * @param s
	 * @return
	 */
	public static boolean canPermutePalindrome(String s) {
		int[] c = new int[128];
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			c[s.charAt(i) - 'a']++;
		}
		
		for(int i = 0; i < 128; i++) {
			count += c[i] % 2;
		}
		return count <= 1;
	}

}
