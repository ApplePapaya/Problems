package com.example.java;

import java.util.Date;
/**
Total JVM memory :257425408
Before Free memory :254741016
After Free memory :230242248
After GC Free memory :443080952
 * @author prparasu
 *
 */
public class CheckGC {

	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Total JVM memory :" + rt.totalMemory());
		System.out.println("Before Free memory :" + rt.freeMemory());
		
		Date d = null;
		for(int i =0; i<9999000; i ++) {
			d = new Date();
			String s = new String("Hello " + i);
			d= null;
		}
		System.out.println("After Free memory :" + rt.freeMemory());
		rt.gc();
		
		System.out.println("After GC Free memory :" + rt.freeMemory());
	}

}
