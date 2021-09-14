package com.run.leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This is an interactive problem.
 *
 * You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 * Output: You guessed the secret word correctly.
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Example 2:
 *
 * Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
 * Output: You guessed the secret word correctly.
 *
 *
 * Constraints:
 *
 * 1 <= wordlist.length <= 100
 * wordlist[i].length == 6
 * wordlist[i] consist of lowercase English letters.
 * All the strings of wordlist are unique.
 * secret exists in wordlist.
 * numguesses == 10
 */
public class GuessTheWord843 {
    /**
     * Intuition
     * Take a word from wordlist and guess it.
     * Get the matches of this word
     * Update our wordlist and keep only the same matches to our guess.
     *
     * This process is straight forward.
     * However, the key point is, which word should we guess from all of the wordlist?
     * @param wordlist
     * @param master
     */
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[wordlist2.size()]);
        }
    }
    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }


    /**
     * Solution 3: Minimax
     * Now we want to try a better solution.
     * Generally, we will get 0 matches from the master.guess.
     * As a result, the size of wordlist reduces slowly.
     *
     * Recall some math here, the possiblity that get 0 matched is:
     * (25/26) ^ 6 = 79.03%
     *
     * That is to say, if we make a blind guess,
     * we have about 80% chance to get 0 matched with the secret word.
     *
     * To simplify the model,
     * we're going to assume that,
     * we will always run into the worst case (get 0 matched).
     *
     * In this case,
     * we have 80% chance to eliminate the candidate word
     * as well as its close words which have at least 1 match.
     *
     * Additionally, in order to delete a max part of words,
     * we select a candidate who has a big "family",
     * (that is, the fewest 0 matched with other words.)
     * We want to guess a word that can minimum our worst outcome.
     *
     * So we compare each two words and count their matches.
     * For each word, we note how many word of 0 matches it gets.
     * Then we guess the word with minimum words of 0 matches.
     *
     * In this solution, we apply a minimax idea.
     * We minimize our worst case,
     * The worst case is max(the number of words with x matches),
     * and we assume it equal to "the number of words with 0 matches"
     *
     * Time complexity O(N^2)
     * Space complexity O(N)
     */

    public void findSecretWordMinMax(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1, 0) + 1);
            String guess = "";
            int min0 = 100;
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < min0) {
                    guess = w;
                    min0 = count.getOrDefault(w, 0);
                }
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }
    /**
     * Solution 4: Count the Occurrence of Characters
     * In the previous solution, we compaired each two words.
     * This make the complexity O(N^2) for each turn.
     *
     * But actually we don't have to do that.
     * We just need to count the occurrence for each character on each position.
     *
     * If we can guess the word that not in the wordlist,
     * we can guess the word based on the most frequent character on the position.
     *
     * Here we have to guess a word from the list,
     * we still can calculate a score of similarity for each word,
     * and guess the word with highest score.
     *
     * Time complexity O(N)
     * Space complexity O(N)
     */

    public void findSecretWordCountOccurenceOfCharacters(String[] wordlist, Master master) {
        for (int t = 0, x = 0; t < 10 && x < 6; ++t) {
            int count[][] = new int[6][26], best = 0;
            for (String w : wordlist)
                for (int i = 0; i < 6; ++i)
                    count[i][w.charAt(i) - 'a']++;
            String guess = wordlist[0];
            for (String w: wordlist) {
                int score = 0;
                for (int i = 0; i < 6; ++i)
                    score += count[i][w.charAt(i) - 'a'];
                if (score > best) {
                    guess = w;
                    best = score;
                }
            }
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }
}
class Master{
    public static int guess(String word) {
        return new Random().nextInt(6);
    }
}