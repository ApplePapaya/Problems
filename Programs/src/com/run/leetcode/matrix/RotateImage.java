package com.run.leetcode.matrix;

import java.util.Arrays;

//https://leetcode.com/problems/rotate-image/
public class RotateImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {
		               {1,2,3},
		               {4,5,6},
		               {7,8,9}
		}	;
		rotate(arr);
		for(int[] row : arr)
			System.out.println(Arrays.toString(row));
			
		
	}
	
	public static void rotate(int[][] matrix) {
		int s=0;int e = matrix.length-1;
		while(s<e) {
			int temp[] = matrix[s];
			matrix[s] = matrix[e];
			matrix[e] = temp;
			s++;
			e--;
		}
		
		for(int i =0;i<matrix.length;i++) {
			for(int j=i+1;j<matrix[0].length;j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		
	}

}
