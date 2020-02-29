package com.run.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubsetsWithDuplicatesNumsNoDupsInAnswer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = subsets(new int[] {1,2,2});
		for(List<Integer> rs : result) {
			System.out.println(rs);
		}

	}

	public static List<List<Integer>> subsets(int[] nums){
		
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		backTrack(result, tempList, nums, 0);
		return result;
			
	}
	
	public static void backTrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start){
		result.add(new ArrayList<>(tempList));
		for(int i = start; i< nums.length; i++) {
			if(i >  start && nums[i] == nums[i-1]) continue;
			tempList.add(nums[i]);
			backTrack(result, tempList, nums, i+1);
			tempList.remove(tempList.size() -1 );
		}
	}
}
