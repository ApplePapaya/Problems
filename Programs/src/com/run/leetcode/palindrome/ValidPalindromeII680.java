package com.run.leetcode.palindrome;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aba"
 * Output: true
 * Example 2:
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 *
 * Input: s = "abc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII680 {


    public boolean validPalindrome(String s) {
        return isPalindrome(s, 0);
    }

    private boolean isPalindrome(String s, int cnt) {
        int i = 0, j = s.length() - 1;
        for (; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                if (cnt == 0) {
                    //startIndex : starting index is inclusive
                    //  endIndex : ending index is exclusive
                    if (isPalindrome(s.substring(i, j), cnt + 1)) return true;
                    if (isPalindrome(s.substring(i + 1, j + 1), cnt + 1)) return true;
                }
                return false;
            }
        }

        return true;
    }




    public boolean validPalindrome6ms(String s) {
        int f=0,l=s.length()-1;
        while(f<l){
            if(s.charAt(f)!=s.charAt(l))
                return check(s,f+1,l) || check(s,f,l-1);
            f++; l--;
        }
        return true;
    }
    private boolean check(String s,int f,int l){
        while(f<l){
            if(s.charAt(f)!=s.charAt(l)) return false;
            f++; l--;
        }
        return true;
    }
}
