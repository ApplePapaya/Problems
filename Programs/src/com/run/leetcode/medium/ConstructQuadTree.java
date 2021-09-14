package com.run.leetcode.medium;

import javax.print.attribute.SetOfIntegerSyntax;
import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing the grid.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 *
 *     val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 *     isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 *
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 *
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 *     If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 *     If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 *     Recurse for each of the children with the proper sub-grid.
 *
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 *
 * Quad-Tree format:
 *
 * The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 *
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 *
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 * Explanation: The explanation of this example is shown below:
 * Notice that 0 represnts False and 1 represents True in the photo representing the Quad-Tree.
 *
 * Example 2:
 *
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
 * The topLeft, bottomLeft and bottomRight each has the same value.
 * The topRight have different values so we divide it into 4 sub-grids where each has the same value.
 * Explanation is shown in the photo below:
 *
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: [[1,1]]
 *
 * Example 4:
 *
 * Input: grid = [[0]]
 * Output: [[1,0]]
 *
 * Example 5:
 *
 * Input: grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]
 * Output: [[0,1],[1,1],[1,0],[1,0],[1,1]]
 *
 *Time complexit - O(N^2) or O(N^2 * log(N) base 4)
 * Since every cell is visited once atleast to check for the value int he quad.
 * If i the worst case there is no quad of more than single, cell, there will be logN levels to the base 4 and that many
 * times the cell will be scanned.
 *
 * Constraints:
 *
 *     n == grid.length == grid[i].length
 *     n == 2^x where 0 <= x <= 6
 */
public class ConstructQuadTree {

    public static void main(String[] args){

        System.out.println(":"+ Arrays.toString(new char[5])+ ":");
        System.out.println(":"+new String(new char[5]).replace('\0', ' ')+":");
        int[][] grid = {{1,1,0,0},{1,1,0,0},{0,0,1,1},{0,0,1,1}};
        ConstructQuadTree q = new ConstructQuadTree();
     //   q.construct(grid);
    }

//Takes 0 ms
    public QNode construct(int[][] grid) {

        return build(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    private QNode build(int[][] grid, int rowS, int rowE, int colS, int colE) {
        if (rowS == rowE) {//if just one row
            return new QNode(grid[rowS][colS] == 1, true);
        } else {
            if (check(grid, rowS, rowE, colS, colE)) {
                return new QNode(grid[rowS][colS] == 1, true);
            } else {
                QNode curr = new QNode(true, false);//val doesnt matter here
                curr.topLeft = build(grid, rowS, rowS+(rowE-rowS)/2, colS, colS+(colE-colS)/2);
                curr.topRight = build(grid, rowS, rowS+(rowE-rowS)/2, colS+(colE-colS)/2+1, colE);
                curr.bottomLeft = build(grid, rowS+(rowE-rowS)/2+1, rowE, colS, colS+(colE-colS)/2);
                curr.bottomRight = build(grid, rowS+(rowE-rowS)/2+1, rowE, colS+(colE-colS)/2+1, colE);
                return curr;
            }
        }
    }

    private boolean check(int[][] grid, int rowS, int rowE, int colS, int colE) {
        int val = grid[rowS][colS];
        for (int i = rowS; i <= rowE; i++) {
            for (int j = colS; j <= colE; j++) {
                if (grid[i][j] != val)
                    return false;
            }
        }
        return true;
    }



    //Takes 1 ms
    public QNode construct2(int[][] grid) {
        return (grid.length == 0)? null : helper(grid, 0, 0, grid.length);
    }

    public QNode helper(int[][] grid, int x, int y, int len){
        QNode newNode = new QNode(grid[x][y] != 0, true, null, null, null, null);
        int half = len / 2;

        if(len == 1) return newNode;

        QNode topLeft = helper(grid, x, y, half);
        QNode topRight = helper(grid, x, y + half, half);
        QNode bottomLeft = helper(grid, x + half, y, half);
        QNode bottomRight = helper(grid, x + half, y + half, half);

        if(!topLeft.isLeaf || !topRight.isLeaf || !bottomLeft.isLeaf || !bottomRight.isLeaf
                || topLeft.val != topRight.val || topRight.val != bottomLeft.val
                || bottomLeft.val != bottomRight.val){
            newNode.topLeft = topLeft;
            newNode.topRight = topRight;
            newNode.bottomLeft = bottomLeft;
            newNode.bottomRight = bottomRight;
            newNode.isLeaf = false;
        }
        return newNode;
    }
}
class QNode {
    public boolean val;
    public boolean isLeaf;
    public QNode topLeft;
    public QNode topRight;
    public QNode bottomLeft;
    public QNode bottomRight;


    public QNode() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public QNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public QNode(boolean val, boolean isLeaf, QNode topLeft, QNode topRight, QNode bottomLeft, QNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
