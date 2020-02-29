package com.run.leetcode.hardProblems;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] input =
			{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};

		System.out.println(isValidSudoku(input));
	}
	
	public static boolean isValidSudoku(char[][] input) {
		Set<String> seen  = new HashSet<>();
		
		for(int i = 0; i< 9; i++) {
			for(int j = 0; j < 9; j++) {
				char c = input[i][j];
				if(c != '.') {
					if(!seen.add(c + " found in row " + i) ||
							!seen.add(c + " found in column "+ j) ||
							   !seen.add(c + " found in box " + i/3 + "-" + j/3))
						return false;
				}
			}
		}
		return true;
	}

}
