package com.run.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = permuteUnique(new int[] {1,2,3});
		for(List<Integer> rs : result) {
			System.out.println(rs);
		}

	}

	public static List<List<Integer>> permuteUnique(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		backtrack(result, tempList, nums);
		return result;
	}
	
	private static 	void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
		if(tempList.size() == nums.length) {
			result.add(new ArrayList<>(tempList));
		}else {
			for(int i=0; i< nums.length;i++) {
				if(tempList.contains(nums[i])) continue;
				tempList.add(nums[i]);
				backtrack(result, tempList, nums);
				tempList.remove(tempList.size() -1);
			}
		}
	}
}
