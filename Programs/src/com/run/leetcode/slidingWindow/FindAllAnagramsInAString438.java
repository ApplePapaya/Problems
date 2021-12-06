package com.run.leetcode.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class FindAllAnagramsInAString438 {

    /**
     *
     * Here for the input anagram, find the count of characters present. We can either use 256 int size or
     * use 26 and do - 'a' since only lower characters are used.
     *
     * 1> Define int array of 26 to store the count.
     * 2> Define result array list to store the left indices
     * 3> intialize count variable = p.length, left pointer start = 0 , right pointer end = 0
     * 4> While right pointer < s.length
     *      1> find the character at right
     *      2> if its count is > 0 in the array, then decrement the count( basically is in present in p yes then 1
     *      char got)
     *      3> decrement the character count from array.
     *      4> Increment the right pointer
     *      5> is the count == 0 -> did we get all the characters of p in s? yes then add the left index to list
     *      6> is the window right - left = p.length?
     *              1> get the left most character and increase its count in the array.
     *              2> if the count is 0 or greater than 0, increase its. any character not prsenet in p will have
     *              negative value in the array. so if its 0 or positive it implies we will be missing in one char of p.
     *              3> increment the value in the count array.
     *              4> increwase the left pointer.
     *
     *
     *       7> return result
     *
     *
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] arr = new int[26];
        List<Integer> res = new ArrayList<>();
        for(char c : p.toCharArray())
        {
            arr[c - 'a']++;

        }
        int count = p.length();
        int start = 0;
        int end = 0;
       // int index = 0;
        while(end < s.length())
        {
            char c = s.charAt(end);

            if(arr[c - 'a'] > 0 ) count--;
            arr[c - 'a']--;
            end++;

            if(count == 0)
                res.add(start);

            if(end - start == p.length())
            {
                if(arr[s.charAt(start) - 'a'] >= 0)
                    count++;
                arr[s.charAt(start) - 'a']++;
                start++;
            }
        }
        return res;
    }
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;

        int[] hash = new int[256]; //character hash

        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right)] >= 1) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                list.add(left);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() ) {

                if (hash[s.charAt(left)] >= 0) {
                    count++;
                }
                hash[s.charAt(left)]++;
                left++;

            }


        }
        return list;
    }
}
