package com.run.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.
Example 1:
Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:
Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
Note:
All strings contain only lowercase English letters.
It’s guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
Solution:
A naive approach would be finding all the match of the word in the text and track the position of the hit.
After finding all the match for every word, do some sorting like interval sorting. I found it tricky.
— Better Approach —
We can use the beauty of TreeMap which does natural sorting for us. We will use the starting position as a Key(K) and value will list(List<Integer>) of endings for all the word. Before creating the output pairs, we can take Key as the first value and Sort the list make pairs with the first value.
Example:
text = "ababa", words = ["aba","ab"]
Take the word “aba” and search it in the text. It will hit the 0th position and 2nd position. so Map would be
K   V
0 ->2
2 ->4
Now take “ab”, it will hit 0th and 2nd position. Update our map and it will look like below:
K   V
0 ->2,1
2 ->4,3
As we see that our map is naturally ordered already. So take the keys and sort the value before making the pairs.
K   V
0 ->1,2
2 ->3,4
So the final result would be:
[0,1][0,2][2,3][2,4]
 *
 *
 */
public class IndexPairsOfAString {

	public static void main(String[] args) {
		/*
		 * TreeMap<Integer, List<Integer>> map = new TreeMap<>(); map.computeIfAbsent(7,
		 * k -> new ArrayList<>()) .add(6); map.computeIfAbsent(7, k -> new
		 * ArrayList<>()) .add(8); System.out.println(map);
		 */
		String text = "thestoryofleetcodeandme";
		String[] words =  new String[]{"story", "fleet", "leetcode"};
		int[][] arr = findIndexPairs(text, words);
		for(int[] row : arr)
		{
			System.out.println(Arrays.toString(row));
		}
	}
	
	public static int[][] findIndexPairs(String text, String[] words){
		if(words.length ==0 || text.length() ==0)
		{
			return null;
		}
		TreeMap<Integer, List<Integer>> map = new TreeMap<>();
		for(int i =0;i< words.length;i++) {
			String word = words[i];
			//Get all the start indexes for the given word. End index will be computed based
			//on the word length.
			List<Integer> indexes = findWords(text, word);
			int n = word.length();
			for(int j=0;j<indexes.size();j++ ) {
				int start = indexes.get(j);
				int end = start + n-1;
				map.computeIfAbsent(start, k-> new ArrayList<Integer>()).add(end);
			}
		}
		System.out.println(map);
		// Converting the map contents to List of int[] arrays sorted containing the start
		// and ends
		ArrayList<int[]> resultList = new ArrayList<>();
		Iterator<Map.Entry<Integer, List<Integer>>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Integer, List<Integer>> entry = iterator.next();
			int key = entry.getKey();
			List<Integer> values = entry.getValue();
			Collections.sort(values);
			for(int i =0;i< values.size();i++) {
				resultList.add(new int [] {key, values.get(i)});
			}
		}
		
		int res[][] = new int[resultList.size()][2];
		for(int i =0;i< resultList.size();i++)
		{
			res[i] = resultList.get(i);
		}
		
		return res;
		
	}
	
	public static List<Integer> findWords(String textString, String word){
		List<Integer> indexes = new ArrayList<>();
		String ltextString = textString.toLowerCase();
		String lword = word.toLowerCase();
		int index =0;
		while (index != -1) {
			index = ltextString.indexOf(lword,index);
			if(index != -1) {
				indexes.add(index);
				index++;
			}
		}
		return indexes;
	}
}
