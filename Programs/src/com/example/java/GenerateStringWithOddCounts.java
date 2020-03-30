package com.example.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toList;

public class GenerateStringWithOddCounts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(generateTheString(10));
		System.out.println(generateTheString(15));
		int[] arr = {1, 2, 4};
		List<Double> list = Arrays.stream(arr)
									.mapToDouble(i -> (double) i)
									.boxed()
									.collect(Collectors.toList());
		List<Double> list2 = Arrays.stream(arr)
									.mapToDouble(Double :: valueOf)
									.boxed()
									.collect(Collectors.toList());
		System.out.println(list);
									System.out.println(list2);
									
	}

	 public static String generateTheString(int n) {
		 StringBuilder sb = new StringBuilder();
	        if (n % 2 == 1) {
	            for (int i = 1; i <= n; i++) {
	                sb.append("c");
	            }
	        } else {
	            for (int i = 1; i <= n - 1; i++) {
	                sb.append("a");
	            }
	            sb.append("b");
	        }
	        return sb.toString();
	    }
	    
}
