package com.run.leetcode.hardProblems;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Hard
 *
 * 1780
 *
 * 295
 *
 * Add to List
 *
 * Share
 * Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 */
public class ExpressionAddOperators282 {
    /**
     * Input: num = "232", target = 8
     * Output: ["2*3+2","2+3*2"]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(addOperators("23", 8));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, num, 0, target, 0, 0);
        return res;

    }
    /**
     * When we use dfs to do this question, the most tricky part is that how to deal with multiplication. For every
     * addition and subtraction, we just directly adding or subtracting the new number. However, for multiplication,
     * we should multiply current number and previous number firstly, and then add previous previous number.
     * So we can use a variable preNum to record every previous number in each recursion step. If current recursive
     * call is trying multiplication, we should use previous calculation value subtract previous number, and then
     * adding multiplication result between previous number and current number.
     *
     * om second digit, we can choose + , - , * and empty space (in this case, eg. 12 was treated as one number), in total four kinds of choices. Also, remember there is a for loop inside every call stack, So, for the call stack of length N,
     * Total time complexity should be N * 4^(N - 1)
     *
     *  I'm going to say it's O(n 4^n). The input "000...000" is a special case where each operand is "0" and we never concatenate multiple digits together. In general, we can think of the problem like this: between each digit we have 4 different things we could do:
     * 1.) do nothing and concatenate the two digits together
     * 2.) add ' + ' between the two digits
     * 3.) add ' - ' between the two digits
     * 4.) add ' * ' between the two digits
     * Each call branches into 4 additional calls, and each call has a time complexity of O(n) which is the cost of copying a valid path to the res:
     * T(n) = 4T(n - 1) + O(n) which according to masters theorem gives O(n 4^n)
     * */
    public static void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) {
        if(pos == num.length()) {
            System.out.println(sb.toString());
            if(target == prev) res.add(sb.toString());
            return;
        }
        // start from first index of current position in num string, try all possible length of nums
        for(int i = pos; i < num.length(); i++) {
            // corner case: if current position is 0, we can only use it as a single digit number, should be 0
            // if it is not a single digit number with leading 0, it should be considered as an invalid number
           if(num.charAt(pos) == '0' && i != pos) {
                    break; //[2+3+0+2+1, 2+3-0+2+1, 2+3+2+1, 2+3*2*1, 2*3+0+2*1, 2*3-0+2*1, 2*3+2*1]
           }
            long curr = Long.parseLong(num.substring(pos, i + 1));
            int len = sb.length();
            // position 0 should be considered individually, since it does not have any operand character before curNum
            if(pos == 0) {
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr);
                sb.setLength(len);
            } else {
                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                sb.setLength(len);
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                sb.setLength(len);
                // trick part: to calculate multiplication, we should subtract previous number, and then add current
                // multiplication result to the subtraction result
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                sb.setLength(len);
            }
        }
    }
}
