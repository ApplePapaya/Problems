package com.run.leetcode.slidingWindow;
/**
 * https://leetcode.com/problems/grumpy-bookstore-owner/
Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 

Note:

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
 *
 */
public class GrumpyBookstoreOwner {
	
	
	public static void main (String[] args) {
		int[] cust = {1,0,1,2,1,1,7,5};
		int[] grumpy= {0,1,0,1,0,1,0,1};
		int x= 3;
		System.out.println(maxSatisfiedCustomers(cust, grumpy,x));
	}
//Both cust and grumpy are of same length
	private static int maxSatisfiedCustomers(int[] cust, int[] grumpy, int x) {
		int maxSatisfied =0;
		int happyCustomers=0;
		int techniqueWind=0;
		
		for(int i=0;i< cust.length;i++ ) {
			happyCustomers += grumpy[i] ==0? cust[i] :0;// Calculate the customers who iwll be happy irrespective of the technique since storeperson isn ot grumpy
			techniqueWind += grumpy[i] ==1 ? cust[i] :0; // calculate the customers who will be unhappy because of grumpy customer and can probably be made happy with technique
			if(i>=x) {
				techniqueWind -= grumpy[i-x] ==1 ? cust[i-x] :0; // Sliding window by subtracting the customer count at position i-x -keeping window size as x.
			}
			maxSatisfied = Math.max(techniqueWind, maxSatisfied);// Max of the window giving customers happy
		}
		
		return maxSatisfied + happyCustomers;// Sum of happy customers coz store keeper is not grumpy and using technique max value.
	}

}
