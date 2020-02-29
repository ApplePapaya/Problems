package com.example.graphTraversals;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * 
 * 
 Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

Accepted
 *
 */
public class SurroundedRegions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[][] input = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		
		solve(input);
		for(char[] a : input)
			System.out.println(Arrays.toString(a));

	}
	
	public static void solve(char[][] board) {
		if(board.length ==0 || board[0].length == 0)
			return;
		
		/* Below are the steps
        1> Chk if any of the edges have a 0.
        2> then do dfs for that element and chk for all o's adjacent to it and mark it as * - diff character
        3> now loop through all rows and columns and mark all o as X and all * as back to 0
        
        */
		int rows = board.length;
		int cols = board[0].length;
		for(int i = 0; i < rows; i++)
		{
			if(board[i][0] == 'O')
				boundaryDFS(board, i, 0);
			if(board[i][cols-1] == 'O')
				boundaryDFS(board, i, cols-1);
		}
			
		for(int j = 0; j < cols; j++) {
			if(board[0][j] == 'O')
				boundaryDFS(board, 0, j);
			if(board[rows-1][j] == 'O')
				boundaryDFS(board, rows - 1, j);
			
		}
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					if(board[i][j] == 'O')
						board[i][j] ='X';
					if(board[i][j] == '*')
						board[i][j] = 'O';
				}
				
			}
			
			
			
		}
	
	public static void boundaryDFS(char[][] board, int i, int j) {
		
		board[i][j] = '*';
		
		if(i > 0 && board[i-1][j] == 'O')
			boundaryDFS(board, i - 1, j);
		
		if(i < board.length -1  && board[i+1][j] == 'O')
			boundaryDFS(board, i + 1, j);
		
		if(j > 0 && board[i][j-1] == 'O')
			boundaryDFS(board, i, j - 1);
		
		if(j < board[0].length -1  && board[i][j+1] == 'O')
			boundaryDFS(board, i, j+1);
		
		
	}
			
			
			
		
	

}
