package com.run.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 * @author Home-Laptop		
 *
 */
public class SubSetsWithDistinctNumsNoDuplicatesInAns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,3};
		List<List<Integer>> result = subSets(nums);
		for(List<Integer> rs : result)
			System.out.println(rs);
	}

	public static List<List<Integer>> subSets(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		//Arrays.sort(nums);//Is this reqd??
		backtrack(result, tempList, nums, 0);
		return result;
	}

	private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
		result.add(new ArrayList<>(tempList));
		System.out.println("TempList : "+tempList);
		for(int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			System.out.println("##Start :"+ start +   "   i :" + i +"  templist : " + tempList );
			backtrack(result, tempList, nums, i+1);
			System.out.println("Start :"+ start +   "   i :" + i +"  templist : " + tempList +"  templist.remove : "+ tempList.get(tempList.size()-1));
			tempList.remove(tempList.size()-1);
		}
		
	}
}
