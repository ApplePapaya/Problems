package com.run.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class CountingBits {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		long total = LongStream.rangeClosed(1, 3_000_000)
								.parallel()
								.sum();
		System.out.println(total + "   - total");
		int pool = ForkJoinPool.commonPool().getPoolSize();
		System.out.println("pool : " + pool);
		List<Long> time = new ArrayList<>();
		Thread[] r = new Thread[18];
		for( int i = 0; i < r.length; i++)
		{
			r[i] = new Thread( () -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" Ex : " + Thread.currentThread().getName()+ "  " + System.nanoTime());
				time.add(System.nanoTime());
			});
			
			
		}
		for(Thread a : r)
			a.start();
		for(Thread a : r)
			a.join();
		
		
		Collections.sort(time);
		System.out.println(time);
		
		System.out.println(Arrays.toString(countBits(40)));
	}
	
	public static int[] countBits(int num) {
		
		int[] res = new int[num +  1];
		res[0] = 0;
		for(int i = 1; i <= num; i++) {
			if((i & 1) == 0) {
				res[i] = res[i >> 1];
			}
			else {
				res[i] = res[i-1] + 1;
			}
		}
		return res;
	}

}
