package com.run.leetcode.arrays;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
public class BestTimeToBuyAndSellStockIII123 {

    /**
     Here, the oneBuy keeps track of the lowest price, and oneBuyOneSell keeps track of the biggest profit we could get.
     Then the tricky part comes, how to handle the twoBuy? Suppose in real life, you have bought and sold a stock and made $100 dollar profit. When you want to purchase a stock which costs you $300 dollars, how would you think this? You must think, um, I have made $100 profit, so I think this $300 dollar stock is worth $200 FOR ME since I have hold $100 for free.
     There we go, you got the idea how we calculate twoBuy!! We just minimize the cost again!! The twoBuyTwoSell is just making as much profit as possible.
     Hope this explanation helps other people to understand this!

     **/

    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for(int i = 0; i < prices.length; i++){
            oneBuy = Math.max(oneBuy, -prices[i]);//we set prices to negative, so the calculation of profit will be convenient
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);//we can buy the second only after first is sold
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }

        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }
/**
    var maxProfit = function(prices) {
        let oneBuyOneSell = 0;
        let twoBuyTwoSell = 0;
        let oneBuy = Number.POSITIVE_INFINITY
        let twoBuy = Number.POSITIVE_INFINITY;

        for(let i = 0; i < prices.length; i++) {
    const p = prices[i];
            oneBuy = Math.min(oneBuy, p);
            oneBuyOneSell = Math.max(oneBuyOneSell, p - oneBuy);
            twoBuy = Math.min(twoBuy, p - oneBuyOneSell);
            twoBuyTwoSell = Math.max(twoBuyTwoSell, p - twoBuy);
        }

        return twoBuyTwoSell;
    };

 **/
    public int maxProfit3(int[] prices) {
        int oneBuy = Integer.MAX_VALUE;
        int oneBuyProfit = 0;
        int twoBuy = Integer.MAX_VALUE;
        int twoBuyProfit = 0;

        for (int currentPrice : prices) {
            //update min
            if (currentPrice < oneBuy) {
                oneBuy = currentPrice;
            }
            //get profit
            if (currentPrice - oneBuy > oneBuyProfit) {
                oneBuyProfit = currentPrice - oneBuy;
            }
            //best profit
            if (currentPrice - oneBuyProfit < twoBuy) {
                twoBuy = currentPrice - oneBuyProfit;
            }
            //
            if (currentPrice-twoBuy>twoBuyProfit) {
                twoBuyProfit=currentPrice-twoBuy;
            }

        }

        return twoBuyProfit;//?
    }
    public static Integer twoTrades(Integer[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int[] bestTill = new int[prices.length];
        int minSoFar = Integer.MAX_VALUE, maxDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);
            maxDiff = Math.max(maxDiff, prices[i] - minSoFar);
            bestTill[i] = maxDiff;
        }
        int[] bestFrom = new int[prices.length];
        int maxSoFar = Integer.MIN_VALUE;
        maxDiff = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            maxSoFar = Math.max(maxSoFar, prices[i]);
            maxDiff = Math.max(maxDiff, maxSoFar - prices[i]);
            bestFrom[i] = maxDiff;
        }
        int maxTwoTrades = 0;
        for (int i = 0; i < prices.length; i++) {
            int maxSecondTrade = (i + 1 < prices.length) ? bestFrom[i + 1] : 0;
            maxTwoTrades = Math.max(maxTwoTrades, bestTill[i] + maxSecondTrade);
        }
        return maxTwoTrades;
    }
}
