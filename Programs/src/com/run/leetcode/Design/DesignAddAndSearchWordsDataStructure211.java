package com.run.leetcode.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 500
 * word in addWord consists lower-case English letters.
 * word in search consist of  '.' or lower-case English letters.
 * At most 50000 calls will be made to addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure211 {
    /**

     HashMap solution passes all leetcode solution and has time complexity of O( M * N) where M is length of word and N is number of words.
     Also for large data sets, once hash table increase in size, there will be hash collisions and search time complexity could degrade to
     O(( N ^ 2 ) * M))

     Trie can use less space compared to hashmap when storing keys with same prefix. Trie has only O(M * N) time complexity where M is length of

     Complexity Analysis
     M is a length of the word to find, and N is the number of words.

     Time complexity is O(M) for well defined words without dots where M is key length  and N is number of keys
     and O ( N * 26 ^ M) for undefined words.

     Space is O(1) for search of well defined words and O(M) for undefined words.


     GO FOR TRIE


     **/

    class WordDictionary {



        /** Initialize your data structure here. */
        Map<Integer, List<String>> map;//can use set here
        public WordDictionary() {
            map = new HashMap<>();
        }

        public void addWord(String word) {

            int len = word.length();
            List<String> list =map.get(len);

            if (list ==null){
                list = new ArrayList<String>();
                map.put(len,list);
            }
            list.add(word);

        }

        public boolean search(String word) {
            int len = word.length();
            List<String> list =map.get(len);

            if (list == null)
                return false;

            for (String listWord: list){
                if(isEqual(listWord,word)){
                    return true;
                }
            }

            return false;

        }

        private boolean isEqual(String listWord, String word){

            char inp2[]=listWord.toCharArray();
            char inp1[]= word.toCharArray();

            for( int i =0; i <inp1.length;i++){
                if (inp1[i]=='.'){
                    continue;
                }
                else if (inp1[i]!=inp2[i]){
                    return false;
                }
            }

            return true;
        }


    }



 TrieNode root;

 /// Initialize your data structure here.
 public DesignAddAndSearchWordsDataStructure211( ) {
 this.root = new TrieNode();
 }

 //  /** Adds a word into the data structure.
 public void addWord(String word) {
 TrieNode node = root;
 for(char ch : word.toCharArray()){
 if (node.child[ch - 'a'] == null){
 node.child[ch - 'a'] = new TrieNode();
 }
 node = node.child[ch - 'a'];
 }
 node.isWord = true;
 }

 // /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
 public boolean search(String word) {
 return search(word, this.root, 0);
 }

 private boolean search(String word, TrieNode node, int index){

 if (index == word.length()) return node.isWord;

 if(word.charAt(index) != '.'){
 return (node.child[word.charAt(index) - 'a'] != null  &&
 search(word, node.child[word.charAt(index) - 'a'], index+1));
 }else{
 for(TrieNode n : node.child){
 if (n != null && search (word, n, index+1)) return true;
 }
 return false;
 }

 }
 }

 class TrieNode{
 TrieNode[] child;
 boolean isWord;

 TrieNode(){
 this.child = new TrieNode[26];
 this.isWord = false;
 }
 }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

/**



 class WordDictionary {

 private ArrayList<String>[] dict = new ArrayList[501];


 public WordDictionary() {}


 public void addWord(String word) {
 ArrayList<String> a = dict[word.length()];

 if (a == null)
 a = new ArrayList();

 a.add(word);
 dict[word.length()] = a;
 }


 public boolean search(String word) {
 if (dict[word.length()] == null)
 return false;

 ArrayList<String> a = dict[word.length()];

 for (String s : a) {
 boolean match = true;

 for(int i=0;i<word.length() && match;i++) {
 if(word.charAt(i) == '.')
 continue;
 else if(s.charAt(i) != word.charAt(i))
 match = false;
 }

 if(match)
 return true;
 }

 return false;
 }
 }


 **/