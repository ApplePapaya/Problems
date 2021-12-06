package com.run.leetcode.string;

import java.util.Stack;

public class ReverseOnlyLetters917 {
//o(1 ) space 2 pointer
    public String reverseOnlyLetters(String S) {
        int i = 0 , j = S.length() - 1 ;
        char[] ch = S.toCharArray();

        while(i < j) {
            if(!Character.isLetter(ch[i])) i++;
            else if(!Character.isLetter(ch[j])) j--;
            else{
                char temp = ch[i];
                ch[i] =ch[j];
                ch[j] = temp;
                i++;
                j--;
            }
        }
        return new String(ch);
    }

    //Using stack

    public String reverseOnlyLettersStack(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }

    public String reverseOnlyLettersSB(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }

        return ans.toString();
    }
}
