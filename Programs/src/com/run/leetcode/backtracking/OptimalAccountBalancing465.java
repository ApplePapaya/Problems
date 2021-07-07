package com.run.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 *
 * Return the minimum number of transactions required to settle the debt.
 *
 *
 *
 * Example 1:
 *
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 *
 * Example 2:
 *
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 *
 *
 * Constraints:
 *
 *     1 <= transactions.length <= 8
 *     transactions[i].length == 3
 *     0 <= fromi, toi <= 20
 *     fromi != toi
 *     1 <= amounti <= 100
 *     Solution Explaination That is because the for loop is working on start and trying to find i which is > start and have a different sign.
 * Once the first such i is found we dump everything from start to i so logically start is now free of any debt/credit.
 * And the next call/recurse to settle in the Math.min tries to find the next min number of settlement given this newly modified list of debt.
 * In this newly modified list only the start is made free of debt/credit not the elements between start to i, so we recurse on start + 1 instead of i.
 * i may be having some debt/credit left after freeing the start.
 * And after that the debt.set after the Math.min set the i back to original/previous value and the for loop then search of another such i which is > current i and tries to do the same.
 * (Meaning the for loop finds all the possible combinations of start and i that differs in sign(using the for loop) and tries the settlement for all such cases with Recursion(recursive call to settle() after doing the settlement between start and i -> This settlement only frees start and not the i).)
 * (The overall logic is more of Backtracking in essence than DFS)
 *
 * Now for the Time Complexity.
 * Since we are incrementing the start for each recursive call, the loop will be in the fashion of N, N-1, N-2, ... 1 which is N!
 * So the Time complexity is O(N!)
 * Space Complexity is O(N) for the map.
 * N is the number of transaction.
 *
 * The RT can be improved by some heuristics before calling the settle(Backtracking function) as follows.
 * 1> Settle all the debts that can be done in a single transaction prior to calling the Backtracking functions. Such transactions are debt.get(i) + debt.get(j) == 0.
 */
public class OptimalAccountBalancing465 {

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : transactions) {
            m.put(t[0], m.getOrDefault(t[0], 0) - t[2]);
            m.put(t[1], m.getOrDefault(t[1], 0) + t[2]);
        }
        return settle(0, new ArrayList<>(m.values()));
    }

    int settle(int start, List<Integer> debt) {
        //if there is no debt for that indexed person because he did give and take equally we can ignore him
        while (start < debt.size() && debt.get(start) == 0)
            start++;

        if (start == debt.size()) return 0;//if all trxns nullify then no trxn required to settele the debt
        int r = Integer.MAX_VALUE;
        //Ekse go through the rest of the trxns except the start. Make the start to 0 either by credit or debit and using
        //another i person and using backtracking check the number of trxn required further
        for (int i = start + 1; i < debt.size(); i++)
            if (debt.get(start) * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + debt.get(start));
                r = Math.min(r, 1 + settle(start + 1, debt));
                debt.set(i, debt.get(i) - debt.get(start));
            }
        return r;
    }
}
