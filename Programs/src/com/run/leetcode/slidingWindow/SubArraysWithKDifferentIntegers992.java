package com.run.leetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A good array is an array where the number of different integers in that array is exactly k.
 *
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 *
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 */

/**
 *
 *
 * Method 1 (Brute Force)
 *
 * If the length of string is n, then there can be n*(n+1)/2 possible substrings. A simple way is to generate all the substring and check each one whether it has exactly k unique characters or not. If we apply this brute force, it would take O(n*n) to generate all substrings and O(n) to do a check on each one. Thus overall it would go O(n*n*n).
 *
 * Method 2
 *
 * The problem can be solved in O(n*n). Idea is to maintain a hash table while generating substring and checking the number of unique characters using that hash table.
 * The implementation below assume that the input string contains only characters from ‘a’ to ‘z’.
 */
public class SubArraysWithKDifferentIntegers992 {

    /***

     To find directly k exact, we can use hashset and forloop and for every int i starting from 0. slide to window to have k diff integers. and keep moving right.
     Explaination and code in comments below. But that is O ( n^ 2)

     Concept - Two sliding windows

     Create two windows with a 'given end ptr' --> Note, this is very important. End ptr will always be fixed and same for the two windows in any given iteration.
     i. First window is where the startPtr points such that the given subArray has <= K distinct integers
     ii. Second window is where the startPtr points such that the given subArray has < K distinct integers
     VERY IMPORTANT - For a given array of length 'N' and 'always ending with last element', number of possible sub-arrays = N
     With above concept,
     i. Number of possible sub-arrays of first window = N (with <= K distinct integers)
     ii. Number of possible sub-arrays of second window = M (with < K distinct integers)
     Total number of sub-arrays with 'exactly' K distinct integers = N - M,
     since N = endPtr - startPtr1
     and M = endPtr - startPtr2
     N - M = startPtr2 - startPtr1
     Continue doing this till endPtr iterates from start till end of the input array


     **/
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Find the number of subarrays having atmost K distinct elements   --> v1
        // Find the number of subarrays having atmost K-1 distinct elements --> v2
        // return (v1 - v2)

        return countOneToK(nums, k) - countOneToK(nums, k-1);
    }
    public int countOneToK(int[] arr, int k)
    {
        if( k == 0 ) return 0;

        int n = arr.length;
        int total = 0;
        int diff = 0;  // number of different elements so far
        int start = 0;
        int[] count = new int[n+1];  // maintains the count of elements since 0 is not present and index is 0 we need to do +1

        for(int i=0; i<n; i++)
        {
            if(count[arr[i]] == 0)  // encountering the element for the first time
            {
                diff++;  // increment the diff elements found so far
            }
            count[arr[i]]++;
            if( diff > k )
            {
                // if the number of unique elements becomes > k
                // then move the start pointer to the point where
                // number of diff elements becomes <= k

              //  while( start < n && start <= i && diff > k)
                while( diff > k)
                {
                    count[arr[start]]--;  // decrement the count

                    if(count[arr[start]] == 0)
                    {
                        // if the count becomes 0, means that element will not be
                        // a part of the subarray
                        diff--;
                    }

                    start++;
                }
            }
            total += (i - start + 1);
        }

        return total;
    }

    public int countOneToKSKIPFYI(int[] arr, int k)
    {
        if( k == 0 ) return 0;

        int n = arr.length;
        int total = 0;
        int diff = 0;  // number of different elements so far
        int start = 0;
        int[] count = new int[n+1];  // maintains the count of elements since 0 is not present and index is 0 we need to do +1

        for(int i=0; i<n; i++)
        {
            if(count[arr[i]] == 0)  // encountering the element for the first time
            {
                diff++;  // increment the diff elements found so far
                count[arr[i]]++;  // increment its count
            }
            else
            {
                count[arr[i]]++;  // increment its count
            }

            /**  if( diff <= k )
             {
             //   total += (i - start + 1);
             // i.e add in the answer, the subarrays having unique elements <= diff and ending at i
             // between 1...k
             }**/
            if( diff > k )
            {
                // if the number of unique elements becomes > k
                // then move the start pointer to the point where
                // number of diff elements becomes <= k

                while( start < n && start <= i && diff > k)
                {
                    count[arr[start]]--;  // decrement the count

                    if(count[arr[start]] == 0)
                    {
                        // if the count becomes 0, means that element will not be
                        // a part of the subarray
                        diff--;
                    }

                    start++;
                }

                // now, start will be pointing at the point when diff <= k
                // therefore, we need to include that answer as well

                //  total += (i - start + 1);
            }
            total += (i - start + 1);
        }

        return total;
    }
    public int subarraysWithKDistinctHashMap(int[] A, int K) {
        int count = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        int left1 = 0;
        int left2 = 0;

        for (int right = 0; right < A.length; right++) {
            int val = A[right];
            map1.put(val, map1.getOrDefault(val, 0) + 1);
            map2.put(val, map2.getOrDefault(val, 0) + 1);

            while(map1.size() > K) {
                /**    int lval = A[left1];
                 int lc = map1.get(lval);
                 map1.put(lval, lc - 1);
                 if(lc - 1 == 0)
                 map1.remove(lval);
                 left1++;**/

                int l1 = map1.getOrDefault(A[left1], 0);
                if (l1 - 1 <= 0)
                    map1.remove(A[left1]);
                else
                    map1.put(A[left1], l1-1);
                left1++;
            }

            while(map2.size() >= K) { // > k-1
                /**  int lval = A[left2];
                 int lc = map2.get(lval);
                 map2.put(lval, lc - 1);
                 if(lc - 1 == 0)
                 map2.remove(lval);
                 left2++;**/


                int l2 = map2.getOrDefault(A[left2], 0);
                if (l2 - 1 <= 0)
                    map2.remove(A[left2]);
                else
                    map2.put(A[left2], l2-1);
                left2++;
            }

            count += left2 - left1;
        }

        return count;
    }


}


