package com.run.leetcode.Design;

/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 *     A move is guaranteed to be valid and is placed on an empty block.
 *     Once a winning condition is reached, no more moves are allowed.
 *     A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 *
 * Implement the TicTacToe class:
 *
 *     TicTacToe(int n) Initializes the object the size of the board n.
 *     int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 *
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 *
 *
 * Constraints:
 *
 *     2 <= n <= 100
 *     player is 1 or 2.
 *     0 <= row, col < n
 *     (row, col) are unique for each different call to move.
 *     At most n2 calls will be made to move.
 */
public class DesignTicTacToe348 {
    /**
     *The algorithm can be implemented as follows:
     *
     *     For a given n, initialize arrays rows and cols of size n with the value of every element set to 0.
     *
     *     For each move, we must increment/decrement the row, column, diagonal, and anti-diagonal according to who is the current player and which cell was marked. If the current player is player 1, we increment the value and if it is player 2, we decrement the value.
     *
     *         Note: If we apply simple math rules, we can increment or decrement the values irrespective of the player.
     *
     *     We can use an additional variable currentPlayer with the value 1 for player 1 and -1 for player 2, and add the value of currentPlayer to the current row, column, diagonal and anti-diagonal.
     *
     *     As a final step, we must determine whether the current player has won the game. If any row, column, diagonal, or anti-diagonal is equal to n (for player 1) or -n (for player 2) then the current player has won the game.
     *
     *     Also, rather than having separate conditions to check whose turn it is, we can check the absolute values.
     *
     * Time COmplexity  = O(1)
     * Space Complexity = o(n)
     */
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;

    public DesignTicTacToe348(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int currentPlayer = (player == 1) ? 1 : -1;
        // update currentPlayer in rows and cols arrays
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;
        // update diagonal
        if (row == col) {
            diagonal += currentPlayer;
        }
        //update anti diagonal
        if (col == (cols.length - row - 1)) {
            antiDiagonal += currentPlayer;
        }
        int n = rows.length;
        // check if the current player wins
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n) {
            return player;
        }
        // No one wins
        return 0;
    }

    /**
     * Approach Basic with O(N^2) Spac and O(N) time just for reference
     */

    private int[][] board;
    private int n;

    public DesignTicTacToe348(int n, boolean approach) {
        board = new int[n][n];
        this.n = n;
    }

    public int move2(int row, int col, int player) {
        board[row][col] = player;
        // check if the player wins
        if ((checkRow(row, player)) ||
                (checkColumn(col, player)) ||
                (row == col && checkDiagonal(player)) ||
                (col == n - row - 1 && checkAntiDiagonal(player))) {
            return player;
        }
        // No one wins
        return 0;
    }

    private boolean checkDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][row] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][n - row - 1] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, int player) {
        for (int row = 0; row < n; row++) {
            if (board[row][col] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int row, int player) {
        for (int col = 0; col < n; col++) {
            if (board[row][col] != player) {
                return false;
            }
        }
        return true;
    }
}
