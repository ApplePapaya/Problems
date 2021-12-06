package com.run.leetcode.dfs;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch79 {


        /**
         This is simple dfs problem. Starting every index of the matrix, check if its can form a word by doing dfs
         1> inside main method run a loop to iterate through row and column and for each index i, j do dfs for the word starting at char 0
         2> Define the direction array to go all 4 directions
         3> Inside the dfs method, check if the index = word.length . if yes then we have found the word and return true;
         4> if not check for validations of the row and col, if they are withing bounds and the achar at row/col is not equal to char at index in the word.
         5> mark the element at row col as visited by makin it #
         6> Now loop through all possible directions and call dfs
         7> if dfs returns true DONT RETURN , just break the loop
         8> Reset the characters in the matrix back to the original and then return the boolean value
         In regular dfs , once we visit a node we dont want to go there again
         however here for every node that matches first character, we want to explore its connected nodes again
         hence if we mark it as visited or the grid as # we need to undo it



         We argue that a more accurate term to summarize the solution would be backtracking, which is a methodology where we mark the current path of exploration, if the path does not lead to a solution, we then revert the change (i.e. backtracking) and try another path.

         As the general idea for the solution, we would walk around the 2D grid, at each step we mark our choice before jumping into the next step. And at the end of each step, we would also revert our marking, so that we could have a clean slate to try another direction. In addition, the exploration is done via the DFS strategy, where we go as further as possible before we try the next direction.

         Complexity Analysis

         Time Complexity: O(N?3L)\mathcal{O}(N \cdot 3 ^ L)O(N?3L) where NNN is the number of cells in the board and LLL is the length of the word to be matched.

         For the backtracking function, initially we could have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from). As a result, the execution trace after the first step could be visualized as a 3-ary tree, each of the branches represent a potential exploration in the corresponding direction. Therefore, in the worst case, the total number of invocation would be the number of nodes in a full 3-nary tree, which is about 3L3^L3L.

         We iterate through the board for backtracking, i.e. there could be NNN times invocation for the backtracking function in the worst case.

         As a result, overall the time complexity of the algorithm would be O(N?3L)\mathcal{O}(N \cdot 3 ^ L)O(N?3L).

         Space Complexity: O(L)\mathcal{O}(L)O(L) where LLL is the length of the word to be matched.
         The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length of the call stack would be the length of the word. Therefore, the space complexity of the algorithm is O(L)\mathcal{O}(L)O(L).

         **/
        //1ms
        public boolean exist2(char[][] board, String word) {
            //  if(word.equals("ABCESEEEFS")){
            //    return true;
            //}
            int r = board.length;
            for(int i = 0; i < r; i++){
                for(int j = 0; j < board[i].length; j++){
                    if(word.charAt(0) == board[i][j]){
                        if(dfs(board, word, 1, i, j, new boolean[board.length][board[0].length])){
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        public boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited){
            if(index == word.length()){
                return true;
            }
            visited[i][j] = true;
            if(isValid(board, i - 1, j, visited)){
                if(board[i - 1][j] == word.charAt(index)){
                    if(dfs(board, word, index + 1, i - 1, j, visited)){
                        return true;
                    }
                }
            }
            if(isValid(board, i + 1, j, visited)){
                if(board[i + 1][j] == word.charAt(index)){
                    if(dfs(board, word, index + 1, i + 1, j, visited)){
                        return true;
                    }
                }
            }
            if(isValid(board, i, j - 1, visited)){
                if(board[i][j - 1] == word.charAt(index)){
                    if(dfs(board, word, index + 1, i, j - 1, visited)){
                        return true;
                    }
                }
            }
            if(isValid(board, i, j + 1, visited)){
                if(board[i][j + 1] == word.charAt(index)){
                    if(dfs(board, word, index + 1, i, j + 1, visited)){
                        return true;
                    }
                }
            }
            visited[i][j] = false;
            return false;
        }
        public boolean isValid(char[][] board, int i, int j, boolean[][] visited){
            if(i >= 0 && j >= 0 && i < board.length && j < board[i].length && !visited[i][j]){
                return true;
            }
            return false;
        }

        //168ms
        public boolean exist(char[][] board, String word) {
            char[] wrd = word.toCharArray();
            for(int i = 0; i < board.length; i++)
            {
                for(int j = 0; j < board[0].length; j++)
                {
                    if(wrd[0] == board[i][j])
                        if(dfs(board, i, j, wrd, 0))
                            return true;

                }
            }
            return false;
        }

        private  final int[][] dirs = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

        public boolean dfs(char[][] board, int row, int col, char[] word, int index){
            if(index >= word.length)
                return true;

            if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word[index])//here we dont need to chk for # since they will never be matching with word at index
                return false;

            char c = board[row][col];
            board[row][col] = '#';
            boolean result = false;
            for(int[] dir : dirs)
            {
                int x = row + dir[0];
                int y = col + dir[1];
                result = dfs(board, x, y, word, index + 1);
                if(result)
                    break;//dont return here since it will manipulate the data
            }
            board[row][col] = c;
            return result;
        }
    }

/**



 omplexity Analysis

 Time Complexity: O(N?4L)\mathcal{O}(N \cdot 4 ^ L)O(N?4L) where NNN is the number of cells in the board and LLL is the length of the word to be matched.

 For the backtracking function, its execution trace would be visualized as a 4-ary tree, each of the branches represent a potential exploration in the corresponding direction. Therefore, in the worst case, the total number of invocation would be the number of nodes in a full 4-nary tree, which is about 4L4^L4L.

 We iterate through the board for backtracking, i.e. there could be NNN times invocation for the backtracking function in the worst case.

 As a result, overall the time complexity of the algorithm would be O(N?4L)\mathcal{O}(N \cdot 4 ^ L)O(N?4L).

 Space Complexity: O(L)\mathcal{O}(L)O(L) where LLL is the length of the word to be matched.
 The main consumption of the memory lies in the recursion call of the backtracking function. The maximum length of the call stack would be the length of the word. Therefore, the space complexity of the algorithm is O(L)\mathcal{O}(L)O(L).
 --------------------------------------------------------------

 It would be bounded by whatever is smaller. So it should be O(N*(min(4^L,N)))

 After the first exploring,each step check 3 directions. I would say the time complexity is O(N*3^(L-1)).

 **/

