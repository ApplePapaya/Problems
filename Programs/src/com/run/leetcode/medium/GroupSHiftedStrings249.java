package com.run.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class GroupSHiftedStrings249 {

    public static void main(String[] args) {
        GroupSHiftedStrings249 g = new GroupSHiftedStrings249();
        System.out.println(g.groupStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z"}));
    }
//// time: O(N * M) N: len(strings) and M: average len(strings[i])
//// space: O(N * M)
    public List<List<String>> groupStrings(String[] strings) {

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
