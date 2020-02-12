package com.run.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class JumpingOnTheClouds {

	public static void main(String[] args) {
		int[] c = {0,1,0,0,0,1,0};
		System.out.println("Fetch Count : "+ fetchCount(c));
		//System.out.printf("%05d",58);
		int n = 10;
		StringBuilder str= new StringBuilder(n);
		String fmt = "%"+n+"s\n";
		Map<String, Integer> map = new HashMap<>();
		map.put("abc",100);
		map.put("abc",10000);
		System.out.println(map);
		for(int i =0;i<10 ;i++) {
			str.append("#");
			System.out.printf(fmt,str);
		}
		

	}

	private static int fetchCount(int[] c) {
		int count =0;
		for(int i=0;i< c.length-1;i++) {
			if(i+2< c.length && c[i+2] == 0) {
				i++;
			}
			count++;
		}
		
		return count;
	}
}
