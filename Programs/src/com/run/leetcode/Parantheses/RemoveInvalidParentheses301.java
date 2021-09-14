package com.run.leetcode.Parantheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 */
public class RemoveInvalidParentheses301 {

    /**

     Time complexity should be O(N*2^N). O(2^N) states reachable across backtracking, with worst-case O(N) computation for converting StringBuilder to string per state.

     You are correct. Though I believe it is more evolved. Time complexity should be of form O(2^N + k*N), where k is the number of leaves in backtracking tree which form balanced expression. Now, k can vary from [1, ..., 2^N)]. Max_k is for the case when all the children in tree are valid expressions.

     **/
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        helper(0, 0, s.toCharArray(), res, '(', ')');
        return res;
    }

    private void helper(int last_i, int last_j, char[] s, List<String> res, char ch1, char ch2){
        int count = 0;

        for(int i = last_i; i < s.length; i++){
            if(s[i] == ch1){
                count++;
            }
            else if(s[i] == ch2){
                count--;
            }
            if(count< 0){//// We have an extra closed paren we need to remove
                for(int j = last_j; j <= i; j++){
                    if(s[j] == ch2 && (j == last_j || s[j] != s[j-1])){//// Try removing one at each position, skipping duplicates
                        char[] newS = deleteFromStr(s, j);
                        helper(i, j, newS, res, ch1, ch2);//// Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                    }
                }
                return;// Stop here. The recursive calls handle the rest of the string.
            }
        }

        int n = s.length;
        char[] newS = new char[n];
        n--;
        for(int i = 0; i < s.length; i++, n--){
            newS[i] = s[n];
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        if(ch1 == '('){
            helper(0, 0, newS, res, ch2, ch1);
        }
        else{
            res.add(new String(newS));
        }

    }

    private char[] deleteFromStr(char[] s, int j){
        int n = s.length;
        char[] newS = new char[n-1];
        int m = 0;

        for(int i = 0; i < n; i++){
            if(i != j){
                newS[m++] = s[i];
            }

        }
        return newS;
    }

    /**
     Time complexity: you have a length n string, every character have 2 states "keep/remove", that is 2^n states and check valid is O(n). All together O(n*2^n). This means there is a lot of duplicates. Ideally it should be O(C(n, k) + n) where k is the number of chars needs remove. Use a O(n) time to preprocess and get the value k.
     **/
    public static List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();
        char[] check = new char[]{'(', ')'};
        dfs(s, res, check, 0, 0);
        return res;
    }

    public static void dfs(String s, List<String> res, char[] check, int last_i, int last_j) {
        int count = 0;
        int i = last_i;
        while (i < s.length() && count>= 0) {

            if (s.charAt(i) == check[0]) count ++;
            if (s.charAt(i) == check[1]) count --;
            i ++;
        }

        if (count >= 0)  {
            // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
            String reversed = new StringBuffer(s).reverse().toString();
            if (check[0] == '(')
                dfs(reversed, res, new char[]{')', '('}, 0, 0);
            else
                res.add(reversed);

        }

        else {  // extra ')' is detected and we have to do something
            i -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
            for (int j = last_j; j<= i; j++) {
                //to avoid duplicates second condition is added
                if (s.charAt(j) == check[1] && (j == last_j || s.charAt(j-1) != check[1])) {
                    dfs(s.substring(0, j) + s.substring(j+1, s.length()), res, check, i, j);
                }
            }
        }
    }
}
