package com.run.leetcode.string;

import java.util.*;

/**
 * You are given a string s and an array of strings words. You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words. If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag. If two substrings wrapped by bold tags are consecutive, you should combine them.
 *
 * Return s after adding the bold tags.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcxyz123", words = ["abc","123"]
 * Output: "<b>abc</b>xyz<b>123</b>"
 *
 * Example 2:
 *
 * Input: s = "aaabbcc", words = ["aaa","aab","bc"]
 * Output: "<b>aaabbc</b>c"
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 1000
 *     0 <= words.length <= 100
 *     1 <= words[i].length <= 1000
 *     s and words[i] consist of English letters and digits.
 *     All the values of words are unique.
 */
public class AddBoldTagToAString616 {

    public String addBoldTagBooleanArray(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }

        return result.toString();
    }


    /* use two pointer l and r to indicate the boundary index of the bold region
     *if the current character is within the bold region, extend the right boundry of the bold region
     * otherwise add the current bold region, append the character, and update the left boundy of the bold region...
     */

        public String addBoldTag2pointer(String s, String[] dict) {
            if (dict == null || dict.length == 0) return s;
            int l = 0, r = -1, n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (String word : dict) {
                    if (word.length() > n - i) continue;
                    if (s.startsWith(word, i)) {
                        r = Math.max(r, i + word.length() - 1); //update right boundry
                    }
                }
                if (r < i) { //char i not in the bold region...
                    if (r - l + 1 > 0) { //add the current bold region
                        sb.append("<b>" + s.substring(l, r + 1) + "</b>");
                    }
                    sb.append(s.charAt(i)); //append character i
                    l = i + 1; //update left boundry
                }
            }
            if (r >= l) { //add bold reigion if have one
                sb.append("<b>" + s.substring(l, r + 1) + "</b>");
            }
            return sb.toString();
        }




    /// Merge Intervals Logic

    public String addBoldTag(String s, String[] dict) {
        List<Interval> intervals = new ArrayList<>();
        for (String str : dict) {
            int index = -1;
            index = s.indexOf(str, index);
            while (index != -1) {
                intervals.add(new Interval(index, index + str.length()));
                index +=1;
                index = s.indexOf(str, index);
            }
        }
        System.out.println(Arrays.toString(intervals.toArray()));
        intervals = merge(intervals);
        System.out.println(Arrays.toString(intervals.toArray()));
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        for (Interval interval : intervals) {
            sb.append(s.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        if (prev < s.length()) {
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }

    class Interval {
        int start, end;
        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        public String toString() {
            return "[" + start + ", " + end + "]" ;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals) {
            if (i.start <= end) {
                end = Math.max(end, i.end);
            } else {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
}
