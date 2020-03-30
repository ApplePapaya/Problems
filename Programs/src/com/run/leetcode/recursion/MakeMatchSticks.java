package com.run.leetcode.recursion;

public class MakeMatchSticks {
	static int count1, count2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,1,2,2,2};
		
		long t1 = System.nanoTime();

		System.out.println(makesquare(arr) + "      "+ count1 + "    " +(System.nanoTime() - t1));
		
		long t2 = System.nanoTime();
		
		System.out.println(makesquare22(arr) + "      " + count2 + "    " +(System.nanoTime() - t2));
	}
	
    
  public static boolean makesquare(int[] nums) {
  if(nums == null || nums.length == 0) return false;
  int sum = 0;
  for(int num : nums) {
      sum += num;
  }
  if(sum % 4 != 0) return false;
  int target = sum / 4;
  return dfs(new boolean[nums.length], nums, 0, target, 0, 4);
}

public static boolean dfs(boolean[] visited, int[] nums, int start, int target, int sum, int k) {
	count1++;
	if(k == 1) return true;
  if(target == sum) {
      return dfs(visited, nums, 0, target, 0, k - 1);
  }else if(sum > target) {
      return false;
  }
  for(int i = start; i < nums.length; i++) {
     if(visited[i]) continue;
      visited[i] = true;
      if(dfs(visited, nums, i + 1, target, sum + nums[i], k)) {
          return true;
      }
      visited[i] = false;
  }
  return false;
}



public static boolean makesquare22(int[] nums) {
    
        if(nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for(int j : nums)
            sum += j;
    
    if(sum % 4 != 0) return false;
    int side = sum / 4;
    return dfs22(nums, nums.length - 1, side, new int[4]);
    

}

public static boolean dfs22( int[] nums, int index, int side,  int[] target) {
	count2++;
if(index < 0)
  return true;

for(int i = 0; i < 4; i++)
{
    if(target[i] + nums[index] <= side)
    {
        target[i] += nums[index];
        if(dfs22(nums, index - 1, side, target)) return true;
        target[i] -= nums[index];
    }
}
return false;
}

}
