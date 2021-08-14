package com.run.leetcode.arrays;

import java.util.Arrays;

/**
 * Overview
 *
 * Calculating the area of every possible piece of cake can get out of hand very quickly. In the first example alone, there are already 12 pieces of cake, and our input can have up to 100,000 vertical and horizontal cuts. We need a smarter way to find the area of the largest piece of cake.
 *
 * The key insight to solve this problem is that not all of the pairs of horizontal and vertical cuts matter. Let's take a step back - the area of a cake piece is defined by width * height. If we were to consider only the horizontal cuts, then we would end up with many pieces of cake with width = w and varying heights. For each new piece, making a vertical cut will change the width, but not the height.
 *
 * Let's use the first test case in the problem description as an example. If we were to only apply the horizontal cuts [1, 2, 4], we will end up with 4 pieces of cake, all with width = 4. Take the piece of cake with height = 2 between the cuts at 2 and 4, and make any vertical cut you want - notice that the height will always be 2. The same logic can be applied when considering the vertical cuts first - we will have many pieces of cake with height = h and varying widths.
 *
 * Therefore, we know the largest piece of cake must have a height equal to the tallest height after applying only the horizontal cuts, and it will have a width equal to the widest width after applying only the vertical cuts.
 * Current
 * 1 / 3
 *
 * Approach: Sort
 *
 * Intuition
 *
 * As mentioned above, we can find the max height and the max width separately. Our final answer will be maxHeight * maxWidth. Each height and width is defined by the distance between 2 cuts. In the first example, the max height of 2 is defined by the distance between cuts 2 and 4 (4 - 2 = 2). To find all heights and widths, we must first sort our inputs horizontalCuts and verticalCuts. This will ensure that all of the cuts that are beside each other on the cake are also beside each other in the array. Then, we can iterate through the sorted inputs one at a time and find each height or width by simply taking the difference between two adjacent cuts.
 *
 * One thing to be careful about is the edges. For cuts in the middle, the distance is defined by the difference between two cuts. However, for the edges, they are defined by the cake's dimensions.
 *
 *     The top-most cut's height will be equal to horizontalCuts[0], while the bottom-most cut's height will be equal to h - horizontalCuts[horizontalCuts.length - 1].
 *     The left-most cut's width will be equal to verticalCuts[0], while the right-most cut's width will be equal to w - verticalCuts[verticalCuts.length - 1].
 *
 * Algorithm
 *
 *     Sort both horizontalCuts and verticalCuts in ascending order.
 *
 *     Initialize a variable maxHeight as the larger of the top and bottom edge: maxHeight = max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]).
 *
 *     Iterate through horizontalCuts starting from index 1 (skip the 0th index since it represents the edge cut, which we accounted for in the previous step). At each iteration, find the height defined by the ithi^{th}ith cut and the nearest cut above, horizontalCuts[i] - horizontalCuts[i - 1]. Update maxHeight if necessary.
 *
 *     Initialize a variable maxWidth as the larger of the left and right edge: maxWidth = max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]).
 *
 *     Iterate through verticalCuts starting from index 1. At each iteration, find the width defined by the ithi^{th}ith cut and the nearest cut to the left, verticalCuts[i] - verticalCuts[i - 1]. Update maxWidth if necessary.
 *
 *     Our maximum area is maxHeight * maxWidth. Don't forget the modulo 109+710^{9} + 7109+7, and depending on what language you're using, be careful of overflow. Return the maximum area.
 */
public class MaxAreaOfCakeAfterHorizontalAndVerticalCuts1465 {
    /**
     * Complexity Analysis
     *
     * Given NNN as the length of horizontalCuts and MMM as the length of verticalCuts,
     *
     *     Time complexity: O(N?log?(N)+M?log?(M))O(N \cdot \log(N) + M \cdot \log(M))O(N?log(N)+M?log(M))
     *
     *     Sorting an array of length nnn costs n?lognn \cdot lognn?logn time. We need to sort both horizontalCuts and verticalCuts, which is why both are present in the time complexity. Although we also iterate through both arrays, which costs O(N)O(N)O(N) and O(M)O(M)O(M) time, these iterations are not as expensive as the sorting, and by the rules of Big O, do not get included in the final time complexity.
     *
     *     Space complexity: O(1)O(1)O(1)
     *
     *     Regardless of the input size, we only ever need to use 2 variables: maxHeight and maxWidth.
     * @param h
     * @param w
     * @param horizontalCuts
     * @param verticalCuts
     * @return
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // Start by sorting the inputs
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int n = horizontalCuts.length;
        int m = verticalCuts.length;

        // Consider the edges first
        long maxHeight = Math.max(horizontalCuts[0], h - horizontalCuts[n - 1]);
        for (int i = 1; i < n; i++) {
            // horizontalCuts[i] - horizontalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible height
            maxHeight = Math.max(maxHeight, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        // Consider the edges first
        long maxWidth = Math.max(verticalCuts[0], w - verticalCuts[m - 1]);
        for (int i = 1; i < m; i++){
            // verticalCuts[i] - verticalCuts[i - 1] represents the distance between
            // two adjacent edges, and thus a possible width
            maxWidth = Math.max(maxWidth, verticalCuts[i] - verticalCuts[i - 1]);
        }

        // Be careful of overflow, and don't forget the modulo!
        return (int) ((maxWidth * maxHeight) % (1000000007));
    }
}
