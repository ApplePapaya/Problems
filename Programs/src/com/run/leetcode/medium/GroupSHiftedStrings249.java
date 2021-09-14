package com.run.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 *     For example, "abc" can be shifted to be "bcd".
 *
 * We can keep shifting the string to form a sequence.
 *
 *     For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 *
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 *
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= strings.length <= 200
 *     1 <= strings[i].length <= 50
 *     strings[i] consists of lowercase English letters.
 */
public class GroupSHiftedStrings249 {

    public static void main(String[] args) {
        GroupSHiftedStrings249 g = new GroupSHiftedStrings249();
        System.out.println(g.groupStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z"}));
    }


    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strings.length; i++)
        {
            String shifted = shiftToA(strings[i]);

            if (!map.containsKey(shifted))
            {
                map.put(shifted, new ArrayList<>());
            }

            map.get(shifted).add(strings[i]);
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> list : map.values())
        {
            result.add(list);
        }

        return result;
    }

    private String shiftToA(String string)
    {
        char[] chars = string.toCharArray();
        int offset = chars[0] - 'a';

        for (int i = 0; i < chars.length; i++)
        {
            chars[i] = (char)(chars[i] - offset);
//Either add 26 only if negative or always add 26 and do %26 to handle when value goesbeqyond 26
            if (chars[i] < 'a')
            {
                chars[i] = (char)(chars[i] + 26);
            }
        }

        return String.valueOf(chars);
    }
//// time: O(N * M) N: len(strings) and M: average len(strings[i])
//// space: O(N * M)
    public List<List<String>> groupStrings4(String[] strings) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strings) {
            String key = key(str);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    private String key(String str) {
        char firstc = str.charAt(0);

        char[] cs = new char[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int v = c - firstc + 26;
            v %= 26;
            cs[v] += 1;
        }

        return new String(cs);
    }
    public List<List<String>> groupStrings6(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String string : strings) {
            StringBuilder differences = new StringBuilder();
            for (int i = 1; i < string.length(); i++) {
                int previous = string.charAt(i-1) - 'a';
                int current = string.charAt(i) - 'a';
                // this allows us find the difference when they loop around "az" -> "ab", diff = 25
                // and when previous > current "ab" != "ba", diffab = 1, diffba = 25
                int currentDifference = (current-previous+26)%26;
                differences.append(Integer.toString(currentDifference)).append(" "); // added space to be safe
            }

            String sbString = differences.toString();
            List<String> temp = map.get(sbString);
            if (temp == null) {
                temp = new ArrayList<>();
            }

            temp.add(string);
            map.put(sbString, temp);
        }

        return new ArrayList(map.values());
    }
    //Using stream

    public List<List<String>> groupStringsStream(String[] strings) {
        return Arrays.stream(strings)
                .collect(Collectors.groupingBy(this::normalize))
                .values()
                .stream()
                .collect(toList());
    }

    private List<Integer> normalize(String s) {
        return s.chars()
                .mapToObj(
                        c -> (c - s.charAt(0) + 26) % 26
                )
                .collect(toList());
    }
}
