package com.run.leetcode.medium;

import java.util.Arrays;

public class SingleNumberIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int mask = -7 & 7;
		//System.out.println(mask);
		
		for(int i = 25; i <= 26; i++)
		{
			//System.out.println("I : " + i + " - "+ (i & -i) );
			System.out.println("i :" + Integer.toBinaryString(i) + "    -i :" + Integer.toBinaryString(-i) + "   and I : " + Integer.toBinaryString(-(-i)));
		}
		System.out.println(Arrays.toString(singleNumber(new int[] {1,1,2,2,3,4,4,5,5,6,6,7})));
		
		System.out.println(Arrays.toString(singleNumber2(new int[] {1,1,2,2,3,4,4,5,5,6,6,7})));
	}
	
	
	public static int[] singleNumber2(int[] nums) {
        int temp = 0;
        for (int n : nums) {
            temp ^= n;
        }
        int mask = -temp & temp;

        int single1 = 0, single2 = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                single1 ^= num;
            } else {
                single2 ^= num;
            }
        }
        return new int[] {single1, single2};
    }
public static int[] singleNumber(int[] nums) {
        
        int xor = 0;
        for(int num : nums){
        	xor = xor ^ num;
        }

        int bit = 0;
        for(int i = 0; i < 32; i++){
        	if(((xor >>> i) & 1) != 0){
        		bit = i;
        		break;
        	}
        }
        xor = (1 << bit);

        int[] result = new int[2];
        int a = 0; 
        int b = 0;

        for(int num : nums){

        	if((num & xor) == 0){
        		a = a ^ num;
        	}
        	else if((num & xor) != 0){
        		b = b ^ num;
        	}
        }
        result[0] = a;
        result[1] = b;
        return result;
    }

}
