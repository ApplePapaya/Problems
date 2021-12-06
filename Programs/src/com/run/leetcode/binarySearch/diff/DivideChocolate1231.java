package com.run.leetcode.binarySearch.diff;

import java.util.Arrays;

/***
 You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

 You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.

 Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

 Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.



 Example 1:

 Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 Output: 6
 Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

 Example 2:

 Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 Output: 1
 Explanation: There is only one way to cut the bar into 9 pieces.

 Example 3:

 Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 Output: 5
 Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]



 Constraints:

 0 <= k < sweetness.length <= 104
 1 <= sweetness[i] <= 105


 */
public class DivideChocolate1231 {


    /**
     In this problem, the left boundary left is the minimum of sweetness, that is left = min(sweetness), since left is the smallest possible sweetness for this piece of chocolate. (There is no piece with sweetness less than left!)

     The right boundary right is the total sweetness of the chocolate bar (total) divided by the number of people k + 1 , that is total / (k + 1). It is the largest possible sweetness we can get for ourselves. Note that if the sweetness of the chunk for us is larger than this value, say total / (k + 1) + 1, this will still be the smallest sweetness. Thus, the total sweetness is no less than (total / (k + 1) + 1) * (k + 1) which is strictly larger than total, meaning any value larger than total / (k + 1) is impossible.

     Therefore, we can assign the lower boundary left = min(sweetness) and the upper boundary right = total / (k + 1). The optimal minimum sweetness must be within this range.

     Since we are looking for the maximum possible value, thus the middle value should be mid = (left + right + 1) / 2.

     Algorithm

     Set up the two boundaries (left and right) of the search space, that is: left = 1, right = total / (k + 1).

     Get the middle value from left and right, that is mid = (left + right + 1) / 2.

     Check if we can cut the chocolate into k + 1 pieces with sweetness no less than mid, where mid is our current guess at the optimal workable value.

     If cutting the chocolate bar in this method results in everyone receiving a piece of chocolate that is at least as sweet as mid, then let left = mid. Otherwise, let right = mid - 1.

     Repeat the steps 2, 3, and 4 until the two boundaries overlap, i.e., left == right, which means that you have found the maximum total sweetness of a piece you can receive by cutting the chocolate bar optimally. We can return either left or right as the answer.


     Complexity Analysis

     Let nnn be the number of chunks in the chocolate and SSS be the total sweetness of the chocolate bar.

     Time complexity: O(n?log?(S/(k+1)))O(n \cdot \log(S / (k + 1)))O(n?log(S/(k+1)))

     The lower and upper bounds are min(sweetness) and S / (k + 1) respectively. In the worst case (when k is small), the right boundary will have the same magnitude as S, and the left boundary will be 1. Thus, the maximum possible time complexity for a single binary search is O(log?S)O(\log S)O(logS). For every single search, we need to traverse the chocolate bar in order to allocate chocolate chunks to everyone, which takes O(n)O(n)O(n) time.

     Space complexity: O(1)O(1)O(1)

     For every search, we just need to count the number of people who receive a piece of chocolate with enough sweetness, and the total chocolate sweetness for the current people, both only cost constant space.

     **/
    public int maximizeSweetness(int[] sweetness, int k) {

        int low = 1;
        int high = Integer.MAX_VALUE;
        int max = 1;
        while(low <= high) {
            final int mid = low + (high - low)/2;
            if(canDivideChoclate(sweetness, mid , k + 1)) {
                // can eat mid
                if(mid > max) {
                    max = mid;
                }
                // try for larger
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return max;
    }

    private boolean canDivideChoclate(final int[] sweetness, final int target, final int k) {
        final int n = sweetness.length;
        int i = 0;
        int curr = 0;
        int count = 0;
        while(i < n) {
            curr += sweetness[i];
            if(curr >= target) {
                curr = 0;
                count++;
            }
            i++;
        }
        return count >=k;
    }
    public int maximizeSweetness222(int[] sweetness, int k) {
        // Initialize the left and right boundaries.
        // left = 1 and right = total sweetness / number of people.
        int numberOfPeople = k + 1;
        int left = Arrays.stream(sweetness).min().getAsInt();
        int right = Arrays.stream(sweetness).sum() / numberOfPeople;

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // cur_sweetness stands for the total sweetness for the current person.
            // people_with_chocolate stands for the number of people that have
            // a piece of chocolate of sweetness greater than or equal to mid.
            int mid = (left + right + 1) / 2;
            int curSweetness = 0;
            int peopleWithChocolate = 0;

            // Start assigning chunks to the current people,.
            for (int s : sweetness) {
                curSweetness += s;

                // If the total sweetness for him is no less than mid, meaning we
                // have done with him and should move on to assigning chunks to the next people.
                if (curSweetness >= mid) {
                    peopleWithChocolate += 1;
                    curSweetness = 0;
                }
            }

            // Check if we successfully give everyone a piece of chocolate with sweetness
            // no less than mid, and eliminate the search space by half.
            if (peopleWithChocolate >= numberOfPeople) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the maximum possible sweetness we can get.
        return right;
    }
}
