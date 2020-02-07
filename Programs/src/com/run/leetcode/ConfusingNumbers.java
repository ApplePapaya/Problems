package com.run.leetcode;
/**
 * https://github.com/varunu28/LeetCode-Java-Solutions/blob/master/Easy/Confusing%20Number.java
 * http://www.voidcn.com/article/p-oomyxxqq-bza.html
 * 
 * Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 */
public class ConfusingNumbers {

	public static void main(String[] args) {
		
		System.out.println(isConfusingNumbers(11));
		
		System.out.println(isConfusingNumberCharARray(89));
		System.out.println(5/2);
	}
	
	private static boolean isConfusingNumbers(int n) {
		if(n==0) {
			return false;
		}
		int copy = n;
		StringBuilder sb = new StringBuilder();
		while(n>0) {
			int temp = n%10;
			if(temp ==6 || temp ==9) {
				sb.append(temp==6?9 :6);
			}else if(temp==0 || temp == 1 || temp == 8) {
				sb.append(temp);
			}else {
				return false;
			}
			n /=10;
		}
		System.out.println("Rotated number : " +sb.toString());
		return Integer.parseInt(sb.toString()) != copy;
	}
	
	public static boolean isConfusingNumberCharARray(int n) {
		char[] rotate = {'0','1','$','$','$','$','9','$','8','6'};
		char[] num = Integer.toString(n).toCharArray();
		boolean res = false;
		for(int l=0,r = num.length-1;l<=r ; l++,r--) {
			if(rotate[num[l]-'0'] == '$' || rotate[num[r] -'0'] =='$')
				return false;
			if(rotate[num[l] -'0'] != num[r])
				res = true;
		}
		return res;
	}

}
