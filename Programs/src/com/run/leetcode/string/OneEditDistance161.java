package com.run.leetcode.string;

/**
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 *     Insert exactly one character into s to get t.
 *     Delete exactly one character from s to get t.
 *     Replace exactly one character of s with a different character to get t.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 *
 * Example 2:
 *
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 *
 * Example 3:
 *
 * Input: s = "a", t = ""
 * Output: true
 *
 * Example 4:
 *
 * Input: s = "", t = "A"
 * Output: true
 *
 *
 *
 * Constraints:
 *
 *     0 <= s.length <= 104
 *     0 <= t.length <= 104
 *     s and t consist of lower-case letters, upper-case letters and/or digits.
 */
public class OneEditDistance161 {
    /**
     *
     Get the length of both s & t strings as l1 and l2
     If they differ more than 1, they are more than one edit distance apart, so straight return false.
     Have two pointers i and j, starting with 0th index in s & t strings respectively and loop until they are same.
     Now we are at a point where both chars pointed by i and j are different or we reach end of both strings.
     If we reach end of both strings, it means both strings are same, so straight return false as same strings cannot be one edit distance apart.
     If l1 > l2, which means s is longer than t, so only deletion is possible. Ignore the current character s[i], and see if remaining of s[i+1, l1] matches with t[j, l2]. If matches they are one edit distance apart, else no.
     If l1 < l2, which means s is shorter than t, so only insertion is possible. Assuming we insert t[j] at ith index in s and see if remaining of s[i, l1] matches with t[j+1, l2]. If matches they are one edit distance apart, else no.
     If l1 == l2, which means s and t are of same length, so only replacing is possible. Assuming we replace s[i] with t[j] and see if remaining of s[i+1, l1] matches with t[j+1, l2]. If matches they are one edit distance apart, else no.

     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (Math.abs(n-m) > 1) {
            return false;
        }

        if (n < m) {
            return isOneEditDistance(t, s);
        }

        for (int i = 0; i < m; i++) {
            if (t.charAt(i) != s.charAt(i)) {
                if (n == m) {
                    return compare(s, t, i+1, i+1);//delete this char from both and compare ther est
                }

                return compare(s, t, i+1, i);//remove this character from larger strin and compare the rest
            }
        }

        return n == m + 1;//just remve the last character
    }

    private boolean compare(String s, String t, int i, int j) {
        int si = i;
        int ti = j;

        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) != t.charAt(ti)) {// since already one mismatchis done, this is second and hence false
                return false;
            }

            si++;
            ti++;
        }

        return si == s.length() && ti == t.length();// have we reached the end . if yes true.
    }
}
