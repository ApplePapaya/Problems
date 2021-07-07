package com.run.leetcode.matrix;
/**
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 *     A stone '#'
 *     A stationary obstacle '*'
 *     Empty '.'
 *
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 *
 * Example 1:
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 *
 * Example 2:
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 *
 * Example 3:
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 *
 *
 * Constraints:
 *
 *     m == box.length
 *     n == box[i].length
 *     1 <= m, n <= 500
 *     box[i][j] is either '#', '*', or '.'.
 */

import java.util.Arrays;

public class RotatingTheBox1861 {
    public char[][] rotateTheBox(char[][] box) {
        int noRows = box.length;
        int noCols = box[0].length;

        char [][] result = new char[noCols][noRows];
        for (int i=0; i<noCols; i++)
            Arrays.fill(result[i], '.');

        for (int i=0; i<noRows; i++){
            int stopRow = noCols-1;
            int col = noRows-i-1;
            for (int j=noCols-1; j>=0; j--){
                if (box[i][j] == '*'){
                    result[j][col] = '*';
                    stopRow = j-1;//why j - 1

                } else if (box[i][j] == '#'){
                    result[stopRow][col] = '#';
                    stopRow--;
                } // end-else
            } // end-for
        } // end-for

        return result;
    } // end-rotateTheBox
    //6ms
    public char[][] rotateTheBox3(char[][] box) {
        // assuming input box array is always valid
        int m = box.length;
        int n = box[0].length;

        char[][] rotatedBox = new char[n][];
        for(int i=0;i<n;i++){
            rotatedBox[i] = new char[m];
            Arrays.fill(rotatedBox[i],'.');
        }


        /*
        for(int i=0;i<m;i++){
            int stones = 0, blanks = 0;
            for(int j=0;j<n+1;j++){ // to reach the end of the array,
                                    // j goes up to n instead of n-1
                if(j == n || box[i][j] == '*'){ // wall '*' or bottom

                    if(j != n) {
                        rotatedBox[j][m-1-i] = '*';
                    }

                    int k = j;
                    while(stones > 0){
                        k--;
                        rotatedBox[k][m-1-i] = '#';
                        stones--;
                    }
                    while(blanks > 0){
                        k--;
                        rotatedBox[k][m-1-i] = '.';
                        blanks--;
                    }
                } else if(box[i][j] == '#'){ // stone '#'
                    stones++;
                } else if(box[i][j] == '.'){ // blank '.'
                    blanks++;
                }
            }
        }
        */

        for (int i = 0; i < m; ++i) {
            for (int j = n - 1, k = n - 1; j >= 0; --j) {
                if (box[i][j] != '.') {
                    k = box[i][j] == '*' ? j : k;
                    rotatedBox[k--][m - i - 1] = box[i][j];
                }
            }
        }


        return rotatedBox;
    }
    //11ms
    public char[][] rotateTheBox2(char[][] box) {
        char[][] ans = new char[box[0].length][box.length];
        for(int i = 0; i < box.length; i++){
            int j = box[i].length - 1;
            int pos = j;
            while(j >= 0){
                // mark the first (possible) empty position
                if(box[i][j] == '*'){
                    pos = j - 1;
                }
                // move the stone to the first empty position
                // set the current stone to empty
                else if(box[i][j] == '#'){
                    box[i][j] = '.';
                    box[i][pos] = '#';
                    // transpose to answer array
                    ans[pos][ans[0].length - i - 1] = box[i][pos];
                    pos--;
                }
                // transpose to answer array
                ans[j][ans[0].length - i - 1] = box[i][j];
                j--;
            }
        }
        return ans;
    }
}
