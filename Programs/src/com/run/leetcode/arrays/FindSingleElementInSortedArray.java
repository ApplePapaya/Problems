package com.run.leetcode.arrays;


//Divide and conquer using binary search
// Time o(logn) an space is o(1)
public class FindSingleElementInSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	  public static int singleNonDuplicate(int[] nums) {
	        
	        return searchSingleElement(nums, 0, nums.length -1);
	    }
	  
	   public static int searchSingleElement(int[] nums, int low, int high){
	        int result =0;
	        if(low > high)
	            return 0;
	        if(low == high)
	            return nums[low];
	        
	        int mid = (low + high)/2;
	        
	        if(mid % 2 == 0)// even number of elements-- mid will even and needs to be checked with next element
	        {
	            if(nums[mid] == nums[mid +1] ){// this implies left side of the array is fine and we need to search right side
	             result = searchSingleElement(nums, mid + 2, high);
	            }else{
	              result=  searchSingleElement(nums, low, mid);
	            }
	        }else if(mid % 2 ==1){
	            if(nums[mid] == nums[mid-1]){
	              result=  searchSingleElement(nums, mid + 1, high);
	            } else{
	               result = searchSingleElement(nums, low, mid);
	            }
	        }
	        return result;
	    }

}
