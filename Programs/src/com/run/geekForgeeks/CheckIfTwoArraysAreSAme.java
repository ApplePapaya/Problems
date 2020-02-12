package com.run.geekForgeeks;

/*package whatever //do not write package name here */
23280666447858  amazon test id

23280666447858
import java.util.*;
import java.lang.*;
import java.io.*;

class CheckIfTwoArraysAreSAme {
	public static void main (String[] args) {
		/*
		 * StringBuilder sb = new StringBuilder(); Scanner in = new Scanner(System.in);
		 * int T = in.nextInt(); while(T-->0){ int N = in.nextInt(); int A[] = new
		 * int[N]; int B[] = new int[N]; for(int i=0; i<N; i++) A[i] = in.nextInt();
		 * for(int i=0; i<N; i++) B[i] = in.nextInt();
		 * sb.append(""+CheckIfTwoArraysAreEqual(A, B, N)+"\n"); }
		 */
		long[] a = {8,9,7,5,3,1};
		long[] b = {5,4,2,5,1,7};
		String a = "sdfsdf"
				a.conta
		System.out.println(CheckIfTwoArraysAreEqual(a, b, 5));
	}
	public static int CheckIfTwoArraysAreEqual(long[] a, long[] b ,long n){
	    Map<Long, Integer> arrayMap = new HashMap<>();
	    for(long val : a){
	        arrayMap.put(val, arrayMap.getOrDefault(val,0)+1);
	    }
	    for(long val :b){
	        if(arrayMap.containsKey(val)){
	            int count = arrayMap.get(val);
	            arrayMap.put(val,count--);
	            if(count<0)
	            return 0;
	        }else{
	            return 0;
	        }
	    }
	    return 1;
	}
	
	public static int CheckIfTwoArraysAreEqualOptimized(long[] a, long[] b ,long n){
	    Set<Long> set = new HashSet<>();
	    long sum1 =0;
	    long sum2=0;
	    
	    for(long val : a)
	    {
	    	set.add(val);
	    	sum1 += val;
	    	
	    }
	    System.out.println(set);
	    for(long val :a)
	    {
	    	if(!set.contains(val))
	    		return 0;
	    	sum2 += val;
	    }
	    
	    if(sum1 == sum2)
	    	return 1;
	    else
	    	return 0;
		
		
	}
}