package com.run.leetcode.dfs;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 * De Bruijn sequence
 * The sequence can be used to shorten a brute-force attack on a PIN-like code lock that does not have an
 * "enter" key and accepts the last n digits entered. For example, a digital door lock with a 4-digit code
 * (each digit having 10 possibilities, from 0 to 9) would have B?(10, 4) solutions, with length 10000.
 * Therefore, only at most 10000 + 3 = 10003 (as the solutions are cyclic) presses are needed to open the lock.
 * Trying all codes separately would require 4 × 10000 = 40000 presses.
 */

/**
 * There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].
 *
 * The safe has a peculiar way of checking the password. When you enter in a sequence, it checks the most recent n digits that were entered each time you type a digit.
 *
 *     For example, the correct password is "345" and you enter in "012345":
 *         After typing 0, the most recent 3 digits is "0", which is incorrect.
 *         After typing 1, the most recent 3 digits is "01", which is incorrect.
 *         After typing 2, the most recent 3 digits is "012", which is incorrect.
 *         After typing 3, the most recent 3 digits is "123", which is incorrect.
 *         After typing 4, the most recent 3 digits is "234", which is incorrect.
 *         After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
 *
 * Return any string of minimum length that will unlock the safe at some point of entering it.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 2
 * Output: "10"
 * Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.
 *
 * Example 2:
 *
 * Input: n = 2, k = 2
 * Output: "01100"
 * Explanation: For each possible password:
 * - "00" is typed in starting from the 4th digit.
 * - "01" is typed in starting from the 1st digit.
 * - "10" is typed in starting from the 3rd digit.
 * - "11" is typed in starting from the 2nd digit.
 * Thus "01100" will unlock the safe. "01100", "10011", and "11001" would also unlock the safe.
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 4
 *     1 <= k <= 10
 *     1 <= kn <= 4096
 */
public class CrackingTheSafe753 {
//10100011020021112012122022210
    public static void main(String[] args) {
        CrackingTheSafe753 c = new CrackingTheSafe753();
        System.out.println(c.crackSafe3(3, 3));

    }
    public String crackSafe(int n, int k) {
        // String is immutable, use StringBuilder
        StringBuilder result = new StringBuilder();
        // This is total number of permutation we need in the set
        int total = (int) (Math.pow(k, n));

        // This is just for fun and to prove that we can start with any permutation
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
           // result.append(rand.nextInt(k));
            result.append(0);
        }

        Set<String> visited = new HashSet<>();
        visited.add(result.toString());

        dfs(result, total, visited, n, k);

        return result.toString();
    }

    private boolean dfs(StringBuilder result, int target, Set<String> visited, int n, int k) {
        if (visited.size() == target) {
            // We are done, terminate all recursion
            return true;
        }
        String prev = result.substring(result.length() - n + 1, result.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                result.append(i);
                if (dfs(result, target, visited, n, k)) {
                    // recursively terminate, we are done
                    return true;
                }
                else {
                    // We got stuck so this will not yield optimal path, go back
                    // Try other path
                    visited.remove(next);
                    result.delete(result.length() - 1, result.length());
                }
            }
        }
        return false;
    }

    //3ms solution
    Set<Integer> seen = new HashSet<Integer>();
    StringBuffer ans = new StringBuffer();
    int highest;
    int k;

    public String crackSafe2(int n, int k) {
        highest = (int) Math.pow(10, n - 1);
        this.k = k;
        dfs(0);
        for (int i = 1; i < n; i++) {
            ans.append('0');
        }
        return ans.toString();
    }

    public void dfs(int node) {
        for (int x = 0; x < k; ++x) {
            int nei = node * 10 + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei % highest);
                ans.append(x);
            }
        }
    }

    //1ms solution
    int node = 0;
    public String crackSafe3(int n, int k) {
        node = (int) Math.pow(k, n-1);
        boolean[] side = new boolean[node*k];
        StringBuilder sb = new StringBuilder();
        dfs(side, n, k, 0, sb);
        for (int i = 0; i < n-1; ++i) sb.append('0');
        return sb.toString();
    }

    private void dfs(boolean[] side, int n, int k, int i, StringBuilder sb) {
        int curNode = i % node;
        for (int j = 0; j < k; ++j) {
            int t = curNode * k + j;
            if (!side[t]) {
                side[t] = true;
                dfs(side, n, k, t, sb);
                sb.append((char)(j+'0'));
            }
        }
    }
}

