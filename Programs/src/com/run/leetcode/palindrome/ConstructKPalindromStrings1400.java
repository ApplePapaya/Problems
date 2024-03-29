package com.run.leetcode.palindrome;

import java.sql.Time;
import java.util.concurrent.locks.Condition;

/**
 * 1400. Construct K Palindrome Strings
 * Medium
 *
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 *
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 *
 * Example 2:
 *
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 *
 * Example 3:
 *
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 *
 * Example 4:
 *
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 *
 * Example 5:
 *
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 10^5
 *     All characters in s are lower-case English letters.
 *     1 <= k <= 10^5
 *
 *         Condition 1. odd characters <= k
 *     Count the occurrences of all characters.
 *     If one character has odd times occurrences,
 *     there must be at least one palindrome,
 *     with odd length and this character in the middle.
 *     So we count the characters that appear odd times,
 *     the number of odd character should not be bigger than k.
 *
 *             Condition 2. k <= s.length()
 *     Also, if we have one character in each palindrome,
 *     we will have at most s.length() palindromes,
 *     so we need k <= s.length().
 *
 *     The above two condition are necessary and sufficient conditions for this problem.
 *     So we return odd <= k <= n
 *
 *             Construction
 *
 *     @spjparmar immediately got a question like why this works always for all strings.
 *     He gave the some following dry runs. :)
 *     For any string with 0 odd character count , we can form k no. of palindrome strings for sure with k<=n
 *             (This is why k<=n)
 *
 *     eg
 *             aabb, k=1| abba
 *     aabb, k=2 | aa, bb
 *             aabb, k=3 | a, a, bb
 *     aabb, k=4 | a, a, b, b
 *
 *     For any string with odd character count <=k , we can always form k palindrome string for sure with k<=n
 *             eg2
 *     aabbc, k=1 | aacbb
 *             aabbc, k=2 | aca, bb
 *     aabbc, k=3 | a,a, bcb
 *             aabbc, k=4 | a, a, c ,bb
 *     aabbc, k=5 | a, a, c, b, b
 *
 *             eg3
 *     aabc, k=1 | N/A
 *             aabc, k=2 | aba, c
 *     aabc, k=3 | aa, b, c
 *             aabc, k=4 | a, a, b, c
 *
 *     Hope this helps somebody.
 *
 *     Complexity
 *
 *     Time O(N)
 *     Space O(1)
 *
 *     Java:
 */
public class ConstructKPalindromStrings1400 {


    public boolean canConstruct(String s, int k) {


        /*
        the number of charecters with odd count need
        to be less than or equal to K only then we can
        make K palindromes
        also if string length is shorter than K we can't
        make K palindromes
        */

        if(s.length() < k) {
            return false;
        }
        /**
         IF K >26 then, definitely odd count is less than  or equal to 26
         and it satisfies the condition of odd count < k
         k >= 26 as well works
         **/
        if(k>26)//this save 3 ms if not for this will be 4 ms instead of 1ms
            return true;

        int[] str = new int[26];
        for(char c:s.toCharArray()){
            str[c-'a']++;
        }

        int oddCount = 0;
        for(int i=0; i<26; i++){
            if(str[i]%2!=0)
                oddCount++;
        }

        if(oddCount>k)
            return false;

        return true;
    }
    //14ms
//14 ms Adding the checks for k < s.length and if k > 26 then odd count is definitely less than k, we can return true.
    public boolean canConstruct2(String s, int k) {
        int odd = 0, n = s.length(), count[] = new int[26];
        for (int i = 0; i < n; ++i) {
            count[s.charAt(i) - 'a'] ^= 1;// Exoring will flip 1 to 0 and 0 to 1. So if odd will be 1 and even will be 0
            odd += count[s.charAt(i) - 'a'] > 0 ? 1 : -1;// Always odd will first become 1 for a character and if we get it even,
            //then -1 to remove the count from odd.
        }
        return odd <= k && k <= n;
    }

    public boolean canConstructBasic(String s, int k) {
        int odd = 0;
        int n = s.length();
        int[] arr = new int[26];

        //count number of odd chars
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (int i : arr) {
            if (i % 2 == 1) odd++;
        }

        return odd <= k && k <= n;
    }
}
