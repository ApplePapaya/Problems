package com.run.leetcode.string;

import java.util.Arrays;

/**
 * Given an array of keywords words and a string s, make all appearances of all keywords words[i] in s bold. Any letters between <b> and </b> tags become bold.
 *
 * Return s after adding the bold tags. The returned string should use the least number of tags possible, and the tags should form a valid combination.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["ab","bc"], s = "aabcd"
 * Output: "a<b>abc</b>d"
 * Explanation: Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
 *
 * Example 2:
 *
 * Input: words = ["ab","cb"], s = "aabcd"
 * Output: "a<b>ab</b>cd"
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 500
 *     0 <= words.length <= 50
 *     1 <= words[i].length <= 10
 *     s and words[i] consist of lowercase English letters.
 */
public class BoldWordsInAString758 {

    public String addBoldTag(String s, String[] dict) {
        int boldTill = -1;
        StringBuffer result = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            for (String word: dict)
                if (s.startsWith(word, i)) {
                    if (boldTill<i) result.append("<b>");
                    boldTill = Math.max(boldTill, i+word.length());
                }
            if (i==boldTill) result.append("</b>");
            result.append(s.charAt(i));
        }
        if (boldTill == s.length()) result.append("</b>");
        return result.toString();
    }


    public String boldWords(String[] words, String S) {
        boolean[] bold = new boolean[S.length() + 1];
        for (String w : words) {
            int start = S.indexOf(w, 0);
            while (start != -1) {
                Arrays.fill(bold, start, start + w.length(), true);
                start = S.indexOf(w, start + 1);
            }
        }
        StringBuilder r = new StringBuilder().append(bold[0] ? "<b>" : "");
        for (int i = 0; i < bold.length - 1; i++) {
            r.append(S.charAt(i));
            if (!bold[i] && bold[i + 1]) r.append("<b>");
            else if (bold[i] && !bold[i + 1]) r.append("</b>");
        }
        return r.toString();
    }
}
