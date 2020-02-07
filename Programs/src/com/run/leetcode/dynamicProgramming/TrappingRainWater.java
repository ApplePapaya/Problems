package com.run.leetcode.dynamicProgramming;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class TrappingRainWater {
	public static void main(String[] args) throws Exception{
		File text = new File("/demo/src/main/resources/geekForGeeks/medium/trappingRainWater.txt");
		TrappingRainWater obj = new TrappingRainWater();
		File file = obj.getFileFromResources("trappingRainWater.txt");

		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("/demo/src/main/resources/geekForGeeks/medium/trappingRainWater");
		Scanner scan = new Scanner(file);
		int testCases = scan.nextInt();
		for(int i=0;i<testCases;i++) 
		{
			int N = scan.nextInt();
			int[] a = new int[N];
			for(int j=0;j<N;j++)
			{
				a[j] = scan.nextInt();
			}
			findWater(a);
		}
	

	}

	
	private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
	private static void findWater(int[] a) {
		int ans = 0;
		int[] leftMax = new int[a.length];
		int[] rightMax = new int[a.length];
		leftMax[0] = a[0];
		rightMax[a.length-1] =a[a.length-1];
		for(int i =1;i< a.length;i++)
		{
			leftMax[i] = Math.max(leftMax[i-1], a[i]);
		}
		for(int j=a.length-2;j>=0;j--)
		{
			rightMax[j] = Math.max(rightMax[j+1], a[j]);
		}
		
		for(int k=1;k<a.length-1;k++)
		{
			ans += Math.min(leftMax[k],rightMax[k] ) - a[k];
		}
		System.out.println(ans);
		
	}
}
