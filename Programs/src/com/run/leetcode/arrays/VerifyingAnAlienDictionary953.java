package com.run.leetcode.arrays;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '?', where '?' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAnAlienDictionary953 {


    /**
     * Complexity analysis
     *
     * Let NN be the length of order, and MM be the total number of characters in words.
     *
     * Time complexity : O(M)O(M).
     *
     * Storing the letter-order relation of each letter takes O(N)O(N) time. For the nested for-loops, we examine each pair of words in the outer-loop and for the inner loop, we check each letter in the current word. Therefore, we will iterate over all of letters in words.
     *
     * Taking both into consideration, the time complexity is O(M + N)O(M+N). However, we know that NN is fixed as 2626. Therefore, the time complexity is O(M)O(M).
     *
     * Space complexity : O(1)O(1). The only extra data structure we use is the hashmap/array that serves to store the letter-order relations for each word in order. Because the length of order is fixed as 2626, this approach achieves constant space complexity.
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {

        // Create a int array of size 26 and set value as mentioned in the order  as mentioned in string order.
        // The compare the values for the minimum length word with the otder.
        //if the comparison is same for the smaller word like app and apple, then compare the length.

        int[] charMap = new int[26];

        for(int i = 0; i < 26; i++)
        {
            charMap[order.charAt(i) - 'a'] = i;
        }

        for(int i = 1; i < words.length; i++)
        {
            if(compare(words[i-1], words[i], charMap) > 0)
                return false;
        }

        return true;

    }


    public int compare(String word1, String word2, int[] charMap){

        int i = 0;
        int c = 0;

        while(i < word1.length() && i < word2.length() && c == 0)
        {
            c = charMap[word1.charAt(i) - 'a'] - charMap[word2.charAt(i) - 'a'];
            i++;
        }

        if(c == 0)
        {
            return word1.length() - word2.length();
        }
        else
            return c;

    }
}
