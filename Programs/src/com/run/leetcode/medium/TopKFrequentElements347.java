package com.run.leetcode.medium;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 * @author Home-Laptop
 *
 */
public class TopKFrequentElements347 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(topKFrequent(new int[] {1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4}, 2));

	}
	//Bucket sort o(n) solution
	public List<Integer> topKFrequent2(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<>();

		for(int i : nums)
		{
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		List<Integer>[] arr = new List[nums.length + 1]; // Since for single element frequency minimum is 1 so we need +1 here

		for(int e : map.keySet())
		{
			int frequency = map.get(e);
			if(arr[frequency] == null)
			{
				arr[frequency] = new ArrayList<>();
			}
			arr[frequency].add(e);
		}

		List<Integer> res = new ArrayList<>();
		for(int i = arr.length - 1; i >= 0 && res.size() <= k; i--)
		{
			if(arr[i] != null && arr[i].size() > 0)
			{
				for(int j = 0; j < arr[i].size() ; j++)
				{
					res.add(arr[i].get(j));
					if(res.size() == k)
						return res;
				}
			}
		}

		return res;
	}

//nlogn solution or n logk
	public int[] topKFrequentMap(int[] nums, int k) {
		Map<Integer, Integer> counterMap = new HashMap<>();
		for(int num : nums) {
			int count = counterMap.getOrDefault(num, 0);
			counterMap.put(num, count+1);
		}

		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
		for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			pq.offer(entry);
			if(pq.size() > k) pq.poll();
		}

		// List<Integer> res = new LinkedList<>();
		int[] res = new int[k];
		int i = k - 1;
		while(!pq.isEmpty()) {
			// res.add(0, pq.poll().getKey());
			res[i--]  = pq.poll().getKey();
		}
		return res;
	}
	public static List<Integer> topKFrequent(int[] nums, int k) {

		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}

}
