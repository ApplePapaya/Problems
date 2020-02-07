package com.run.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s = {"eat","tea","tan","ate","nat","bat"};
		System.out.println(groupAnagrams(s));
	}
	
	public static List<List<String>> groupAnagrams(String[] s){
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<>();
		
		for(String str :s) {
			char[] arr = new char[26];//26 alphabets
			for(int i=0;i< str.length();i++) {
				arr[str.charAt(i) -'a']++;
			}
			String key = new String(arr);
			if(map.containsKey(key)) {
				map.get(key).add(str);
			}else {
				List<String> list = new ArrayList<>();
				list.add(str);
				map.put(key, list);
			}
		}
		
		result.addAll(map.values());
		return result;
	}

}
