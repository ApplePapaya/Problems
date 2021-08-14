package com.run.leetcode.binarySearch;

public class KthMissingPositiveInteger1539 {

    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[] {2, 3, 4, 7, 11}, 5));
    }

    public static int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            // If number of positive integers
            // which are missing before arr[pivot]
            // is less than k -->
            // continue to search on the right.
            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
                // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }

        // At the end of the loop, left = right + 1,
        // and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is
        // arr[right] - right - 1 -->
        // the number to return is
        // arr[right] + k - (arr[right] - right - 1) = k + left
        return left + k;
    }
    public int findKthPositiveLinear(int[] arr, int k) {
        int n = 0;
        int start = 1;
        for(int i = 0; i < arr.length; )
        {
            if(arr[i] == start)
            {
                i++;
                start++;
            }else{
                n++;
                start++;
            }
            System.out.println(start);
            if(n == k)
                return start - 1;
        }
        start--;
        while(n != k)
        {
            start++;
            n++;
        }

        return start;

    }
}
