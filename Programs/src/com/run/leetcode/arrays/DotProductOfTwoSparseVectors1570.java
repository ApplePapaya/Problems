package com.run.leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 *     SparseVector(nums) Initializes the object with the vector nums
 *     dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 *
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 *
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 *
 * Example 3:
 *
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 *
 * Constraints:
 *
 *     n == nums1.length == nums2.length
 *     1 <= n <= 10^5
 *     0 <= nums1[i], nums2[i] <= 100
 */
public class DotProductOfTwoSparseVectors1570 {
    /**
     * Intuition
     * We only need to store the values > 0, as we dont need to waste computing on calculating for all 0 valued indexes. We are also storing the distinct index-value pairs in a map.
     *
     * Algorithm
     *
     *     If either of the two vectors are blank, return 0. dont need to go forward
     *     If one of them is large, always use the one which has less items,
     *     Finally, we calculate the dot_product by matching the index of first with that of the second vector
     *
     * Our Solution:
     * O(min(dm, dn)), where d denotates distinct values only
     */




    Map<Integer, Integer> indexMap = new HashMap<>();
    int n = 0;
    DotProductOfTwoSparseVectors1570(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0)
                indexMap.put(i, nums[i]);
        n = nums.length;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(DotProductOfTwoSparseVectors1570 vec) {
        if (indexMap.size() == 0 || vec.indexMap.size() == 0) return 0;
        if (indexMap.size() > vec.indexMap.size())
            return vec.dotProduct(this);
        int productSum = 0;
        for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
            int index = entry.getKey();
            Integer vecValue = vec.indexMap.get(index);
            if (vecValue == null) continue;
            productSum += (entry.getValue() * vecValue);
        }
        return productSum;
    }

    /**
     *
     *  Using HashSet
     * Complexity Analysis
     *
     * Let nnn be the length of the input array and LLL be the number of non-zero elements.
     *
     *     Time complexity: O(n)O(n)O(n) for creating the Hash Map; O(L)O(L)O(L) for calculating the dot product.
     *
     *     Space complexity: O(L)O(L)O(L) for creating the Hash Map, as we only store elements that are non-zero. O(1)O(1)O(1) for calculating the dot product.
     */

    // Map the index to value for all non-zero values in the vector
    Map<Integer, Integer> mapping;
/** Uncomment this for this section
    DotProductOfTwoSparseVectors1570(int[] nums) {
        mapping = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                mapping.put(i, nums[i]);
            }
        }
    }
**/
    public int dotProduct2(DotProductOfTwoSparseVectors1570 vec) {
        int result = 0;

        // iterate through each non-zero element in this sparse vector
        // update the dot product if the corresponding index has a non-zero value in the other vector
        for (Integer i : this.mapping.keySet()) {
            if (vec.mapping.containsKey(i)) {
                result += this.mapping.get(i) * vec.mapping.get(i);
            }
        }
        return result;
    }


    /**
     * Index Value pairs
     *
     */

    /**
     * Complexity Analysis
     *
     * Let nnn be the length of the input array and LLL and L2L_{2}L2? be the number of non-zero elements for the two vectors.
     *
     *     Time complexity: O(n)O(n)O(n) for creating the <index, value> pair for non-zero values; O(L+L2)O(L+L_{2})O(L+L2?) for calculating the dot product.
     *
     *     Space complexity: O(L)O(L)O(L) for creating the <index, value> pairs for non-zero values. O(1)O(1)O(1) for calculating the dot product.
     * @param vec
     * @return
     */
    List<int[]> pairs;

    DotProductOfTwoSparseVectors1570(int[] nums, int a) {//int a is dummy to remove
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }


    // Return the dotProduct of two sparse vectors
    public int dotProduct3(DotProductOfTwoSparseVectors1570 vec) {
        int result = 0, p = 0, q = 0;
        while (p < pairs.size() && q < vec.pairs.size()) {
            if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                p++;
                q++;
            }
            else if (pairs.get(p)[0] > vec.pairs.get(q)[0]) {
                q++;
            }
            else {
                p++;
            }
        }
        return result;
    }
}
