package com.run.leetcode.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        int r = image.length;
        int c = image[0].length;
        
        int color = image[sr][sc];
        
        dfs(image, sr, sc, color, newColor);
        
        return image;
    }
    
    public void dfs(int[][] image, int x, int y, int color, int newColor){
        
        if(x < 0 || y < 0 || x >= image.length || y >= image[0].length || image[x][y] != color)
            return;
        
        image[x][y] = newColor;
        
        dfs(image, x + 1, y, color, newColor);
        dfs(image, x - 1, y, color, newColor);
        dfs(image, x, y + 1, color, newColor);
        dfs(image, x, y - 1, color, newColor);
        
    }
}

public class LeetCodeMainCLassSAmple {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static int[][] stringToInt2dArray(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
          return new int[0][0];
        }
    
        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
          JsonArray cols = jsonArray.get(i).asArray();
          arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }
    
    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }
    
        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            sb.append(Integer.toString(item));
            sb.append(",");
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] image = stringToInt2dArray(line);
            line = in.readLine();
            int sr = Integer.parseInt(line);
            line = in.readLine();
            int sc = Integer.parseInt(line);
            line = in.readLine();
            int newColor = Integer.parseInt(line);
            
            int[][] ret = new Solution().floodFill(image, sr, sc, newColor);
            
            String out = int2dArrayToString(ret);
            
            System.out.print(out);
        }
    }
}